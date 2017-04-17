package registration;

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
import sample.Application;

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
        password = faker.internet().password(8, 10);
        userLogin = faker.name().username();
    }

    @Test
    public void signup200() throws Exception {

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
}
