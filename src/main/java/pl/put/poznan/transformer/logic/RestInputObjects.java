package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.sorting.SortingAlgorithms;

import java.util.List;

public class RestInputIntegers {

    private List<SortingAlgorithms> sortingAlgorithms;

    private List<Integer> toSort;

    public RestInputIntegers(List<SortingAlgorithms> sortingAlgorithms, List<Integer> toSort) {
        this.sortingAlgorithms = sortingAlgorithms;
        this.toSort = toSort;
    }

    public List<SortingAlgorithms> getSortingAlgorithms() {
        return sortingAlgorithms;
    }

    public void setSortingAlgorithms(List<SortingAlgorithms> sortingAlgorithms) {
        this.sortingAlgorithms = sortingAlgorithms;
    }

    public List<Integer> getToSort() {
        return toSort;
    }

    public void setToSort(List<Integer> toSort) {
        this.toSort = toSort;
    }
}
