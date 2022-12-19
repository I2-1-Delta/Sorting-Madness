package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class RestInputObjects {

    private final List<JsonNode> toSort;
    private final String property;

    private final List<SortingStrategyEnum> sortingStrategies;

    public RestInputObjects(List<JsonNode> toSort, String property, List<SortingStrategyEnum> sortingStrategies) {
        this.toSort = toSort;
        this.property = property;
        this.sortingStrategies = sortingStrategies;
    }

    public List<JsonNode> getToSort() {
        return toSort;
    }

    public String getProperty() {
        return property;
    }

    public List<SortingStrategyEnum> getSortingStrategies() {
        return sortingStrategies;
    }
}
