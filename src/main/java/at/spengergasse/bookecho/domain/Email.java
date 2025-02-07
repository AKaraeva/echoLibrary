package at.spengergasse.bookecho.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Embeddable
public record Email(String email) {

    public static final int length = 64;
    public static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    public static final Predicate<String> isValidEmail = pattern.asMatchPredicate();
    public Email {
        Objects.requireNonNull(email);
        if(!isValidEmail.test(email)) throw new IllegalArgumentException("Invalid email address: " + email);
        }
}
