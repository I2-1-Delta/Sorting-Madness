package pl.put.poznan.transformer.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.transformer.sorting.JsonNodeComparator;
import pl.put.poznan.transformer.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortingStrategy {

    @Override
    public String getName() {
        return "Merge sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
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
}
