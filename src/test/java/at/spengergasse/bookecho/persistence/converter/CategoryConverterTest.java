package at.spengergasse.bookecho.persistence.converter;
import at.spengergasse.bookecho.domain.*;
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
class CategoryConverterTest {
    private CategoryConverter converter = new CategoryConverter();

    @Nested
    class While_converting_valid_values {
        @Test
        void can_convert_enum_constants_to_db_value() {
            assertThat(converter.convertToDatabaseColumn(Category.WANT_TO_READ)).isEqualTo('W');
            assertThat(converter.convertToDatabaseColumn(Category.CURRENTLY_READING)).isEqualTo('C');
            assertThat(converter.convertToDatabaseColumn(Category.READ)).isEqualTo('R');
        }
    }

        @ParameterizedTest
        @MethodSource
        void can_convert_valid_db_value_to_enum_constant(Character dbValue, Category expectedEnumConstant) {
            assertThat(converter.convertToEntityAttribute(dbValue)).isEqualTo(expectedEnumConstant);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_constant() {
            return Stream.of(
                    Arguments.of('W', Category.WANT_TO_READ),
                    Arguments.of('C', Category.CURRENTLY_READING),
                    Arguments.of('R', Category.READ));
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

        @Test
        void correctly_handle_invalid_db_value() {
            assertThatThrownBy(() -> converter.convertToEntityAttribute('x')).isInstanceOf(DataQualityException.class);
        }
    }
}