package pl.put.poznan.transformer.sorting;

public class NoSortingAlgorithmSelected extends RuntimeException {
    @Override
    public String getMessage() {
        return "No sorting algorithm selected";
    }
}
