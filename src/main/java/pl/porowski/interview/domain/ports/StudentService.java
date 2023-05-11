package pl.porowski.interview.domain.ports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.porowski.interview.domain.model.SortableStudents;
import pl.porowski.interview.domain.model.Student;
import pl.porowski.interview.infrastructure.Time;

import java.util.List;

import static pl.porowski.interview.domain.model.SortingStrategy.Name;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final SortingStrategyProvider sortingStrategyProvider;
    private final Time time;

    public SortableStudents sortUsing(List<Student> students, Name sortingAlgorithm) {
        SortableStudents sortableStudents = new SortableStudents(students, sortingStrategyProvider.of(sortingAlgorithm));
        sortableStudents.sort(time);
        return sortableStudents;
    }
}
