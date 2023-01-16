package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.sorting.algorithms.BubbleSort;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BubbleSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new BubbleSort(false);
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }

    @Test
    void shouldSortObjectsInAscendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new BubbleSort(false);
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH));
    }

    @Test
    void shouldSortIntegersInDescendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new BubbleSort(true);
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldSortObjectsInDescendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new BubbleSort(true);
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH).reversed());
    }
}