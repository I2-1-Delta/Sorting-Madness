package pl.put.poznan.sortingmadness.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.put.poznan.sortingmadness.app.SortingMadness;
import pl.put.poznan.sortingmadness.logic.RestInputIntegers;
import pl.put.poznan.sortingmadness.logic.RestInputObjects;
import pl.put.poznan.sortingmadness.logic.RestInputObjectsBestStrategy;
import pl.put.poznan.sortingmadness.logic.SortingStrategyEnum;
import pl.put.poznan.sortingmadness.sorting.NoSortingAlgorithmSelected;
import pl.put.poznan.sortingmadness.sorting.NothingToSort;
import pl.put.poznan.sortingmadness.sorting.Sorter;
import pl.put.poznan.sortingmadness.sorting.SortingResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = SortingMadness.class)
class SortingMadnessControllerTest {
    @MockBean
    private Sorter sorter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCallSorterForSortingIntegers() throws Exception {
        when(sorter.sort(anyList(), anyList())).thenReturn(List.of(new SortingResult<>("Bubble sort", 10000L, List.of(1, 2, 3))));
        mockMvc.perform(
                get("/sort/integers")
                        .content(toJson(new RestInputIntegers(List.of(3, 1, 2), List.of(SortingStrategyEnum.BUBBLE_SORT), null)))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
        verify(sorter, times(1)).sort(anyList(), anyList());
    }

    @Test
    void shouldReturn400BadRequestWhenNothingToSortThrown() throws Exception {
        when(sorter.sort(anyList(), anyList())).thenThrow(new NothingToSort());
        mockMvc.perform(
                        get("/sort/integers")
                                .content(toJson(new RestInputIntegers(List.of(), List.of(SortingStrategyEnum.BUBBLE_SORT), null)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
        verify(sorter, times(1)).sort(anyList(), anyList());
    }

    @Test
    void shouldReturn400BadRequestWhenNoSortingAlgorithmSelectedThrown() throws Exception {
        when(sorter.sort(anyList(), anyList())).thenThrow(new NoSortingAlgorithmSelected());
        mockMvc.perform(
                        get("/sort/integers")
                                .content(toJson(new RestInputIntegers(List.of(3, 1, 2), List.of(), null)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
        verify(sorter, times(1)).sort(anyList(), anyList());
    }

    @Test
    void shouldCallSorterForSortingObjects() throws Exception {
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{\"prop\": 2}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");

        when(sorter.sortObjects(anyList(), anyString(), anyList())).thenReturn(
                List.of(new SortingResult<>("Bubble sort", 10000L, List.of(object1, object2, object3)))
        );
        mockMvc.perform(
                        get("/sort/objects")
                                .content(toJson(new RestInputObjects(List.of(object3, object1, object2), "/prop", List.of(SortingStrategyEnum.BUBBLE_SORT), null)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        verify(sorter, times(1)).sortObjects(anyList(), eq("/prop"), anyList());
    }

    @Test
    void shouldVerifyThatEachObjectHasSortingProperty() throws Exception {
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");

        when(sorter.sortObjects(anyList(), anyString(), anyList())).thenReturn(
                List.of(new SortingResult<>("Bubble sort", 10000L, List.of(object1, object2, object3)))
        );
        mockMvc.perform(
                        get("/sort/objects")
                                .content(toJson(new RestInputObjects(List.of(object3, object1, object2), "/prop", List.of(SortingStrategyEnum.BUBBLE_SORT), null)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
        verify(sorter, never()).sortObjects(anyList(), eq("/prop"), anyList());
    }

    @Test
    void shouldCallSortBestStrategyForIntegers() throws Exception {
        when(sorter.sortWithBestStrategy(anyList(), eq(false))).thenReturn(new SortingResult<>("Heap sort", 10000L, List.of(1, 2, 3)));
        mockMvc.perform(
                        get("/sort/integers/best/strategy")
                                .content(toJson(List.of(3, 2, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        verify(sorter, times(1)).sortWithBestStrategy(anyList(), eq(false));
    }

    @Test
    void shouldCallSortBestStrategyForObjects() throws Exception {
        JsonNode object1 = toJsonNode("{\"prop\": 1}");
        JsonNode object2 = toJsonNode("{\"prop\": 2}");
        JsonNode object3 = toJsonNode("{\"prop\": 3}");
        when(sorter.sortObjectsWithBestStrategy(anyList(), anyString(), eq(false))).thenReturn(new SortingResult<>("Heap sort", 10000L, List.of(object1, object2, object3)));
        mockMvc.perform(
                        get("/sort/objects/best/strategy")
                                .content(toJson(new RestInputObjectsBestStrategy(List.of(object3, object1, object2), "/prop")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        verify(sorter, times(1)).sortObjectsWithBestStrategy(anyList(), eq("/prop"), eq(false));
    }

    private <T> String toJson(T object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode toJsonNode(String json) {
        try {
            return new ObjectMapper().readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}