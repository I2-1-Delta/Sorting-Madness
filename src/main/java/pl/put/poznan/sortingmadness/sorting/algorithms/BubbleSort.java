package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortingStrategy {
    private final int limit;

    public BubbleSort() {
        this(0);
    }

    public BubbleSort(int limit) {
        this.limit = limit;
    }

    @Override
    public String getName() {
        return "Bubble sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 0; i < n - 1; i++) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            for (int j = 0; j < n - i - 1; j++) {
                if (result.get(j) > result.get(j + 1)) {
                    int temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = 0; i < n - 1; i++) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(result.get(j), result.get(j + 1)) > 0) {
                    JsonNode temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    private boolean overLimit(int numOfIterations) {
        return limit != 0 && numOfIterations == limit;
    }
}
