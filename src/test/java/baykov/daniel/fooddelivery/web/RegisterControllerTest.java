package baykov.daniel.fooddelivery.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")
                .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .param("username", "ivan")
                .param("email", "ivantest@gmail.com")
                .param("password", "!Ivan123")
                .param("confirmPassword", "!Ivan123")
                .param("age", "17")
                .param("phoneNumber", "0887070707")
                .param("gender", "MALE")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }

    @Test
    void testRegistration_InvalidData() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("firstName", "")
                        .param("lastName", "Baykov")
                        .param("username", "daniel")
                        .param("email", "daniel@gmail.com")
                        .param("password", "!Daniel123")
                        .param("confirmPassword", "!Daniel123")
                        .param("age", "27")
                        .param("phoneNumber", "0887070707")
                        .param("gender", "MALE")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }
}
