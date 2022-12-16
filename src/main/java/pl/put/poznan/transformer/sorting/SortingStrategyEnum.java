package pl.put.poznan.transformer.sorting;

import pl.put.poznan.transformer.sorting.algorithms.*;

public enum SortingStrategyEnum {
    BUBBLE_SORT(new BubbleSort()),
    HEAP_SORT(new HeapSort()),
    INSERTION_SORT(new InsertionSort()),
    MERGE_SORT(new MergeSort()),
    SELECTION_SORT(new SelectionSort()),
    QUICK_SORT(null);  //TODO quick sort to be implemented


    private final SortingStrategy sortingStrategy;

    SortingStrategyEnum(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }


    public SortingStrategy getSortingStrategy(){
        return sortingStrategy;
    }
}
