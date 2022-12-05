package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

/**
 * A class that uses given types of sorting to sort given values
 * and measures the required time for each sorting method.
 */
public class Sorter {
    /**
     * Sort list of integers with given sorting methods
     *
     * @param toSort            the list to sort
     * @param sortingStrategies the sorting methods
     * @return the sorting result with a map where keys are the names of sorting methods
     * and values are elapsed times in nanoseconds.
     */
    public SortingResult<Integer> sort(List<Integer> toSort, List<SortingStrategy> sortingStrategies) {
        if (toSort.isEmpty()) {
            throw new NothingToSort();
        }
        if (sortingStrategies.isEmpty()) {
            throw new NoSortingAlgorithmSelected();
        }

        List<Integer> sorted = null;
        HashMap<String, Long> elapsed = new HashMap<>();

        for (SortingStrategy sortingStrategy :
                sortingStrategies) {
            Instant start = Instant.now();
            sorted = sortingStrategy.sort(toSort);
            Instant stop = Instant.now();
            long duration = Duration.between(start, stop).toNanos();
            elapsed.put(sortingStrategy.getName(), duration);
        }

        return new SortingResult<>(elapsed, sorted);
    }

    /**
     * Sort list of custom objects with given sorting methods. Path is used to select which property
     * should be used for comparison, e.g. object:
     * <p>
     * <pre>
     * {
     *     "name": "object",
     *     "sort": {
     *         "key": 1
     *     }
     * }
     * </pre>
     * </p>
     * and path: "/sort/key" gives value 1.
     * @param toSort            the list to sort
     * @param sortingStrategies the sorting methods
     * @param path              the key by which the objects should be sorted
     * @return the sorting result with a map where keys are the names of sorting methods
     * and values are elapsed times in nanoseconds.
     */
    public SortingResult<JsonNode> sortObjects(List<JsonNode> toSort, String path, List<SortingStrategy> sortingStrategies) {
        if (toSort.isEmpty()) {
            throw new NothingToSort();
        }
        if (sortingStrategies.isEmpty()) {
            throw new NoSortingAlgorithmSelected();
        }

        List<JsonNode> sorted = null;
        HashMap<String, Long> elapsed = new HashMap<>();

        for (SortingStrategy sortingStrategy :
                sortingStrategies) {
            Instant start = Instant.now();
            sorted = sortingStrategy.sort(toSort, path);
            Instant stop = Instant.now();
            long duration = Duration.between(start, stop).toNanos();
            elapsed.put(sortingStrategy.getName(), duration);
        }

        return new SortingResult<>(elapsed, sorted);
    }
}
