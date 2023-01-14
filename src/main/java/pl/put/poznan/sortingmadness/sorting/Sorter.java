package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that uses given types of sorting to sort given values
 * and measures the required time for each sorting method.
 */
public class Sorter {
    private static final Logger log = LoggerFactory.getLogger(Sorter.class);

    /**
     * Sort list of integers with given sorting methods
     *
     * @param toSort            the list to sort
     * @param sortingStrategies the sorting methods
     * @return list of the sorting results with the name of sorting method
     * and elapsed time in nanoseconds.
     */
    public List<SortingResult<Integer>> sort(List<Integer> toSort, List<SortingStrategy> sortingStrategies) {
        if (toSort.isEmpty()) {
            throw new NothingToSort();
        }
        if (sortingStrategies.isEmpty()) {
            throw new NoSortingAlgorithmSelected();
        }

        List<SortingResult<Integer>> results = new ArrayList<>();
        for (SortingStrategy sortingStrategy :
                sortingStrategies) {
            log.info("Sorting integers using algorithm {}", sortingStrategy.getName());
            Instant start = Instant.now();
            List<Integer> sorted = sortingStrategy.sort(toSort);
            Instant stop = Instant.now();
            long duration = Duration.between(start, stop).toNanos();
            log.debug("Run {} algorithm for integers in {} ns", sortingStrategy.getName(), duration);
            results.add(new SortingResult<>(sortingStrategy.getName(), duration, sorted));
        }

        return results;
    }

    /**
     * Sort list of custom objects with given sorting methods. Path is used to select which property
     * should be used for comparison, e.g. object:
     * <pre>
     * {
     *     "name": "object",
     *     "sort": {
     *         "key": 1
     *     }
     * }
     * </pre>
     * and path: "/sort/key" gives value 1.
     *
     * @param toSort            the list to sort
     * @param sortingStrategies the sorting methods
     * @param path              the key by which the objects should be sorted
     * @return list of the sorting results with the name of sorting method
     * and elapsed time in nanoseconds.
     */
    public List<SortingResult<JsonNode>> sortObjects(List<JsonNode> toSort, String path, List<SortingStrategy> sortingStrategies) {
        if (toSort.isEmpty()) {
            throw new NothingToSort();
        }
        if (sortingStrategies.isEmpty()) {
            throw new NoSortingAlgorithmSelected();
        }

        List<SortingResult<JsonNode>> results = new ArrayList<>();
        for (SortingStrategy sortingStrategy :
                sortingStrategies) {
            log.info("Sorting objects using algorithm {}", sortingStrategy.getName());
            Instant start = Instant.now();
            List<JsonNode> sorted = sortingStrategy.sort(toSort, path);
            Instant stop = Instant.now();
            long duration = Duration.between(start, stop).toNanos();
            log.debug("Run {} algorithm for objects in {} ns", sortingStrategy.getName(), duration);
            results.add(new SortingResult<>(sortingStrategy.getName(), duration, sorted));
        }

        return results;
    }
}
