package services.mappers;

import objects.ObjUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Denis on 09.03.2017.
 */
public class UserMapperLeaderBoard implements RowMapper<ObjUser> {
    public ObjUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ObjUser objUser = new ObjUser();
        objUser.setLogin(rs.getString("login"));
        objUser.setRating(rs.getInt("rating"));
        return objUser;
    }
}
