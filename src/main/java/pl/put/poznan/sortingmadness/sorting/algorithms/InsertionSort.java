package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingStrategy {
    private final boolean descending;

    public InsertionSort() {
        this(false);
    }

    public InsertionSort(boolean descending) {
        this.descending = descending;
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
    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        for (int i = 1; i < n; i++) {
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
        for (int i = 1; i < n; i++) {
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
