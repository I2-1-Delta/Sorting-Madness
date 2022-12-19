package pl.put.poznan.sortingmadness.sorting.algorithms;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.JsonNodeComparator;
import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortingStrategy {

    @Override
    public String getName() {
        return "Merge sort";
    }

    @Override
    public List<Integer> sort(List<Integer> toSort) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int center;

        if (toSort.size() == 1) {
            return toSort;
        } else {
            center = toSort.size() / 2;
            for (int i = 0; i < center; i++) {
                left.add(toSort.get(i));
            }

            for (int i = center; i < toSort.size(); i++) {
                right.add(toSort.get(i));
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
            if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
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

        if (toSort.size() == 1) {
            return toSort;
        } else {
            center = toSort.size() / 2;
            for (int i = 0; i < center; i++) {
                left.add(toSort.get(i));
            }

            for (int i = center; i < toSort.size(); i++) {
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
            if ((comparator.compare(left.get(leftIndex), right.get(rightIndex)) < 0)) {
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
