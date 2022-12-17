package pl.put.poznan.transformer.rest;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.RestInputIntegers;
import pl.put.poznan.transformer.logic.RestInputObjects;
import pl.put.poznan.transformer.logic.SortingMadnessLogic;
import pl.put.poznan.transformer.sorting.Sorter;
import pl.put.poznan.transformer.sorting.SortingResult;
import pl.put.poznan.transformer.sorting.SortingStrategy;


import java.util.List;


@RestController
public class SortingMadnessController {

    @GetMapping("/sortIntegers")
    public SortingResult sortIntegers(@RequestBody RestInputIntegers restInputIntegers){
        Sorter sorter = new Sorter();
        List<Integer> toSort = restInputIntegers.getToSort();
        List<SortingStrategy> sortingStrategies = SortingMadnessLogic.getSortingStrategies(restInputIntegers.getSortingStrategies());


        SortingResult sortingResult = sorter.sort(toSort, sortingStrategies);
        return sortingResult;
    }

    @GetMapping("/sortObjects")
    public SortingResult sortObjects(@RequestBody RestInputObjects restInputObjects){
        Sorter sorter = new Sorter();
        List<JsonNode> toSort = restInputObjects.getToSort();
        List<SortingStrategy> sortingStrategies = SortingMadnessLogic.getSortingStrategies(restInputObjects.getSortingStrategies());

        SortingResult sortingResult = sorter.sortObjects(toSort, "/sort/key", sortingStrategies);
        return sortingResult;
    }
}


