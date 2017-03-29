package registration;


import application.Application;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by egor on 28.03.17.
 */

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class RegistrationTest {


    @Autowired
    private MockMvc mockMvc;
    private static Faker faker;

    private String userLogin;
    private String password;

    @Before
    public void setUp() {
        faker = new Faker(new Locale("en-US"));
    }

    @Test
    public void signup200() throws Exception {

        password = faker.internet().password(8, 10);
        userLogin = faker.name().username();

        JSONObject json = new JSONObject();

        json.put("login", userLogin);
        json.put("password", password);

        mockMvc
                .perform(
                        post("/api/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json.toString()))
                .andExpect(jsonPath("status").value("200 OK"));
    }

    @Test
    public void signupShortPassword() throws Exception {
        {
            password = faker.internet().password(0, 7);
            userLogin = faker.name().username();

            JSONObject json = new JSONObject();

            json.put("login", userLogin);
            json.put("password", password);

            mockMvc
                    .perform(
                            post("/api/user/signup")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json.toString()))
                    .andExpect(jsonPath("status").value("403 Forbidden"));
        }
    }

    @Test
    public void signupCirilicCharsInLogin() throws Exception {
        {
            password = faker.internet().password(8, 10);

            userLogin = faker.name().username();

            JSONObject json = new JSONObject();

            json.put("login", userLogin);
            json.put("password", password);

            mockMvc
                    .perform(
                            post("/api/user/signup")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json.toString()))
                    .andExpect(jsonPath("status").value("403 Forbidden"));
        }
    }
}
