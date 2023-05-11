package pl.porowski.interview.adapters.view;

import lombok.RequiredArgsConstructor;
import pl.porowski.interview.domain.model.Student;
import pl.porowski.interview.infrastructure.LoadedFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class StudentListLoadedFile implements LoadedFile<List<Student>> {

    private final InputStream value;
    private List<Student> calculatedValue;

    public List<Student> read() {
        if (calculatedValue == null) {
            calculatedValue = parseInputStream();
        }
        return calculatedValue;
    }

    private List<Student> parseInputStream() {
        return new BufferedReader(new InputStreamReader(value)).lines()
                .filter(line -> line.matches("\\w+,\\d+(.\\d+)?"))
                .map(line -> line.split(","))
                .map(array -> new Student(array[0], new BigDecimal(array[1])))
                .toList();
    }
}
