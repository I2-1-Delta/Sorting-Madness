package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements SortingStrategy {
    private final boolean descending;
    private int limit;

    public SelectionSort() {
        this(false, 0);
    }

    public SelectionSort(boolean descending, int limit) {
        this.descending = descending;
        this.limit = limit;
    }

    public SelectionSort(boolean descending) {
        this(descending, 0);
    }

    @Override
    public String getName() {
        return "Selection sort";
    }

    @Override
    public void setLimit(int limit) { this.limit = limit; }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            for (int j = i + 1; j < n; j++) {
                if (compare(result.get(j), result.get(minIndex))) {
                    minIndex = j;
                }
            }
            int temp1 = result.get(minIndex);
            int temp2 = result.get(i);
            result.set(minIndex, temp2);
            result.set(i, temp1);
        }
        return result;
    }

    private boolean compare(Integer first, Integer second) {
        if (descending) {
            return first > second;
        }
        return first < second;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            for (int j = i + 1; j < n; j++) {
                if (compare(comparator, result.get(minIndex), result.get(j))) {
                    minIndex = j;
                }
            }
            JsonNode temp1 = result.get(minIndex);
            JsonNode temp2 = result.get(i);
            result.set(minIndex, temp2);
            result.set(i, temp1);
        }
        return result;
    }

    private boolean compare(JsonNodeComparator comparator, JsonNode first, JsonNode second) {
        if (descending) {
            return comparator.compare(first, second) < 0;
        }
        return comparator.compare(first, second) > 0;
    }

    private boolean overLimit(int numOfIterations) {
        return limit != 0 && numOfIterations == limit;
    }
}
