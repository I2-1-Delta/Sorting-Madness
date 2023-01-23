package pl.put.poznan.sortingmadness.sorting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class SortingConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public Sorter sorter(Clock clock) {
        return new Sorter(clock);
    }
}
