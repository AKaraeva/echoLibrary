package at.spengergasse.bookecho.persistence.converter;

import at.spengergasse.bookecho.domain.Genre;
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
class GenreConverterTest {
    private GenreConverter converter = new GenreConverter();

    @Nested
    class While_converting_valid_values {
        @Test
        void can_convert_enum_constants_to_db_value() {
            assertThat(converter.convertToDatabaseColumn(Genre.SCI_FI)).isEqualTo("SF");
            assertThat(converter.convertToDatabaseColumn(Genre.FANTASY)).isEqualTo("F");
            assertThat(converter.convertToDatabaseColumn(Genre.HISTORY)).isEqualTo("H");
            assertThat(converter.convertToDatabaseColumn(Genre.THRILLER)).isEqualTo("TH");
            assertThat(converter.convertToDatabaseColumn(Genre.ROMANCE)).isEqualTo("R");
            assertThat(converter.convertToDatabaseColumn(Genre.HISTORICAL_FICTION)).isEqualTo("HFI");
            assertThat(converter.convertToDatabaseColumn(Genre.MYSTERY)).isEqualTo("MY");
            assertThat(converter.convertToDatabaseColumn(Genre.BIOGRAPHY)).isEqualTo("B");
            assertThat(converter.convertToDatabaseColumn(Genre.SELF_HELP)).isEqualTo("SH");
            assertThat(converter.convertToDatabaseColumn(Genre.SCIENCE)).isEqualTo("SCI");
        }

        @ParameterizedTest
        @MethodSource
        void can_convert_valid_db_value_to_enum_constant(String dbValue, Genre expectedEnumConstant) {
            assertThat(converter.convertToEntityAttribute(dbValue)).isEqualTo(expectedEnumConstant);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_constant() {
            return Stream.of(
                    Arguments.of("SF", Genre.SCI_FI),
                    Arguments.of("F", Genre.FANTASY),
                    Arguments.of("MY", Genre.MYSTERY),
                    Arguments.of("TH", Genre.THRILLER),
                    Arguments.of("R", Genre.ROMANCE),
                    Arguments.of("HFI", Genre.HISTORICAL_FICTION),
                    Arguments.of("B", Genre.BIOGRAPHY),
                    Arguments.of("SH", Genre.SELF_HELP),
                    Arguments.of("H", Genre.HISTORY),
                    Arguments.of("SCI", Genre.SCIENCE)
            );
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