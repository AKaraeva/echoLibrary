package at.spengergasse.bookecho.presentation.api;

import at.spengergasse.bookecho.FixtureFactory;
import at.spengergasse.bookecho.domain.Firstname;
import at.spengergasse.bookecho.domain.Lastname;
import at.spengergasse.bookecho.domain.User;
import at.spengergasse.bookecho.persistence.UserRepository;
import at.spengergasse.bookecho.service.UserService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRestController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserRestControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSignUp_userExists_shouldReturnBadRequest() throws Exception {
        // Given
        var user = spy(FixtureFactory.user());
        when(userRepository.findByEmail("gdn@spg.at")).thenReturn(Optional.of(user));
        var postBody = """
                {
                    "firstname": "Max",
                    "lastname": "Musterman",
                    "email":"gdn@spg.at",
                    "password": "password"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .with(user("testuser").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\": \"Email is already in use!\"}"))
                .andDo(print());
    }

    @Test
    void testSignUp_successfulRegistration_shouldReturnCreated() throws Exception {
        // Given
        Firstname firstname = new Firstname("Max");
        Lastname lastname = new Lastname("Musterman");
        String email = "new@example.com";
        String password = "password";
        User user = spy(new User(firstname, lastname, email, password));

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        var postBody = """
                {
                    "firstname": "Max",
                    "lastname": "Musterman",
                    "email":"existing@example.com",
                    "password": "password"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .with(user("testuser").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"message\": \"User registered successfully!\"}"))
                .andDo(print());

        verify(userService).saveUser(any(User.class));
    }

    @Test
    void testSignIn_successfulLogin_shouldReturnOk() throws Exception {
        // Given
        var user = spy(FixtureFactory.user());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        var postBody = """
                {
                    "email":"gdn@spg.at",
                    "password": "password"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .with(user("testuser").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"User signed in successfully!\"}"))
                .andDo(print());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}