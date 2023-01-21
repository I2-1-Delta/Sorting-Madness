package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortingStrategy {
    private final boolean descending;
    private int limit;
    private int numOfIterations = 0;
    public MergeSort() {
        this(false);
    }
    public MergeSort(boolean descending) {
        this(descending, 0);
    }
    public MergeSort(boolean descending, int limit) {
        this.descending = descending;
        this.limit = limit;
    }
    @Override
    public String getName() {
        return "Merge sort";
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }
    private boolean compare(Integer first, Integer second) {
        if (descending) {
            return first < second;
        }
        return first > second;
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
    @Override
    public List<Integer> sort(List<Integer> toSort) {
        int center = toSort.size() / 2;

        if (toSort.size() == 1) {
            return toSort;
        } else {
            List<Integer> left = toSort.subList(0, center);
            List<Integer> right = toSort.subList(center, toSort.size());
            numOfIterations++;
            if (overLimit(numOfIterations)) {
                return toSort;
            }
            left = sort(left);
            right = sort(right);

            toSort = mergeInteger(left, right, toSort);
        }
        return toSort;
    }
    private List<Integer> mergeInteger(List<Integer> left, List<Integer> right, List<Integer> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        whole = new ArrayList<>(whole);

        while (leftIndex < left.size() && rightIndex < right.size()) {

            if (compare(right.get(rightIndex), left.get(leftIndex))) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        List<Integer> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }

        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
        return whole;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> left = new ArrayList<>();
        List<JsonNode> right = new ArrayList<>();
        int center;
        int numOfIterations = 0;
        if (toSort.size() == 1) {
            return toSort;
        } else {
            center = toSort.size() / 2;
            for (int i = 0; i < center; i++) {
                numOfIterations++;
                if (overLimit(numOfIterations)) {
                    break;
                }
                left.add(toSort.get(i));
            }

            for (int i = center; i < toSort.size(); i++) {
                numOfIterations++;
                if (overLimit(numOfIterations)) {
                    break;
                }
                right.add(toSort.get(i));
            }

            left = sort(left, path);
            right = sort(right, path);

            toSort = mergeObjects(left, right, toSort, path);
        }
        return toSort;
    }

    private List<JsonNode> mergeObjects(List<JsonNode> left, List<JsonNode> right, List<JsonNode> whole, String path) {

        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        whole = new ArrayList<>(whole);

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (compare(comparator, right.get(rightIndex), left.get(leftIndex))) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        List<JsonNode> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }

        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
        return whole;
    }
}
