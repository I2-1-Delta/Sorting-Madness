package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements SortingStrategy {
    @Override
    public String getName() {
        return "Selection sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result.get(j) < result.get(minIndex)) {
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

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(result.get(minIndex), result.get(j)) > 0) {
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
}
