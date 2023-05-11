package pl.porowski.interview.adapters.sorting;

import org.springframework.stereotype.Component;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.model.Student;

import java.util.List;

import static pl.porowski.interview.domain.model.SortingStrategy.Name.HEAP;

@Component
public class HeapSortStrategy implements SortingStrategy {
    @Override
    public List<Student> execute(List<Student> students) {
        Student[] array = new Student[students.size()];
        students.toArray(array);

        int arrayLength = array.length;

        for (int i = arrayLength / 2 - 1; i >= 0; i--)
            heapify(array, arrayLength, i);

        for (int i = arrayLength - 1; i > 0; i--) {
            Student temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }

        return List.of(array);
    }

    private void heapify(Student[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        if (right < n && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            Student swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    @Override
    public Name name() {
        return HEAP;
    }
}
