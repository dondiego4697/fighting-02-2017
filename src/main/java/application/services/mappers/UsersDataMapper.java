package application.services.mappers;

import application.objects.ObjUsersData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrey on 27.03.17.
 */
public class UsersDataMapper implements RowMapper<ObjUsersData> {
        public ObjUsersData mapRow(ResultSet rs, int rowNum) throws SQLException {
            ObjUsersData objUsersData = new ObjUsersData();
            objUsersData.setLogin(rs.getString("login"));
            objUsersData.setGameCount(rs.getInt("game_count"));
            objUsersData.setGameCountWin(rs.getInt("game_count_win"));
            objUsersData.setCrystalGreen(rs.getInt("crystal_green"));
            objUsersData.setCrystalRed(rs.getInt("crystal_red"));
            objUsersData.setCrystalBlue(rs.getInt("crystal_blue"));
            objUsersData.setCrystalPurple(rs.getInt("crystal_purple"));
            objUsersData.setRating(rs.getInt("rating"));
            return objUsersData;
        }
    }

