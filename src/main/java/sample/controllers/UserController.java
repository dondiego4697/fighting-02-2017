package sample.controllers;

import objects.HttpStatus;
import objects.ObjUser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Denis on 21.02.2017.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final String SESSIONKEY = "user";
    //private final String URL = "https://tp-front-end-js-game.herokuapp.com";
    //private final String URL = "http://localhost:63343";
    private final String URL = "*";

    public UserController(JdbcTemplate jdbcTemplate) {
        this.userService = new UserService(jdbcTemplate);
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String loginUser(@RequestBody ObjUser body, HttpSession httpSession) {
        final JSONObject answer = new JSONObject();
        userService.login(body, new UserService.CallbackWithUser() {
            @Override
            public void onSuccess(String status, ObjUser objUser) {
                answer.put("status", status);
                answer.put("user", objUser.getJson());
                httpSession.setAttribute(SESSIONKEY, objUser);
            }

            @Override
            public void onError(String status) {
                answer.put("status", status);
            }
        });
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/signup", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public String registerUser(@RequestBody ObjUser body) {

        final JSONObject answer = new JSONObject();
        userService.register(body, new UserService.Callback() {
            @Override
            public void onSuccess(String status) {
                answer.put("status", status);
            }

            @Override
            public void onError(String status) {
                answer.put("status", status);
            }
        });
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
    public String getUser(HttpSession httpSession) {

        final JSONObject answer = new JSONObject();
        final ObjUser objUser = (ObjUser) httpSession.getAttribute(SESSIONKEY);
        if (objUser != null) {
            answer.put("status", new HttpStatus().getOk());
            answer.put("user", objUser.getJson());
        } else {
            answer.put("status", new HttpStatus().getUnauthorized());
        }
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public String updateUser(@RequestBody ObjUser body,
                             HttpSession httpSession) {
        final JSONObject answer = new JSONObject();
        if (httpSession.getAttribute(SESSIONKEY) != null) {
            userService.update(body, new UserService.CallbackWithUser() {
                @Override
                public void onSuccess(String status, ObjUser objUser) {
                    httpSession.removeAttribute(SESSIONKEY);
                    httpSession.setAttribute(SESSIONKEY, objUser);
                    answer.put("status", status);
                }

                @Override
                public void onError(String status) {
                    answer.put("status", status);
                }
            });
        } else {
            answer.put("status", new HttpStatus().getUnauthorized());
        }
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/updateinfo", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public String updateUserInfo(@RequestBody ObjUser body,
                                 HttpSession httpSession) {
        final JSONObject answer = new JSONObject();
        if (httpSession.getAttribute(SESSIONKEY) != null) {
            userService.updateInfo(body, new UserService.CallbackWithUser() {
                @Override
                public void onSuccess(String status, ObjUser objUser) {
                    final ObjUser objUserS = (ObjUser) httpSession.getAttribute(SESSIONKEY);
                    httpSession.removeAttribute(SESSIONKEY);
                    objUser.setId(objUserS.getId());
                    objUser.setPassword(objUserS.getPassword());
                    httpSession.setAttribute(SESSIONKEY, objUser);
                    answer.put("status", status);
                }

                @Override
                public void onError(String status) {
                    answer.put("status", status);
                }
            });
        } else {
            answer.put("status", new HttpStatus().getUnauthorized());
        }
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/changepass", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String changeUserPass(@RequestBody ObjUser body,
                                 HttpSession httpSession) {
        final JSONObject answer = new JSONObject();
        if (httpSession.getAttribute(SESSIONKEY) != null) {
            userService.changePass(body, new UserService.CallbackWithUser() {
                @Override
                public void onSuccess(String status, ObjUser objUser) {
                    final ObjUser objUserS = (ObjUser) httpSession.getAttribute(SESSIONKEY);
                    httpSession.removeAttribute(SESSIONKEY);
                    objUserS.setPassword(objUser.getNewpassword());
                    httpSession.setAttribute(SESSIONKEY, objUserS);
                    answer.put("status", status);
                }

                @Override
                public void onError(String status) {
                    answer.put("status", status);
                }
            });
        } else {
            answer.put("status", new HttpStatus().getUnauthorized());
        }
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/logout", method = RequestMethod.GET, produces = "application/json")
    public String logoutUser(HttpSession httpSession) {
        final JSONObject answer = new JSONObject();
        if (httpSession.getAttribute(SESSIONKEY) != null) {
            httpSession.removeAttribute(SESSIONKEY);
            answer.put("status", new HttpStatus().getOk());
        } else {
            answer.put("status", new HttpStatus().getBadRequest());
        }
        return answer.toString();
    }

    @CrossOrigin(origins = URL, maxAge = 3600)
    @RequestMapping(path = "/leaders", method = RequestMethod.GET, produces = "application/json")
    public String getLeaders() {
        final JSONObject answer = new JSONObject();
        try {
            answer.put("leaders", userService.getLeaders());
            answer.put("status", new HttpStatus().getOk());
        } catch (JSONException e) {
            answer.put("status", new HttpStatus().getBadRequest());
        }
        return answer.toString();
    }
}
