package pl.put.poznan.transformer.sorting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SelectionSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers = List.of(1, 5, 3, 6, 4, 2);
        SortingStrategy sortingStrategy = new SelectionSort();
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }
}