package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Category;
import at.spengergasse.bookecho.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Character> {

    private static final String VALID_DB_VALUES = "'W', 'C', 'R'";
    public static final String COLUMN_DEFINITION = "enum (" + VALID_DB_VALUES + ")";

    @Override
    public Character convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return switch (category) {
            case WANT_TO_READ -> 'W';
            case CURRENTLY_READING -> 'C';
            case READ -> 'R';
        };
    }

    @Override
    public Category convertToEntityAttribute(Character dbData) {

        if(dbData == null){
            return null;
        }
        return switch (dbData) {
            case 'W','w' -> Category.WANT_TO_READ;
            case 'C','c'-> Category.CURRENTLY_READING;
            case 'R','r' -> Category.READ;
            default -> throw DataQualityException.forInvalidDbValueCategory(dbData, Category.class, VALID_DB_VALUES);
        };
    }
}
