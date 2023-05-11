package pl.porowski.interview.domain.model;

import lombok.Getter;
import pl.porowski.interview.infrastructure.Time;

import java.util.List;

import static pl.porowski.interview.domain.model.SortableStudents.Status.SORTED;
import static pl.porowski.interview.domain.model.SortableStudents.Status.UNSORTED;

@Getter
public class SortableStudents {
    enum Status {
        SORTED, UNSORTED
    }

    private final List<Student> initialList;
    private final SortingStrategy sortingAlgorithm;
    private List<Student> sortedList;
    private long calculationTime;
    private Status status;

    public SortableStudents(List<Student> initialList, SortingStrategy sortingAlgorithm) {
        this.initialList = initialList;
        this.sortingAlgorithm = sortingAlgorithm;
        this.status = UNSORTED;
    }

    public void sort(Time time) {
        if (status == SORTED) {
            return;
        }
        long start = time.currentTimeMillis();
        sortedList = sortingAlgorithm.execute(initialList);
        long end = time.currentTimeMillis();

        calculationTime = end - start;
        status = SORTED;
    }

    public List<Student> getSortedList() {
        if (status != SORTED) {
            throw new IllegalStateException("Student list is unsorted, sort it first!");
        }
        return sortedList;
    }

    public long getCalculationTime() {
        if (status != SORTED) {
            throw new IllegalStateException("Calculation time unknown because the list was never sorted, sort it first!");
        }
        return calculationTime;
    }

}
