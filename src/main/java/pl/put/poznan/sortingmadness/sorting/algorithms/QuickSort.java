package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort implements SortingStrategy {

    private int limit;

    public QuickSort() {
        this(0);
    }

    public QuickSort(int limit) {
        this.limit = limit;
    }

    private int listPartition(List<Integer> toSort, int low, int high) {
        int pivot = toSort.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (toSort.get(j) <= pivot) {
                i++;
                Collections.swap(toSort, i, j);
            }
        }

        Collections.swap(toSort, i + 1, high);

        return i + 1;
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
            if (comparator.compare(toSort.get(j), pivot) <= 0) {
                i++;
                Collections.swap(toSort, i, j);
            }
        }

        Collections.swap(toSort, i + 1, high);

        return i + 1;
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
