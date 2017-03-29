package objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import support.Encrypt;

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
    private Integer gameCount;
    private Integer gameCountWin;
    private Integer crystalGreen;
    private Integer crystalBlue;
    private Integer crystalRed;
    private Integer crystalPurple;

    public ObjUser() {

    }

    @JsonCreator
    public ObjUser(
            @JsonProperty("id") Integer id,
            @JsonProperty("login") String login,
            @JsonProperty("newlogin") String newlogin,
            @JsonProperty("password") String password,
            @JsonProperty("newpassword") String newpassword,
            @JsonProperty("rating") Integer rating,
            @JsonProperty("game_count") Integer gameCount,
            @JsonProperty("game_count_win") Integer gameCountWin,
            @JsonProperty("crystal_green") Integer crystalGreen,
            @JsonProperty("crystal_blue") Integer crystalBlue,
            @JsonProperty("crystal_red") Integer crystalRed,
            @JsonProperty("crystal_purple") Integer crystalPurple) {
        this.id = id;
        this.login = login;
        this.newlogin = newlogin;
        this.password = password;
        this.newpassword = newpassword;
        this.rating = rating;
        this.gameCount = gameCount;
        this.gameCountWin = gameCountWin;
        this.crystalGreen = crystalGreen;
        this.crystalBlue = crystalBlue;
        this.crystalRed = crystalRed;
        this.crystalPurple = crystalPurple;
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

    public String getClearPassword() {
        return password;
    }

    public String getPassword() {
        return new Encrypt().get(password);
    }

    public String getNewpassword() {
        return new Encrypt().get(newpassword);
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

    public Integer getCrystalBlue() {
        return crystalBlue;
    }

    public Integer getCrystalGreen() {
        return crystalGreen;
    }

    public Integer getGameCount() {
        return gameCount;
    }

    public Integer getGameCountWin() {
        return gameCountWin;
    }

    public Integer getCrystalPurple() {
        return crystalPurple;
    }

    public Integer getCrystalRed() {
        return crystalRed;
    }

    public void setCrystalBlue(Integer crystalBlue) {
        this.crystalBlue = crystalBlue;
    }

    public void setCrystalGreen(Integer crystalGreen) {
        this.crystalGreen = crystalGreen;
    }

    public void setCrystalPurple(Integer crystalPurple) {
        this.crystalPurple = crystalPurple;
    }

    public void setCrystalRed(Integer crystalRed) {
        this.crystalRed = crystalRed;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    public void setGameCountWin(Integer gameCountWin) {
        this.gameCountWin = gameCountWin;
    }

    public JSONObject getJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("login", login);
        jsonObject.put("rating", rating);
        jsonObject.put("game_count", gameCount);
        jsonObject.put("game_count_win", gameCountWin);
        jsonObject.put("crystal_green", crystalGreen);
        jsonObject.put("crystal_red", crystalRed);
        jsonObject.put("crystal_blue", crystalBlue);
        jsonObject.put("crystal_purple", crystalPurple);
        return jsonObject;
    }
}
