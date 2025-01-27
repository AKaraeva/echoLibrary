package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Lastname;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LastnameConverter implements AttributeConverter <Lastname, String> {

    @Override
    public String convertToDatabaseColumn(Lastname attribute) {
        return (attribute == null) ? null : attribute.value();
    }

    @Override
    public Lastname convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : new Lastname(dbData);
    }
}
