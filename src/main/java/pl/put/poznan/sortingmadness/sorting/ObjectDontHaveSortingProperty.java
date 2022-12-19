package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectDontHaveSortingProperty extends RuntimeException {
    private final JsonNode object;
    private final String property;

    public ObjectDontHaveSortingProperty(JsonNode object, String property) {
        this.object = object;
        this.property = property;
    }

    @Override
    public String getMessage() {
        return String.format("Object %s has no property: %s which was chosen for sorting", object, property);
    }
}
