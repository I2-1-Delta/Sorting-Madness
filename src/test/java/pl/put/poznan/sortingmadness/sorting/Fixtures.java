package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

class Fixtures {
    static final List<Integer> INT_LIST = List.of(1, 7, 3, 5, 4, 6, 9, 8, 10, 2);

    static final List<JsonNode> OBJECT_LIST = generateObjectList();
    static final String PATH = "/sort/key";

    private static List<JsonNode> generateObjectList() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<JsonNode> objects = new ArrayList<>();
        for (int i : INT_LIST) {
            String json = String.format("{\"name\": \"object\", \"sort\": {\"key\": %d}}", i);
            try {
                objects.add(mapper.readTree(json));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }

    static JsonNode toJsonNode(String json) {
        try {
            return new ObjectMapper().readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
