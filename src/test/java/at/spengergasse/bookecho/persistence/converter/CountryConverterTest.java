package at.spengergasse.bookecho.persistence.converter;
import at.spengergasse.bookecho.domain.Country;
import at.spengergasse.bookecho.persistence.exception.DataQualityException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CountryConverterTest {
    private CountryConverter converter = new CountryConverter();

    @Nested
    class While_converting_valid_values {
        @Test
        void can_convert_enum_constants_to_db_value() {
            assertThat(converter.convertToDatabaseColumn(Country.AUSTRIA)).isEqualTo("AT");
            assertThat(converter.convertToDatabaseColumn(Country.FRANCE)).isEqualTo("FR");
            assertThat(converter.convertToDatabaseColumn(Country.GERMANY)).isEqualTo("DE");
            assertThat(converter.convertToDatabaseColumn(Country.JAPAN)).isEqualTo("JP");
            assertThat(converter.convertToDatabaseColumn(Country.KYRGYZSTAN)).isEqualTo("KG");
            assertThat(converter.convertToDatabaseColumn(Country.RUSSIA)).isEqualTo("RU");
            assertThat(converter.convertToDatabaseColumn(Country.USA)).isEqualTo("US");
        }

        @ParameterizedTest
        @MethodSource
        void can_convert_valid_db_value_to_enum_constant(String dbValue, Country expectedEnumConstant) {
            assertThat(converter.convertToEntityAttribute(dbValue)).isEqualTo(expectedEnumConstant);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_constant() {
            return Stream.of(
                    Arguments.of("AT", Country.AUSTRIA),
                    Arguments.of("FR", Country.FRANCE),
                    Arguments.of("DE", Country.GERMANY),
                    Arguments.of("JP", Country.JAPAN),
                    Arguments.of("KG", Country.KYRGYZSTAN),
                    Arguments.of("RU", Country.RUSSIA),
                    Arguments.of("US", Country.USA));
        }
    }

    @Nested
    class While_converting_null_values {
        @Test
        void can_convert_null_references_safely_to_db_value() {
            assertThat(converter.convertToDatabaseColumn(null)).isNull();
        }

        @Test
        void can_convert_db_value_safely_to_reference() {
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }
    }

    @Test
    void correctly_handle_invalid_db_value() {
        assertThatThrownBy(() -> converter.convertToEntityAttribute("c")).isInstanceOf(DataQualityException.class);
    }
}