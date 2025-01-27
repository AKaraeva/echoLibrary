package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Country;
import at.spengergasse.bookecho.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CountryConverter implements AttributeConverter<Country, String> {

    private static final String VALID_DB_VALUES = "'AT', 'FR', 'DE', 'JP', 'KG', 'RU', 'US'";
    public static final String COLUMN_DEFINITION = "enum (" + VALID_DB_VALUES + ")";

    @Override
    public String convertToDatabaseColumn(Country country) {

        if (country == null) {
            return null;
        }
        return switch (country) {
            case AUSTRIA -> "AT";
            case FRANCE -> "FR";
            case GERMANY -> "DE";
            case JAPAN -> "JP";
            case KYRGYZSTAN -> "KG";
            case RUSSIA -> "RU";
            case USA -> "US";
        };
    }

    @Override
    public Country convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return switch (dbData.toUpperCase()) {
            case "AT" -> Country.AUSTRIA;
            case "FR" -> Country.FRANCE;
            case "JP" -> Country.JAPAN;
            case "DE" -> Country.GERMANY;
            case "RU" -> Country.RUSSIA;
            case "KG" -> Country.KYRGYZSTAN;
            case "US" -> Country.USA;

            default -> throw DataQualityException.forInvalidDbValue(dbData, Country.class, VALID_DB_VALUES);
        };
    }
}