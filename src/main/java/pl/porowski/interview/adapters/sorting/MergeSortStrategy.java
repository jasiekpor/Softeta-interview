package pl.porowski.interview.adapters.sorting;

import org.springframework.stereotype.Component;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.model.Student;

import java.util.List;

import static pl.porowski.interview.domain.model.SortingStrategy.Name.MERGE;

@Component
public class MergeSortStrategy implements SortingStrategy {
    @Override
    public List<Student> execute(List<Student> students) {
        Student[] array = new Student[students.size()];
        students.toArray(array);

        sort(array, 0, array.length - 1);

        return List.of(array);
    }

    private void sort(Student[] array, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(array, l, m);
            sort(array, m + 1, r);

            merge(array, l, m, r);
        }
    }

    private void merge(Student[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Student[] left = new Student[n1];
        Student[] right = new Student[n2];

        for (int i = 0; i < n1; ++i) {
            left[i] = array[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = array[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    @Override
    public Name name() {
        return MERGE;
    }
}
