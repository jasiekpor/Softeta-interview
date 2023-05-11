package pl.porowski.interview.domain.ports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.porowski.interview.adapters.sorting.BubbleSortStrategy;
import pl.porowski.interview.domain.model.SortableStudents;
import pl.porowski.interview.domain.model.Student;
import pl.porowski.interview.infrastructure.Time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.porowski.interview.StudentFixture.someSortedStudents;
import static pl.porowski.interview.StudentFixture.someUnsortedStudents;
import static pl.porowski.interview.domain.model.SortingStrategy.Name.BUBBLE;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private SortingStrategyProvider sortingStrategyProvider;
    @Mock
    private Time time;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnSortedListOfStudentsWithCalculatedTimeOfExecution() {
        when(sortingStrategyProvider.of(BUBBLE))
                .thenReturn(new BubbleSortStrategy());
        when(time.currentTimeMillis())
                .thenReturn(1000L)
                .thenReturn(2000L);

        SortableStudents sorted = studentService.sortUsing(someUnsortedStudents(), BUBBLE);

        assertThat(sorted.getSortedList()).containsExactly(someSortedStudents().toArray(new Student[5]));
        assertThat(sorted.getCalculationTime()).isEqualTo(1000L);
    }

}