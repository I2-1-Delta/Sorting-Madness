package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface SortingStrategy {
    String getName();

    List<Integer> sort(List<Integer> toSort);

    List<JsonNode> sort(List<JsonNode> toSort, String path);
}
