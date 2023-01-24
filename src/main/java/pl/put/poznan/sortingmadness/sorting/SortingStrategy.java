package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sortingmadness.sorting.Sorter;

import java.util.List;

/**
 * Interface used to implement concrete sorting algorithm. Each strategy have
 * possiblity to sort in ascending and descending order. Used by {@link Sorter}
 */ 
public interface SortingStrategy {
    /** 
     * Get human readable name of the algorithm.
     */
    String getName();

    /**
     * Set limit of iterations for given algorithm.
     */
    void setLimit(int limit);

    /**
     * Sort integers
     */
    List<Integer> sort(List<Integer> toSort);

    /**
     * Sort objects by given property.
     */
    List<JsonNode> sort(List<JsonNode> toSort, String path);
}
