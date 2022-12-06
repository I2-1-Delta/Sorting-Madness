package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

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
        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (result.get(j) < result.get(min_idx)) {
                    min_idx = j;
                }
            }
            int temp1 = result.get(min_idx);
            int temp2 = result.get(i);
            result.set(min_idx, temp2);
            result.set(i, temp1);
        }
        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
