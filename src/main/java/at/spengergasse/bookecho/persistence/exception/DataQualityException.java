package at.spengergasse.bookecho.persistence.exception;

import at.spengergasse.bookecho.domain.Genre;

public class DataQualityException extends RuntimeException {
    private DataQualityException(String message) {
        super(message);
    }

    public static DataQualityException forInvalidDbValue(String invalidValue, Class<Genre> enumClass, String validValues) {
        var msg = "Found an invalid db value (%s) for enum class '%s'! Valid values are: %s".formatted(invalidValue, enumClass.getSimpleName(), validValues);
        return new DataQualityException(msg);
    }
}
