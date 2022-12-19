package pl.put.poznan.transformer.rest;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.RestInputIntegers;
import pl.put.poznan.transformer.logic.RestInputObjects;
import pl.put.poznan.transformer.logic.SortingMadnessLogic;
import pl.put.poznan.transformer.sorting.*;

import java.util.List;


@RestController
public class SortingMadnessController {

    @GetMapping("/sort/integers")
    public SortingResult<Integer> sortIntegers(@RequestBody RestInputIntegers restInputIntegers) {
        Sorter sorter = new Sorter();
        List<Integer> toSort = restInputIntegers.getToSort();
        List<SortingStrategy> sortingStrategies = SortingMadnessLogic.getSortingStrategies(restInputIntegers.getSortingStrategies());

        return sorter.sort(toSort, sortingStrategies);
    }

    @GetMapping("/sort/objects")
    public SortingResult<JsonNode> sortObjects(@RequestBody RestInputObjects restInputObjects) {
        List<JsonNode> toSort = restInputObjects.getToSort();
        String property = restInputObjects.getProperty();
        for (JsonNode object : toSort) {
            if (object.at(property).isMissingNode()) {
                throw new ObjectDontHaveSortingProperty(object, property);
            }
        }
        Sorter sorter = new Sorter();
        List<SortingStrategy> sortingStrategies = SortingMadnessLogic.getSortingStrategies(restInputObjects.getSortingStrategies());

        return sorter.sortObjects(toSort, property, sortingStrategies);
    }
}


