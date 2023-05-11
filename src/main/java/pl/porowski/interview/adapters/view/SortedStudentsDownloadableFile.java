package pl.porowski.interview.adapters.view;

import lombok.Getter;
import pl.porowski.interview.domain.model.SortableStudents;
import pl.porowski.interview.infrastructure.DownloadableFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Collectors;

@Getter
public class SortedStudentsDownloadableFile extends DownloadableFile<SortableStudents> {

    private final String name;

    public SortedStudentsDownloadableFile(SortableStudents students) {
        super(students);
        this.name = students.getSortingAlgorithm().name().name().toLowerCase() + "Sort.txt";
    }

    @Override
    public InputStream serialize() {
        return new ByteArrayInputStream(
                objectToSerialize.getSortedList().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n"))
                        .getBytes()
        );
    }
}
