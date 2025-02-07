package at.spengergasse.bookecho.dtos;

import at.spengergasse.bookecho.domain.Firstname;
import at.spengergasse.bookecho.domain.Lastname;
import lombok.Data;

@Data
public class UserDtos {
    private Firstname firstName;
    private Lastname lastName;
    private String email;
    private String password;

}
