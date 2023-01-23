package pl.put.poznan.sortingmadness.logic;

import java.util.List;

public class RestInputIntegers {

    private final List<Integer> toSort;

    private final List<SortingStrategyEnum> sortingStrategies;

    private final  List<Integer> iterationLimits;

    public RestInputIntegers(List<Integer> toSort, List<SortingStrategyEnum> sortingStrategies, List<Integer> iterationLimits) {
        this.toSort = toSort;
        this.sortingStrategies = sortingStrategies;
        this.iterationLimits = iterationLimits;
    }

    public List<Integer> getToSort() {
        return toSort;
    }

    public List<SortingStrategyEnum> getSortingStrategies() {
        return sortingStrategies;
    }

    public List<Integer> getIterationLimits() {
        return iterationLimits;
    }
}
