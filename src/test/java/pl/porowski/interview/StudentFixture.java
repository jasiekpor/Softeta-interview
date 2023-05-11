package pl.porowski.interview;

import pl.porowski.interview.domain.model.Student;

import java.math.BigDecimal;
import java.util.List;

public class StudentFixture {

    public static List<Student> someUnsortedStudents() {
        return List.of(
                new Student("Jan", new BigDecimal("1.1")),
                new Student("Ola", new BigDecimal("8.5")),
                new Student("Emil", new BigDecimal("5.0")),
                new Student("Zosia", new BigDecimal("3.8")),
                new Student("Kamil", new BigDecimal("7.5"))
        );
    }

    public static List<Student> someSortedStudents() {
        return List.of(
                new Student("Jan", new BigDecimal("1.1")),
                new Student("Zosia", new BigDecimal("3.8")),
                new Student("Emil", new BigDecimal("5.0")),
                new Student("Kamil", new BigDecimal("7.5")),
                new Student("Ola", new BigDecimal("8.5"))
        );
    }
}
