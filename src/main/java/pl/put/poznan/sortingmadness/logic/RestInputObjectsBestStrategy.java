package pl.put.poznan.sortingmadness.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class RestInputObjectsBestStrategy {

    private final List<JsonNode> toSort;
    private final String property;


    public RestInputObjectsBestStrategy(List<JsonNode> toSort, String property) {
        this.toSort = toSort;
        this.property = property;
    }

    public List<JsonNode> getToSort() {
        return toSort;
    }

    public String getProperty() {
        return property;
    }

}
