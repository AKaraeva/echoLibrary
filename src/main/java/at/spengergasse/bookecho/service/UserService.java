package at.spengergasse.bookecho.service;

import at.spengergasse.bookecho.domain.User;
import at.spengergasse.bookecho.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    //exception handling
    public Optional<User> saveUser(User user) {
        return Optional.of(userRepository.save(user));
    }
}
