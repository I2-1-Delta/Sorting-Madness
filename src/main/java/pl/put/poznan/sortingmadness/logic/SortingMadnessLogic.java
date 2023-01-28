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
     public static  List<SortingStrategy> getSortingStrategiesDescending(List<SortingStrategyEnum> sortingStrategiesEnums){
         List<SortingStrategy> sortingStrategies = new ArrayList<>();
         for (SortingStrategyEnum sortingStrategyEnum : sortingStrategiesEnums) {
             sortingStrategies.add(sortingStrategyEnum.getSortingStrategyDescending());
         }
         return sortingStrategies;
     }

     public static void setIterationLimit(List<SortingStrategy> sortingStrategies, List<Integer> iterationLimits){
        if(iterationLimits != null) {
            for (int i = 0; i < iterationLimits.size() && i < sortingStrategies.size(); i++) {
                sortingStrategies.get(i).setLimit(iterationLimits.get(i));
            }
        }
        else{
            for (int i = 0; i < sortingStrategies.size(); i++) {
                sortingStrategies.get(i).setLimit(0);
            }
        }
     }

    public static List<SortingStrategy> getAllSortingStrategies() {
        List<SortingStrategyEnum> sortingStrategiesEnums = new ArrayList<>();
        sortingStrategiesEnums.add(SortingStrategyEnum.BUBBLE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.HEAP_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.QUICK_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.MERGE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.SELECTION_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.INSERTION_SORT);
        return SortingMadnessLogic.getSortingStrategies(sortingStrategiesEnums);
    }

    public static List<SortingStrategy> getAllSortingStrategiesDescending() {
        List<SortingStrategyEnum> sortingStrategiesEnums = new ArrayList<>();

        sortingStrategiesEnums.add(SortingStrategyEnum.BUBBLE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.HEAP_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.QUICK_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.MERGE_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.SELECTION_SORT);
        sortingStrategiesEnums.add(SortingStrategyEnum.INSERTION_SORT);
        return SortingMadnessLogic.getSortingStrategiesDescending(sortingStrategiesEnums);
    }

}
