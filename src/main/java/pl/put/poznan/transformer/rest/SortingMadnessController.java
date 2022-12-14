package pl.put.poznan.transformer.rest;


import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.sorting.SortingResult;


import java.util.Arrays;
import java.util.List;


@RestController
public class SortingMadnessController {

    @GetMapping
    public SortingResult sortIntegers(@RequestBody List<Integer> toSort){
        return null;
    }

    @GetMapping
    public SortingResult sortObjects(@RequestBody List<Integer> toSort){
        return null;
    }
}


