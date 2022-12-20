package pl.put.poznan.sortingmadness.sorting;

public class NoSortingAlgorithmSelected extends RuntimeException {
    @Override
    public String getMessage() {
        return "No sorting algorithm selected";
    }
}
