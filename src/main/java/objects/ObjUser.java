package objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Denis on 21.02.2017.
 */
public class ObjUser {
    private Integer id;
    private String login;
    private String newlogin;
    private String password;
    private String newpassword;
    private Integer rating;

    public ObjUser() {

    }

    @JsonCreator
    public ObjUser(
            @JsonProperty("id") Integer id,
            @JsonProperty("login") String login,
            @JsonProperty("newlogin") String newlogin,
            @JsonProperty("password") String password,
            @JsonProperty("newpassword") String newpassword,
            @JsonProperty("rating") Integer rating){
        this.id = id;
        this.login = login;
        this.newlogin = newlogin;
        this.password = password;
        this.newpassword = newpassword;
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNewlogin() {
        return newlogin;
    }

    public String getPassword() {
        return password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public void setNewlogin(String newlogin) {
        this.newlogin = newlogin;
    }

    public JSONObject getJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("login", login);
        jsonObject.put("rating", rating);
        //jsonObject.put("password", password);
        return jsonObject;
    }
}
