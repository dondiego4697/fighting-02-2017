package services;

import objects.HttpStatus;
import objects.ObjUser;
import objects.ObjUsersData;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import services.mappers.UserMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by andrey on 21.02.2017.
 */
@Service
public class UserService /*implements  PlatformTransactionManager*/ {
    /*public TransactionStatus getTransaction(TransactionDefinition var1) throws TransactionException{
        return ;
    }*/
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String TABLENAME = "users";
    private PlatformTransactionManager transactionManager;

    public interface Callback {
        void onSuccess(String status);

        void onError(String status);
    }

    public  interface CallbackWithUser<T> {
        void onSuccess(String status, T objUser);
        void onError(String status);
    }

    public UserService(JdbcTemplate jdbcTemplate,PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(ObjUser objUser, Callback callback) {
        if (objUser.getPassword().isEmpty() || objUser.getLogin().isEmpty()) {
            callback.onError(new HttpStatus().getForbidden());
        } else if (objUser.getPassword().length() < 8) {
            callback.onError(new HttpStatus().getForbidden());
        } else if (objUser.getLogin().matches("[а-яА-ЯёЁ]+")) {
            callback.onError(new HttpStatus().getForbidden());
        } else if (objUser.getLogin().length() < 4) {
            callback.onError(new HttpStatus().getForbidden());
        } else {
        try {
                registerUser(objUser);
                callback.onSuccess(new HttpStatus().getOk());
            } catch (RuntimeException e) {
                callback.onError(new HttpStatus().getForbidden());
            }
        }
    }

    @Transactional
    private void registerUser(ObjUser objUser)  {
        jdbcTemplate.update("insert into users (login,password) values (?,?);", objUser.getLogin(), objUser.getHashPassword());
        jdbcTemplate.update("INSERT INTO usersData (login) values (?);", objUser.getLogin());
    }

    @Transactional
    private void updateUsers(ObjUser objUser)  {
        jdbcTemplate.update("update users set login=? where login=?;", objUser.getNewlogin(),objUser.getLogin());
        jdbcTemplate.update("update usersdata set login=? where login=?;", objUser.getNewlogin(),objUser.getLogin());
    }

    @Transactional
    private boolean checkINputPasAndLog(ObjUser user){
        String SQL = "SELECT * FROM users WHERE login = ?";
        ObjUser userDB = jdbcTemplate.queryForObject(SQL,
                new Object[]{user.getLogin()}, new UserMapper());
        return user.comparePass.test(userDB.getPassword());
    }

    public void login(ObjUser objUser, CallbackWithUser callbackWithUser) {
        try {
           if(checkINputPasAndLog(objUser)) callbackWithUser.onSuccess(new HttpStatus().getOk(), objUser);
           else callbackWithUser.onError(new HttpStatus().getNotFound());
            } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getNotFound());
        }
    }

    public void update(ObjUser newObjUser, CallbackWithUser callbackWithUser) {
        try {
            updateUsers(newObjUser);
            callbackWithUser.onSuccess(new HttpStatus().getOk(), newObjUser);

        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getBadRequest());
        }
    }

    public void updateInfo(ObjUsersData objUsersData, CallbackWithUser callbackWithUser) {
        try {
            System.out.println(objUsersData.getJson());
            int rownum = jdbcTemplate.update(
                    "UPDATE usersData SET rating = ?, game_count = ?, game_count_win = ?, " +
                            "crystal_purple = ?, crystal_red = ?, crystal_blue = ?, crystal_green = ?" +
                            "WHERE login = ?",
                    objUsersData.getRating(),
                    objUsersData.getGameCount(),
                    objUsersData.getGameCountWin(),
                    objUsersData.getCrystalPurple(),
                    objUsersData.getCrystalRed(),
                    objUsersData.getCrystalBlue(),
                    objUsersData.getCrystalGreen(),
                    objUsersData.getLogin()
            );
            if (rownum == 0) {
                callbackWithUser.onError(new HttpStatus().getBadRequest());
            } else {
                callbackWithUser.onSuccess(new HttpStatus().getOk(), objUsersData);
            }
        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getBadRequest());
        }
    }

    public void changePass(ObjUser objUser, CallbackWithUser callbackWithUser) {

        try {
            if(!checkINputPasAndLog(objUser)) {
                callbackWithUser.onError(new HttpStatus().getNotFound());
                return;
            }
        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getNotFound());
            return;
        }
        String SQL = "UPDATE users SET password= ? where login=?";
        try {
            int rownum = jdbcTemplate.update(
                    SQL, objUser.getNewHashPassword(),objUser.getLogin());

            if (rownum == 0) {
                callbackWithUser.onError(new HttpStatus().getBadRequest());
            } else {
                callbackWithUser.onSuccess(new HttpStatus().getOk(), objUser);
            }
        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getBadRequest());
        }
    }

    public JSONArray getLeaders() {
        final JSONArray jsonArray = new JSONArray();
        String SQL = "SELECT login, rating FROM usersdata ORDER BY rating DESC LIMIT 20";
        List<ObjUsersData> users = jdbcTemplate.query(SQL, new RowMapper<ObjUsersData>(){
            @Override
            public ObjUsersData mapRow(ResultSet rs, int rownumber) throws SQLException {
                ObjUsersData objUsersData = new ObjUsersData();
                objUsersData.setLogin(rs.getString("login"));
                objUsersData.setRating(rs.getInt("rating"));
                return objUsersData;
            }
            });
        for (ObjUsersData user : users) {
            jsonArray.put(user.getJson());
        }
        return jsonArray;
    }
}

