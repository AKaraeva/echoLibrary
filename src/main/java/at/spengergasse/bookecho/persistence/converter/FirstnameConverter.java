package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Firstname;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FirstnameConverter implements AttributeConverter<Firstname, String> {

    @Override
    public String convertToDatabaseColumn(Firstname attribute) {
        return (attribute == null) ? null : attribute.value();
    }

    @Override
    public Firstname convertToEntityAttribute(String dbData) {
     return (dbData == null) ? null : new Firstname(dbData);
    }
}