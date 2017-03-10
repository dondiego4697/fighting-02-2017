package services;

import objects.HttpStatus;
import objects.ObjUser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.mappers.UserMapper;
import services.mappers.UserMapperLeaderBoard;

import java.util.List;

/**
 * Created by Denis on 21.02.2017.
 */
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String TABLENAME = "users";

    public interface Callback {
        void onSuccess(String status);

        void onError(String status);
    }

    public interface CallbackWithUser {
        void onSuccess(String status, ObjUser objUser);

        void onError(String status);
    }


    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(ObjUser objUser, Callback callback) {
        try {
            int rownum = jdbcTemplate.update(
                    "INSERT INTO " + TABLENAME + " (login,password) VALUES(?,?)",
                    objUser.getLogin(),
                    objUser.getPassword());

            if (rownum == 0) {
                callback.onError(new HttpStatus().getForbidden());
            } else {
                callback.onSuccess(new HttpStatus().getOk());
            }
        } catch (Exception e) {
            callback.onError(new HttpStatus().getForbidden());
        }
    }

    public void login(ObjUser objUser, CallbackWithUser callbackWithUser) {
        String SQL = "SELECT * FROM users WHERE (login, password) = (?,?)";
        ObjUser user = null;
        try {
            user = jdbcTemplate.queryForObject(SQL,
                    new Object[]{
                            objUser.getLogin(), objUser.getPassword()}, new UserMapper());
        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getNotFound());
        }
        if (user != null) {
            callbackWithUser.onSuccess(new HttpStatus().getOk(), user);
        } else {
            callbackWithUser.onError(new HttpStatus().getNotFound());
        }
    }

    public void update(ObjUser newObjUser, CallbackWithUser callbackWithUser) {
        try {
            int rownum = jdbcTemplate.update(
                    "UPDATE users SET login= ? WHERE login = ?",
                    newObjUser.getNewlogin(),
                    newObjUser.getLogin());

            if (rownum == 0) {
                callbackWithUser.onError(new HttpStatus().getBadRequest());
            } else {
                callbackWithUser.onSuccess(new HttpStatus().getOk(), newObjUser);
            }
        } catch (Exception e) {
            callbackWithUser.onError(new HttpStatus().getBadRequest());
        }
    }

    public void updateInfo(ObjUser objUser, CallbackWithUser callbackWithUser) {
        try {
            System.out.println(objUser.getJson());
            int rownum = jdbcTemplate.update(
                    "UPDATE users SET rating = ?, game_count = ?, game_count_win = ?, " +
                            "crystal_purple = ?, crystal_red = ?, crystal_blue = ?, crystal_green = ?" +
                            "WHERE login = ?",
                    objUser.getRating(),
                    objUser.getGameCount(),
                    objUser.getGameCountWin(),
                    objUser.getCrystalPurple(),
                    objUser.getCrystalRed(),
                    objUser.getCrystalBlue(),
                    objUser.getCrystalGreen(),
                    objUser.getLogin()
            );
            if (rownum == 0) {
                callbackWithUser.onError(new HttpStatus().getBadRequest());
            } else {
                callbackWithUser.onSuccess(new HttpStatus().getOk(), objUser);
            }
        } catch (Exception e) {
            System.out.println(e);
            callbackWithUser.onError(new HttpStatus().getBadRequest());
        }
    }

    public void changePass(ObjUser objUser, CallbackWithUser callbackWithUser) {
        String SQL = "UPDATE users SET password= ? WHERE (login,password) =(?,?)";
        try {
            int rownum = jdbcTemplate.update(
                    SQL, objUser.getNewpassword(), objUser.getLogin(), objUser.getPassword());

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
        String SQL = "SELECT login, rating FROM users ORDER BY rating DESC LIMIT 20";
        List<ObjUser> users = jdbcTemplate.query(SQL, new UserMapperLeaderBoard());
        for (int i = 0; i < users.size(); i++) {
            jsonArray.put(users.get(i).getJson());
        }
        return jsonArray;
    }
}

