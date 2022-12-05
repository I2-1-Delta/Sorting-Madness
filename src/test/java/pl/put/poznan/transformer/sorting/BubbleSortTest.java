package pl.put.poznan.transformer.sorting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BubbleSortTest {
    @Test
    void shouldSortIntegersInAscendingOrder() {
        List<Integer> integers = Fixtures.INT_LIST;
        SortingStrategy sortingStrategy = new BubbleSort();
        List<Integer> result = sortingStrategy.sort(integers);
        assertThat(result).isSorted();
    }
}