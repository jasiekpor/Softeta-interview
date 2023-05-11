package pl.porowski.interview.adapters.sorting;

import lombok.RequiredArgsConstructor;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.ports.SortingStrategyProvider;

import java.util.Map;

import static pl.porowski.interview.domain.model.SortingStrategy.Name;

@RequiredArgsConstructor
public class SortingStrategyProviderImpl implements SortingStrategyProvider {
    private final Map<Name, SortingStrategy> strategies;

    public SortingStrategy of(Name name) {
        return strategies.get(name);
    }
}
