package pl.put.poznan.sortingmadness.logic;

import pl.put.poznan.sortingmadness.sorting.SortingStrategy;
import pl.put.poznan.sortingmadness.sorting.algorithms.*;

/**
 * This class is used to convert algorithms choosen by user in JSON request
 * to concrete strategy implementation.
 */
public enum SortingStrategyEnum {
    BUBBLE_SORT(new BubbleSort(), new BubbleSort(true)),
    HEAP_SORT(new HeapSort(), new HeapSort(true)),
    INSERTION_SORT(new InsertionSort(), new InsertionSort(true)),
    MERGE_SORT(new MergeSort(), new MergeSort()),  //todo to implement descending in mergesort
    SELECTION_SORT(new SelectionSort(), new SelectionSort()),  //todo to implement descending in selectionsort
    QUICK_SORT(new QuickSort(), new QuickSort());  //todo to implement descending in quicksort


    /**
     * Strategy implementation with ascending order
     *
     * @return the strategy
     */
    private final SortingStrategy sortingStrategy;
    /**
     * Strategy implementation with descending order
     *
     * @return the strategy
     */
    private final SortingStrategy sortingStrategyDescending;

    SortingStrategyEnum(SortingStrategy sortingStrategy, SortingStrategy sortingStrategyDescending) {
        this.sortingStrategy = sortingStrategy;
        this.sortingStrategyDescending = sortingStrategyDescending;
    }


    public SortingStrategy getSortingStrategy() {
        return sortingStrategy;
    }

    public SortingStrategy getSortingStrategyDescending() {
        return sortingStrategyDescending;
    }
}
