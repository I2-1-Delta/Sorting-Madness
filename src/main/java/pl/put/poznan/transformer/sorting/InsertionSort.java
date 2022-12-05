package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingStrategy {

    @Override
    public String getName() {return "Insertion sort";}

    @Override
    public List<Integer> sort(List<Integer> toSort){
        List<Integer> result = new ArrayList<>(toSort);
        int n = result.size();
        for(int i = 1; i < n; i++){
            int temp = result.get(i);
            int j = i - 1;
            while(j >= 0 && result.get(j) > temp ){
                result.set(j+1, result.get(j));
                j = j-1;
            }
            result.set(j+1, temp);
        }
        return result;
    }

    @Override
    public List<JsonNode> sort(List<JsonNode> toSort, String path) {
        List<JsonNode> result = new ArrayList<>(toSort);
        JsonNodeComparator comparator = new JsonNodeComparator(path);
        int n = result.size();
        for(int i = 1; i < n; i++){
            JsonNode temp = result.get(i);
            int j = i - 1;
            while(j >= 0 && comparator.compare(result.get(j), temp) > 0){
                result.set(j+1, result.get(j));
                j = j-1;
            }
            result.set(j+1, temp);
        }
        return result;
    }
}
