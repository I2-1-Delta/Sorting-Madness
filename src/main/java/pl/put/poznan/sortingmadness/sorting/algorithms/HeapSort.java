package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements SortingStrategy {
    private static void heapify(List<Integer> arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && arr.get(l) > arr.get(largest))
            largest = l;
        if (r < N && arr.get(r) > arr.get(largest))
            largest = r;
        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, N, largest);
        }
    }

    private static void object_heapify(List<JsonNode> arr, int N, int i, JsonNodeComparator comp) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && comp.compare(arr.get(l), arr.get(largest)) > 0)
            largest = l;
        if (r < N && comp.compare(arr.get(r), arr.get(largest)) > 0)
            largest = r;
        if (largest != i) {
            JsonNode swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            object_heapify(arr, N, largest, comp);
        }
    }

    @Override
    public String getName() {
        return "Heap sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(result, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = result.get(0);
            result.set(0, result.get(i));
            result.set(i, temp);
            heapify(result, i, 0);
        }
        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            object_heapify(result, n, i, comparator);
        }
        for (int i = n - 1; i >= 0; i--) {
            JsonNode temp = result.get(0);
            result.set(0, result.get(i));
            result.set(i, temp);
            object_heapify(result, i, 0, comparator);
        }
        return result;
    }
}
