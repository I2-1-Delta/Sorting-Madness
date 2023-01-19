package pl.put.poznan.sortingmadness.logic;

import pl.put.poznan.sortingmadness.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortingMadnessLogic {

    public static List<SortingStrategy> getSortingStrategies(List<SortingStrategyEnum> sortingStrategiesEnums) {
        List<SortingStrategy> sortingStrategies = new ArrayList<>();
        for (SortingStrategyEnum sortingStrategyEnum : sortingStrategiesEnums) {
            sortingStrategies.add(sortingStrategyEnum.getSortingStrategy());
        }
        return sortingStrategies;
    }

    public static List<SortingStrategy> getAllSortingStrategies() {
        List<SortingStrategyEnum> sortingStrategiesEnums = new ArrayList<SortingStrategyEnum>();
        sortingStrategiesEnums.add(SortingStrategyEnum.BUBBLE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.HEAP_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.QUICK_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.MERGE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.SELECTION_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.INSERTION_SORT);
        return SortingMadnessLogic.getSortingStrategies(sortingStrategiesEnums);
    }
}
