package pl.porowski.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.porowski.interview.adapters.sorting.SortingStrategyProviderImpl;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.ports.SortingStrategyProvider;
import pl.porowski.interview.infrastructure.Time;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class AppConfiguration {

    @Bean
    SortingStrategyProvider sortingStrategyProvider(Set<SortingStrategy> strategies) {
        return new SortingStrategyProviderImpl(strategies.stream()
                .collect(Collectors.toMap(SortingStrategy::name, Function.identity())));
    }

    @Bean
    Time time() {
        return new Time() {
        };
    }
}