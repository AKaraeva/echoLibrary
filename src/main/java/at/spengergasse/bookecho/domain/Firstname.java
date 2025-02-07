package at.spengergasse.bookecho.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.function.Predicate;

@Builder
public record Firstname(String value) {
    public static final int MAX_LENGTH = 64;
    public static final Pattern PATTERN = Pattern.compile("^\\p{IsAlphabetic}[\\p{IsAlphabetic}--\\. ]{0,%d}+$".formatted(MAX_LENGTH - 1));
    public static final Predicate<String> isValidFirstname = PATTERN.asMatchPredicate();

    @JsonCreator
    public static Firstname of(@JsonProperty("firstName") String firstName) {
        Objects.requireNonNull(firstName);
        if (!isValidFirstname.test(firstName))
            throw new IllegalArgumentException(firstName + " is not a valid first name");
        return new Firstname(firstName);
    }

    /*public Firstname {
        Objects.requireNonNull(value);
        if(!isValidFirstname.test(value)) throw new IllegalArgumentException("Invalid firstname: " + value);
    }

    public static Firstname of(String value) { return new Firstname(value); }*/
}
