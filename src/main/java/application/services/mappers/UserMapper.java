package application.services.mappers;

import application.objects.ObjUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrey on 08.03.2017.
 */
public class UserMapper implements RowMapper<ObjUser> {
    public ObjUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ObjUser objUser = new ObjUser();
        objUser.setId(rs.getInt("id"));
        objUser.setLogin(rs.getString("login"));
        objUser.setPassword(rs.getString("password"));
        return objUser;
    }
}
