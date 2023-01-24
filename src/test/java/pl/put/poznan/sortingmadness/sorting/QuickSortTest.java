package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.sorting.algorithms.QuickSort;

import java.util.Comparator;
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

    @Test
    void shouldSortIntegersInDescendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new QuickSort(true, 0);
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldSortObjectsInDescendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new QuickSort(true, 0);
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH).reversed());
    }

    @Test
    void shouldNotThrowIndexOutOfBoundWhenLastItemIsTheBigger() {
        JsonNode object1 = Fixtures.toJsonNode("{\"name\": \"object\", \"sort\": {\"key\": 1}}");
        JsonNode object2 = Fixtures.toJsonNode("{\"name\": \"object\", \"sort\": {\"key\": 2}}");
        JsonNode object3 = Fixtures.toJsonNode("{\"name\": \"object\", \"sort\": {\"key\": 3}}");
        List<JsonNode> objects = List.of(object2, object1, object3);
        SortingStrategy sortingStrategy = new QuickSort();
        List<JsonNode> result = sortingStrategy.sort(objects, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH));
    }

    @Test
    void shouldStopAlgorithmWhenOverLimit() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new QuickSort(false, 1);
        List<Integer> result = sortingStrategy.sort(integers);
        List<Integer> sortedWithoutLimit = new QuickSort(false, 0).sort(integers);
        assertThat(result).doesNotContainSequence(sortedWithoutLimit);
    }
}