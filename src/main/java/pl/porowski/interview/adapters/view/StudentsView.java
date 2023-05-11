package pl.porowski.interview.adapters.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import pl.porowski.interview.domain.model.SortableStudents;
import pl.porowski.interview.domain.model.SortingStrategy;
import pl.porowski.interview.domain.model.Student;
import pl.porowski.interview.domain.ports.StudentService;

import java.util.List;


@PageTitle("Sorted Students")
@Route(value = "")
public class StudentsView extends VerticalLayout {
    private final Grid<Student> studentsGrid = new Grid<>(Student.class);
    private final ComboBox<SortingStrategy.Name> sortingAlgorithm = new ComboBox<>("Sorting algorithm");

    private final MemoryBuffer buffer = new MemoryBuffer();
    private final Span calculationText = new Span();

    private final HorizontalLayout links = new HorizontalLayout();

    private final StudentService studentService;

    private StudentListLoadedFile loadedFile;


    public StudentsView(StudentService studentService) {
        this.studentService = studentService;

        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> {
            loadedFile = new StudentListLoadedFile(buffer.getInputStream());
            updateStudentsGrid(loadedFile.read());
        });
        upload.getElement().addEventListener("upload-abort", event -> {
            loadedFile = null;
            clearView();
        });

        sortingAlgorithm.setItems(SortingStrategy.Name.values());
        sortingAlgorithm.setValue(SortingStrategy.Name.BUBBLE);
        calculationText.setVisible(false);
        Button apply = new Button("Sort");
        apply.addClickListener(event -> updateView());
        setUpGrid();

        setSizeFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(upload, sortingAlgorithm, apply, calculationText, links);
        add(horizontalLayout, studentsGrid);
    }

    private void updateView() {
        if (loadedFile == null) {
            clearView();
            return;
        }
        SortableStudents sortedStudents = studentService.sortUsing(loadedFile.read(), sortingAlgorithm.getValue());

        updateStudentsGrid(sortedStudents.getSortedList());

        updateCalculationText(String.format("%sSort sorted %d students in %d ms",
                        sortingAlgorithm.getValue().name().toLowerCase(),
                        sortedStudents.getSortedList().size(),
                        sortedStudents.getCalculationTime()),
                "badge success");

        addDownloadLink(sortedStudents);
    }

    private void updateStudentsGrid(List<Student> studentList) {
        studentsGrid.setItems(studentList);
    }

    void clearView() {
        studentsGrid.setItems(List.of());
        links.removeAll();
        updateCalculationText("File not selected!", "badge error");
    }

    private void addDownloadLink(SortableStudents sortedStudents) {
        links.removeAll();
        SortedStudentsDownloadableFile downloadableFile = new SortedStudentsDownloadableFile(sortedStudents);
        StreamResource streamResource = new StreamResource(downloadableFile.getName(), downloadableFile::serialize);
        Anchor anchor = new Anchor(streamResource, downloadableFile.getName());
        anchor.getElement().setAttribute("download", true);
        links.add(anchor);
    }

    private void updateCalculationText(String text, String style) {
        calculationText.setText(text);
        calculationText.getElement().getThemeList().clear();
        calculationText.getElement().getThemeList().add(style);
        calculationText.setVisible(true);
    }

    private void setUpGrid() {
        studentsGrid.setSizeFull();
        studentsGrid.addColumn(Student::name).setHeader("Name");
        studentsGrid.addColumn(Student::rate).setHeader("Rate");
        studentsGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
