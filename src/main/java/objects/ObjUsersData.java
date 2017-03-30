package objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

/**
 * Created by andrey on 27.03.17.
 */
public class ObjUsersData {
        private String login;
        private Integer rating;
        private Integer gameCount;
        private Integer gameCountWin;
        private Integer crystalGreen;
        private Integer crystalBlue;
        private Integer crystalRed;
        private Integer crystalPurple;

        public ObjUsersData() {

        }

        @JsonCreator
        public ObjUsersData(
                @JsonProperty("login") String login,
                @JsonProperty("rating") Integer rating,
                @JsonProperty("game_count") Integer gameCount,
                @JsonProperty("game_count_win") Integer gameCountWin,
                @JsonProperty("crystal_green") Integer crystalGreen,
                @JsonProperty("crystal_blue") Integer crystalBlue,
                @JsonProperty("crystal_red") Integer crystalRed,
                @JsonProperty("crystal_purple") Integer crystalPurple) {
            this.login = login;
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

        public String getLogin() {
            return login;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public void setLogin(String login) {
            this.login = login;
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

