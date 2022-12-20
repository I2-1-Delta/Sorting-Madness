package pl.put.poznan.sortingmadness.logic;

import pl.put.poznan.sortingmadness.sorting.SortingStrategy;
import pl.put.poznan.sortingmadness.sorting.algorithms.*;

public enum SortingStrategyEnum {
    BUBBLE_SORT(new BubbleSort()),
    HEAP_SORT(new HeapSort()),
    INSERTION_SORT(new InsertionSort()),
    MERGE_SORT(new MergeSort()),
    SELECTION_SORT(new SelectionSort()),
    QUICK_SORT(new QuickSort());  //TODO quick sort to be implemented


    private final SortingStrategy sortingStrategy;

    SortingStrategyEnum(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }


    public SortingStrategy getSortingStrategy() {
        return sortingStrategy;
    }
}
