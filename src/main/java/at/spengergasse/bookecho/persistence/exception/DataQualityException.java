package at.spengergasse.bookecho.persistence.exception;

import at.spengergasse.bookecho.domain.Category;
import at.spengergasse.bookecho.domain.Country;
import at.spengergasse.bookecho.domain.Genre;

public class DataQualityException extends RuntimeException {
    private DataQualityException(String message) {
        super(message);
    }

    public static DataQualityException forInvalidDbValue(String invalidValue, Class<? extends Enum> enumClass, String validValues) {
        var msg = "Found an invalid db value (%s) for enum class '%s'! Valid values are: %s".formatted(invalidValue, enumClass.getSimpleName(), validValues);
        return new DataQualityException(msg);
    }

    public static DataQualityException forInvalidDbValueCategory(char invalidValue, Class<Category> enumClass, String validValues) {
        var msg = "Found an invalid db value (%c) for enum class '%s'! Valid values are: %s".formatted(invalidValue, enumClass.getSimpleName(), validValues);
        return new DataQualityException(msg);
    }
}
