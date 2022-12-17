package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class RestInputObjects {

    private List<JsonNode> toSort;

    private List<SortingStrategyEnum> sortingStrategies;

    public RestInputObjects(List<JsonNode> toSort, List<SortingStrategyEnum> sortingStrategies) {
        this.toSort = toSort;
        this.sortingStrategies = sortingStrategies;
    }

    public List<JsonNode> getToSort() {
        return toSort;
    }

    public void setToSort(List<JsonNode> toSort) {
        this.toSort = toSort;
    }

    public List<SortingStrategyEnum> getSortingStrategies() {
        return sortingStrategies;
    }

    public void setSortingStrategies(List<SortingStrategyEnum> sortingStrategies) {
        this.sortingStrategies = sortingStrategies;
    }
}
