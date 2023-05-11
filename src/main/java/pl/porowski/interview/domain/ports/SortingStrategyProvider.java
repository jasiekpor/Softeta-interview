package pl.porowski.interview.domain.ports;

import pl.porowski.interview.domain.model.SortingStrategy;

import static pl.porowski.interview.domain.model.SortingStrategy.Name;

public interface SortingStrategyProvider {

    SortingStrategy of(Name sortingAlgorithm);
}
