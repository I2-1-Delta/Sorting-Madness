package pl.put.poznan.sortingmadness.rest;


import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.sortingmadness.logic.RestInputIntegers;
import pl.put.poznan.sortingmadness.logic.RestInputObjects;
import pl.put.poznan.sortingmadness.logic.RestInputObjectsBestStrategy;
import pl.put.poznan.sortingmadness.logic.SortingMadnessLogic;
import pl.put.poznan.sortingmadness.sorting.*;

import java.util.List;


@RestController
public class SortingMadnessController {
    private static final Logger log = LoggerFactory.getLogger(SortingMadnessController.class);

    @GetMapping("/sort/integers")
    public List<SortingResult<Integer>> sortIntegers(@RequestBody RestInputIntegers restInputIntegers) {
        log.info("Sorting integers");
        Sorter sorter = new Sorter();
        List<Integer> toSort = restInputIntegers.getToSort();
        List<SortingStrategy> sortingStrategies = SortingMadnessLogic.getSortingStrategies(restInputIntegers.getSortingStrategies());

        return sorter.sort(toSort, sortingStrategies);
    }

    @GetMapping("/sort/objects")
    public List<SortingResult<JsonNode>> sortObjects(@RequestBody RestInputObjects restInputObjects) {
        log.info("Sorting objects");
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

    @GetMapping("/sort/integers/best/strategy")
    public SortingResult<Integer> sortIntegersWithBestStrategy(@RequestBody List<Integer> toSort) {
        log.info("Sorting integers");
        Sorter sorter = new Sorter();

        return sorter.sortWithBestStrategy(toSort);
    }

    @GetMapping("/sort/objects/best/strategy")
    public SortingResult<JsonNode> sortObjectsWithBestStrategy(@RequestBody RestInputObjectsBestStrategy restInputObjectsBestStrategy) {
        log.info("Sorting objects");
        List<JsonNode> toSort = restInputObjectsBestStrategy.getToSort();
        String property = restInputObjectsBestStrategy.getProperty();

        for (JsonNode object : toSort) {
            if (object.at(property).isMissingNode()) {
                throw new ObjectDontHaveSortingProperty(object, property);
            }
        }
        Sorter sorter = new Sorter();

        return sorter.sortObjectsWithBestStrategy(toSort, property);
    }


    @ExceptionHandler({NoSortingAlgorithmSelected.class, NothingToSort.class, ObjectDontHaveSortingProperty.class})
    ResponseEntity<ApiError> handleException(RuntimeException exception) {
        log.error("Problem during sorting", exception);
        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }
}

