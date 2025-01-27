package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Genre;
import at.spengergasse.bookecho.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {

    private static final String VALID_DB_VALUES = "'SF', 'F', 'MY', 'TH', 'R', 'HFI', 'B', 'SH', 'H', 'SCI'";
    public static final String COLUMN_DEFINITION = "enum (" + VALID_DB_VALUES + ")";

    @Override
    public String convertToDatabaseColumn(Genre genre) {

        if (genre == null) {
            return null;
        }
        return switch(genre){
                case SCI_FI -> "SF";
                case FANTASY -> "F";
                case MYSTERY -> "MY";
                case THRILLER -> "TH";
                case ROMANCE -> "R";
                case HISTORICAL_FICTION -> "HFI";
                case BIOGRAPHY -> "B";
                case SELF_HELP -> "SH";
                case HISTORY -> "H";
                case SCIENCE -> "SCI";
        };
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return switch(dbData.toUpperCase()){
            case "SF"-> Genre.SCI_FI;
            case "F"-> Genre.FANTASY;
            case "MY" -> Genre.MYSTERY;
            case "TH" -> Genre.THRILLER;
            case "R"-> Genre.ROMANCE;
            case "HFI" -> Genre.HISTORICAL_FICTION;
            case "B"-> Genre.BIOGRAPHY;
            case "SH" -> Genre.SELF_HELP;
            case "H" -> Genre.HISTORY;
            case "SCI" -> Genre.SCIENCE;

            default -> throw DataQualityException.forInvalidDbValue(dbData, Genre.class, VALID_DB_VALUES);
        };
    }
}
