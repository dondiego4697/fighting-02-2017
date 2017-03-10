package services.mappers;

import objects.ObjUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Denis on 08.03.2017.
 */
public class UserMapper implements RowMapper<ObjUser> {
    public ObjUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ObjUser objUser = new ObjUser();
        objUser.setId(rs.getInt("id"));
        objUser.setLogin(rs.getString("login"));
        objUser.setPassword(rs.getString("password"));
        objUser.setGameCount(rs.getInt("game_count"));
        objUser.setGameCountWin(rs.getInt("game_count_win"));
        objUser.setCrystalGreen(rs.getInt("crystal_green"));
        objUser.setCrystalRed(rs.getInt("crystal_red"));
        objUser.setCrystalBlue(rs.getInt("crystal_blue"));
        objUser.setCrystalPurple(rs.getInt("crystal_purple"));
        objUser.setRating(rs.getInt("rating"));
        return objUser;
    }
}
