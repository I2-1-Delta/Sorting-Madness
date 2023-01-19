package pl.put.poznan.sortingmadness.sorting;


import java.util.List;

public class SortingResult<T> {
    private final String algorithm;
    private final Long elapsed;
    private final List<T> result;

    public SortingResult(String algorithm, Long elapsed, List<T> result) {
        this.algorithm = algorithm;
        this.elapsed = elapsed;
        this.result = result;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public List<T> getResult() {
        return result;
    }
}
