package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.sorting.algorithms.BubbleSort;
import pl.put.poznan.transformer.sorting.algorithms.QuickSort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class QuickSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new QuickSort();
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }

    @Test
    void shouldSortObjectsInAscendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new QuickSort();
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH));
    }
}