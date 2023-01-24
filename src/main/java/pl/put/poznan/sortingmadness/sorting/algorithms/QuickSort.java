package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort implements SortingStrategy {
    private final boolean descending;
    private int limit;

    public QuickSort() {
        this(false, 0);
    }

    public QuickSort(boolean descending) {
        this(descending, 0);
    }

    public QuickSort(boolean descending, int limit) {
        this.descending = descending;
        this.limit = limit;
    }

    private int listPartition(List<Integer> toSort, int low, int high) {
        int pivot = toSort.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(toSort.get(j), pivot)) {
                i++;
                Collections.swap(toSort, i, j);
            }
        }

        Collections.swap(toSort, i + 1, high);

        return i + 1;
    }

    private boolean compare(Integer first, Integer second) {
        if (descending) {
            return first >= second;
        }
        return first <= second;
    }

    private void quickSortList(List<Integer> toSort, int low, int high, int numOfIteration) {
        if (overLimit(numOfIteration)) {
            return;
        }
        if (low < high) {
            int pivot = listPartition(toSort, low, high);

            quickSortList(toSort, low, pivot - 1, numOfIteration + 1);
            quickSortList(toSort, pivot + 1, high, numOfIteration + 1);
        }
    }

    private int nodePartition(List<JsonNode> toSort, int low, int high, JsonNodeComparator comparator) {
        JsonNode pivot = toSort.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(comparator, toSort.get(j), pivot)) {
                i++;
                Collections.swap(toSort, i, j);
            }
        }

        Collections.swap(toSort, i + 1, high);

        return i + 1;
    }

    private boolean compare(JsonNodeComparator comparator, JsonNode first, JsonNode second) {
        if (descending) {
            return comparator.compare(first, second) >= 0;
        }
        return comparator.compare(first, second) <= 0;
    }

    private void quickSortNodes(List<JsonNode> toSort, int low, int high, JsonNodeComparator comparator, int numOfIterations) {
        if (overLimit(numOfIterations)) {
            return;
        }
        if (comparator.compare(toSort.get(low), toSort.get(high)) < 0) {
            int pivot = nodePartition(toSort, low, high, comparator);

            quickSortNodes(toSort, low, pivot - 1, comparator, numOfIterations);
            quickSortNodes(toSort, pivot + 1, high, comparator, numOfIterations);
        }
    }

    @Override
    public String getName() {
        return "Quick sort";
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();

        quickSortList(result, 0, n - 1, 0);

        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();

        quickSortNodes(result, 0, n - 1, comparator, 0);

        return result;
    }

    private boolean overLimit(int numOfIterations) {
        return limit != 0 && numOfIterations == limit;
    }
}
