package pl.porowski.interview.domain.model;

import java.util.List;

public interface SortingStrategy {

    enum Name {
        BUBBLE, HEAP, MERGE
    }

    List<Student> execute(List<Student> students);

    Name name();
}
