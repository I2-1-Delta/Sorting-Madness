package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HeapSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers =  Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new HeapSort();
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }

    @Test
    void shouldSortObjectsInAscendingOrder() {
        List<JsonNode> integers = Fixtures.OBJECT_LIST;
        SortingStrategy sortingStrategy = new HeapSort();
        List<JsonNode> result = sortingStrategy.sort(integers, Fixtures.PATH);
        assertThat(result).isSortedAccordingTo(new JsonNodeComparator(Fixtures.PATH));
    }

}