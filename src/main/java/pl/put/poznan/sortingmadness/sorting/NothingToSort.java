package pl.put.poznan.sortingmadness.sorting;

public class NothingToSort extends RuntimeException {
    @Override
    public String getMessage() {
        return "No elements to sort";
    }
}
