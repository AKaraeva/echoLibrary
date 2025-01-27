package at.spengergasse.bookecho.domain;

import lombok.Builder;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.function.Predicate;

@Builder
public record Username(String value) {
    public static final int MAX_LENGTH = 64;
    public static final Pattern PATTERN = Pattern.compile("^[\\p{IsAlphabetic}\\d/\\.]{1,%d}+$".formatted(MAX_LENGTH));
    public static final Predicate<String> isValidUsername = PATTERN.asMatchPredicate();

    public Username {
        Objects.requireNonNull(value);
        if (!isValidUsername.test(value)) throw new IllegalArgumentException("Invalid firstname: " + value);
    }

    public static Username of(String value) { return new Username(value); }
}
