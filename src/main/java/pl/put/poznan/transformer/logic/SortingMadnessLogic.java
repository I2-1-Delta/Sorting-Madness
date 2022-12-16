package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.sorting.SortingStrategyEnum;
import pl.put.poznan.transformer.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortingMadnessLogic {

    public static List<SortingStrategy> getSortingStrategies(List<SortingStrategyEnum> sortingStrategiesEnums){
        List<SortingStrategy> sortingStrategies= new ArrayList<>();
        for(SortingStrategyEnum sortingStrategyEnum : sortingStrategiesEnums){
            sortingStrategies.add(sortingStrategyEnum.getSortingStrategy());
        }
        return sortingStrategies;
    }
}
