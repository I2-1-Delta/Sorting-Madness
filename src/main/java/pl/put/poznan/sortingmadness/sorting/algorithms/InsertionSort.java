package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingStrategy {
    private final boolean descending;
    private final int limit;

    public InsertionSort() {
        this(false, 0);
    }

    public InsertionSort(boolean descending, int limit) {
        this.descending = descending;
        this.limit = limit;
    }
    @Override
    public String getName() {
        return "Insertion sort";
    }

    private boolean compare(Integer first, Integer second) {
        if (descending) {
            return first < second;
        }
        return first > second;
    }
    private boolean overLimit(int numOfIterations) {
        return limit != 0 && numOfIterations == limit;
    }
    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 1; i < n; i++) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            int temp = result.get(i);
            int j = i - 1;
            while (j >= 0 && compare(result.get(j), temp)){
                result.set(j + 1, result.get(j));
                j = j - 1;
            }
            result.set(j + 1, temp);
        }
        return result;
    }
    private boolean compare(JsonNodeComparator comparator, JsonNode first, JsonNode second) {
        if (descending) {
            return comparator.compare(first, second) < 0;
        }
        return comparator.compare(first, second) > 0;
    }
    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 1; i < n; i++) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            JsonNode temp = result.get(i);
            int j = i - 1;
            while (j >= 0 && compare(comparator, result.get(j), temp)) {
                result.set(j + 1, result.get(j));
                j = j - 1;
            }
            result.set(j + 1, temp);
        }
        return result;
    }
}
