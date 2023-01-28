package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Stubber;
import pl.put.poznan.sortingmadness.logic.SortingMadnessLogic;
import pl.put.poznan.sortingmadness.sorting.algorithms.BubbleSort;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SorterTest {

    private final Clock clock = mock(Clock.class);
    private final Sorter sorter = new Sorter(clock);

    @Test
    void shouldSortIntegersInAscendingOrder() {
        Instant start = Instant.now();
        Instant stop = start.plusNanos(1000);
        when(clock.instant())
                .thenReturn(start)
                .thenReturn(stop);

        List<SortingResult<Integer>> result = sorter.sort(List.of(3, 1, 2), List.of(new BubbleSort()));

        Optional<SortingResult<Integer>> bubbleSortResultOpt = result.stream()
                .filter(res -> "Bubble sort".equals(res.getAlgorithm()))
                .findAny();
        assertThat(bubbleSortResultOpt).isPresent();
        SortingResult<Integer> bubbleSortResult = bubbleSortResultOpt.get();
        assertThat(bubbleSortResult.getResult()).containsSequence(1, 2, 3);
    }

    @Test
    void shouldMeasureTimeForIntegersCorrectlyUsingClock() {
        Instant start = Instant.now();
        Instant stop = start.plusNanos(1000);
        when(clock.instant())
                .thenReturn(start)
                .thenReturn(stop);

        List<SortingResult<Integer>> result = sorter.sort(List.of(3, 1, 2), List.of(new BubbleSort()));

        SortingResult<Integer> bubbleSortResult = result.stream()
                .filter(res -> "Bubble sort".equals(res.getAlgorithm()))
                .findAny()
                .orElseThrow();
        assertThat(bubbleSortResult.getElapsed()).isEqualTo(1000L);
    }

    @Test
    void shouldFindTheFastestAlgorithmForIntegers() {
        Instant start = Instant.now();
        Instant stopFast = start.plusNanos(1000);
        Instant stopSlow = stopFast.plusNanos(1000);
        List<SortingStrategy> allSortingStrategies = SortingMadnessLogic.getAllSortingStrategies();
        Stubber stubber = doReturn(start, stopSlow);
        for (int i = 1; i < allSortingStrategies.size(); i++) {
            if (allSortingStrategies.get(i).getName().equals("Heap sort")) {
                stubber.doReturn(start, stopFast);
            } else {
                stubber.doReturn(start, stopSlow);
            }
        }
        stubber.when(clock).instant();

        SortingResult<Integer> result = sorter.sortWithBestStrategy(List.of(3, 1, 2), false);

        assertThat(result.getAlgorithm()).isEqualTo("Heap sort");
        assertThat(result.getElapsed()).isEqualTo(1000L);
        assertThat(result.getResult()).containsSequence(1, 2, 3);
    }

    @Test
    void shouldSortObjectsInAscendingOrder() {
        Instant start = Instant.now();
        Instant stop = start.plusNanos(1000);
        when(clock.instant())
                .thenReturn(start)
                .thenReturn(stop);
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{\"prop\": 2}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");

        List<SortingResult<JsonNode>> result = sorter.sortObjects(List.of(object3, object1, object2), "/prop", List.of(new BubbleSort()));

        Optional<SortingResult<JsonNode>> bubbleSortResultOpt = result.stream()
                .filter(res -> "Bubble sort".equals(res.getAlgorithm()))
                .findAny();
        assertThat(bubbleSortResultOpt).isPresent();
        SortingResult<JsonNode> bubbleSortResult = bubbleSortResultOpt.get();
        assertThat(bubbleSortResult.getResult()).containsSequence(object1, object2, object3);
    }

    @Test
    void shouldMeasureTimeForObjectsCorrectlyUsingClock() {
        Instant start = Instant.now();
        Instant stop = start.plusNanos(1000);
        when(clock.instant())
                .thenReturn(start)
                .thenReturn(stop);
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{\"prop\": 2}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");

        List<SortingResult<JsonNode>> result = sorter.sortObjects(List.of(object3, object1, object2), "/prop", List.of(new BubbleSort()));

        SortingResult<JsonNode> bubbleSortResult = result.stream()
                .filter(res -> "Bubble sort".equals(res.getAlgorithm()))
                .findAny()
                .orElseThrow();
        assertThat(bubbleSortResult.getElapsed()).isEqualTo(1000L);
    }

    @Test
    void shouldFindTheFastestAlgorithmForObjects() {
        Instant start = Instant.now();
        Instant stopFast = start.plusNanos(1000);
        Instant stopSlow = stopFast.plusNanos(1000);
        List<SortingStrategy> allSortingStrategies = SortingMadnessLogic.getAllSortingStrategies();
        Stubber stubber = doReturn(start, stopSlow);
        for (int i = 1; i < allSortingStrategies.size(); i++) {
            if (allSortingStrategies.get(i).getName().equals("Heap sort")) {
                stubber.doReturn(start, stopFast);
            } else {
                stubber.doReturn(start, stopSlow);
            }
        }
        stubber.when(clock).instant();
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{\"prop\": 2}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");

        SortingResult<JsonNode> result = sorter.sortObjectsWithBestStrategy(List.of(object3, object1, object2), "/prop", false);

        assertThat(result.getAlgorithm()).isEqualTo("Heap sort");
        assertThat(result.getElapsed()).isEqualTo(1000L);
        assertThat(result.getResult()).containsSequence(object1, object2, object3);
    }

    private JsonNode toJsonNode(String json) {
        try {
            return new ObjectMapper().readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}