package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class Sorter {
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
