package pl.put.poznan.sortingmadness.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class RestInputObjects {

    private final List<JsonNode> toSort;
    private final String property;

    private final List<SortingStrategyEnum> sortingStrategies;
    private final List<Integer> iterationLimits;

    public RestInputObjects(List<JsonNode> toSort, String property, List<SortingStrategyEnum> sortingStrategies, List<Integer> iterationLimits) {
        this.toSort = toSort;
        this.property = property;
        this.sortingStrategies = sortingStrategies;
        this.iterationLimits = iterationLimits;
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

    public List<Integer> getIterationLimits() {
        return iterationLimits;
    }
}
