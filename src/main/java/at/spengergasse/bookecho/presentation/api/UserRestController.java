package at.spengergasse.bookecho.presentation.api;

import at.spengergasse.bookecho.domain.User;
import at.spengergasse.bookecho.dtos.UserDtos;
import at.spengergasse.bookecho.persistence.UserRepository;
import at.spengergasse.bookecho.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDtos userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \"User signed in successfully!\"}");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserDtos userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Email is already in use!\"}");
        }

        User user = new User(userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \"User registered successfully!\"}");
    }
}
