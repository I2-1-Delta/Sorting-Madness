package pl.put.poznan.sortingmadness.sorting;


import java.util.List;
import pl.put.poznan.sortingmadness.sorting.Sorter;

/**
 * DTO object used to return results to user.
 * Contains name of the algorithm, time that was needed to sort and sorted values.
 * Returned by {@link Sorter}
 */
public class SortingResult<T> {
    /**
     * Name of the algorithm.
     */
    private final String algorithm;
    /**
     * Duration of sorting
     */
    private final Long elapsed;
    /**
     * Sorted values
     */
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
