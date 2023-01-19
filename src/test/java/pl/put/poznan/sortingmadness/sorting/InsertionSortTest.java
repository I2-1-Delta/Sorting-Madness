package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.sorting.algorithms.InsertionSort;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class InsertionSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new InsertionSort();
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }

    @Test
    void shouldSortObjectsInAscendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new InsertionSort();
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH));
    }

    @Test
    void shouldSortIntegersInDescendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new InsertionSort(true, 0);
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldSortObjectsInDescendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new InsertionSort(true, 0);
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH).reversed());
    }

    @Test
    void shouldStopAlgorithmWhenOverLimit() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new InsertionSort(false, 1);
        List<Integer> result = sortingStrategy.sort(integers);
        List<Integer> sortedWithoutLimit = new InsertionSort(false, 0).sort(integers);
        assertThat(result).doesNotContainSequence(sortedWithoutLimit);
    }
}