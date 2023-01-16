package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortingStrategy {
    private final boolean descending;

    public BubbleSort() {
        this(false);
    }

    public BubbleSort(boolean descending) {
        this.descending = descending;
    }

    @Override
    public String getName() {
        return "Bubble sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(result.get(j), result.get(j + 1))) {
                    int temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    private boolean compare(Integer first, Integer second) {
        if (descending) {
            return first < second;
        }
        return first > second;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(comparator, result.get(j), result.get(j + 1))) {
                    JsonNode temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    private boolean compare(JsonNodeComparator comparator, JsonNode first, JsonNode second) {
        if (descending) {
            return comparator.compare(first, second) < 0;
        }
        return comparator.compare(first, second) > 0;
    }
}
