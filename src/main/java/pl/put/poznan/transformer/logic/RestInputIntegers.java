package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.sorting.SortingStrategyEnum;

import java.util.List;

public class RestInputIntegers {

    private List<Integer> toSort;

    private List<SortingStrategyEnum> sortingStrategies;

    public RestInputIntegers(List<Integer> toSort, List<SortingStrategyEnum> sortingStrategies) {
        this.toSort = toSort;
        this.sortingStrategies = sortingStrategies;
    }

    public List<Integer> getToSort() {
        return toSort;
    }

    public void setToSort(List<Integer> toSort) {
        this.toSort = toSort;
    }

    public List<SortingStrategyEnum> getSortingStrategies() {
        return sortingStrategies;
    }

    public void setSortingStrategies(List<SortingStrategyEnum> sortingStrategies) {
        this.sortingStrategies = sortingStrategies;
    }
}
