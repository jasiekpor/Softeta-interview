package pl.porowski.interview.adapters.sorting;

import org.springframework.stereotype.Component;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.model.Student;

import java.util.ArrayList;
import java.util.List;

import static pl.porowski.interview.domain.model.SortingStrategy.Name.BUBBLE;

@Component
public class BubbleSortStrategy implements SortingStrategy {
    @Override
    public List<Student> execute(List<Student> students) {
        List<Student> copy = new ArrayList<>(students);
        Student temp;
        int n = copy.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copy.get(j).compareTo(copy.get(j + 1)) > 0) {
                    temp = copy.get(j);
                    copy.set(j, copy.get(j + 1));
                    copy.set(j + 1, temp);
                }
            }
        }
        return copy;
    }

    @Override
    public Name name() {
        return BUBBLE;
    }
}
