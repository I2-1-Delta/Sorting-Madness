package pl.put.poznan.transformer.logic;

import java.util.List;

public class RestInputIntegers {

    private final List<Integer> toSort;

    private final List<SortingStrategyEnum> sortingStrategies;

    public RestInputIntegers(List<Integer> toSort, List<SortingStrategyEnum> sortingStrategies) {
        this.toSort = toSort;
        this.sortingStrategies = sortingStrategies;
    }

    public List<Integer> getToSort() {
        return toSort;
    }

    public List<SortingStrategyEnum> getSortingStrategies() {
        return sortingStrategies;
    }
}
