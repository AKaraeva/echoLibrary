package at.spengergasse.bookecho.domain;
import lombok.Builder;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.function.Predicate;

@Builder
public record Lastname(String value) {
    public static final int MAX_LENGTH = 64;
    public static final Pattern PATTERN = Pattern.compile("^\\p{IsAlphabetic}[\\p{IsAlphabetic}-\\. ]{0,%d}+$".formatted(MAX_LENGTH - 1));
    public static final Predicate<String> isValidLastname = PATTERN.asMatchPredicate();

    public Lastname {
        Objects.requireNonNull(value);
        if(!isValidLastname.test(value)) throw new IllegalArgumentException("Invalid lastname: " + value);
    }

    public static Lastname of(String value) { return new Lastname(value); }
}