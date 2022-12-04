package pl.put.poznan.transformer.sorting;


import java.util.List;
import java.util.Map;

public class SortingResult<T> {
    private final Map<String, Long> elapsed;
    private final List<T> result;

    public SortingResult(Map<String, Long> elapsed, List<T> result) {
        this.elapsed = elapsed;
        this.result = result;
    }

    public Map<String, Long> getElapsed() {
        return elapsed;
    }

    public List<T> getResult() {
        return result;
    }
}
