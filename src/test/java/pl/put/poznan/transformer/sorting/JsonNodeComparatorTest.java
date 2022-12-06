package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonNodeComparatorTest {
    private static final String PATH = "/sort/key";

    @Test
    void should_return_negative_integer_when_first_object_has_lower_value_than_the_second() {
        JsonNode firstObject = getObject(1);
        JsonNode secondObject = getObject(2);

        int result = new JsonNodeComparator(PATH).compare(firstObject, secondObject);

        assertThat(result).isNegative();
    }

    @Test
    void should_return_zero_when_first_object_has_equal_value_as_the_second() {
        JsonNode firstObject = getObject(1);
        JsonNode secondObject = getObject(1);

        int result = new JsonNodeComparator(PATH).compare(firstObject, secondObject);

        assertThat(result).isZero();
    }

    @Test
    void should_return_positive_integer_when_first_object_has_greater_value_than_the_second() {
        JsonNode firstObject = getObject(2);
        JsonNode secondObject = getObject(1);

        int result = new JsonNodeComparator(PATH).compare(firstObject, secondObject);

        assertThat(result).isPositive();
    }

    private JsonNode getObject(int value) {
        String json = String.format("{\"name\": \"to test\", \"sort\": {\"key\": %d}}", value);
        try {
            return new ObjectMapper().readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}