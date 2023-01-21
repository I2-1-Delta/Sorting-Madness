package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements SortingStrategy {
    private final boolean descending;
    private  int limit;

    @Override
    public String getName() {
        return "Heap sort";
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public HeapSort() {
        this(false, 0);
    }
    public HeapSort(boolean descending, int limit) {
        this.descending = descending;
        this.limit = limit;
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
    private boolean compare(JsonNodeComparator comparator, JsonNode first, JsonNode second) {
        if (descending) {
            return comparator.compare(first, second) < 0;
        }
        return comparator.compare(first, second) > 0;
    }
    private void heapify(List<Integer> arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && compare(arr.get(l), arr.get(largest)))
            largest = l;
        if (r < N && compare(arr.get(r), arr.get(largest)))
            largest = r;
        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, N, largest);
        }
    }
    private void heapify(List<JsonNode> arr, int N, int i, JsonNodeComparator comparator) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && compare(comparator, arr.get(l), arr.get(largest)))
            largest = l;
        if (r < N && compare(comparator, arr.get(r), arr.get(largest)))
            largest = r;
        if (largest != i) {
            JsonNode swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, N, largest, comparator);
        }
    }
    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        int numOfIterations = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            heapify(result, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
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
        int numOfIterations = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            heapify(result, n, i, comparator);
        }
        for (int i = n - 1; i >= 0; i--) {
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                break;
            }
            JsonNode temp = result.get(0);
            result.set(0, result.get(i));
            result.set(i, temp);
            heapify(result, i, 0, comparator);
        }
        return result;
    }
}
