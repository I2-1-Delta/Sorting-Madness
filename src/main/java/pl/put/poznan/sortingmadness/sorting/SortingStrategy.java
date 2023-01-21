package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface SortingStrategy {
    String getName();

    void setLimit(int limit);

    List<Integer> sort(List<Integer> toSort);

    List<JsonNode> sort(List<JsonNode> toSort, String path);
}
