package pl.porowski.interview.adapters.sorting;

import org.junit.jupiter.api.Test;
import pl.porowski.interview.domain.model.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.porowski.interview.StudentFixture.someSortedStudents;
import static pl.porowski.interview.StudentFixture.someUnsortedStudents;

class HeapSortStrategyTest {

    private final HeapSortStrategy heapSortStrategy = new HeapSortStrategy();

    @Test
    void shouldReturnSortedListOfStudents() {
        List<Student> students = someUnsortedStudents();

        List<Student> result = heapSortStrategy.execute(students);

        System.out.println(result);
        assertThat(result).containsExactly(someSortedStudents().toArray(new Student[5]));
    }
}