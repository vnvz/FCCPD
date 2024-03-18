import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Student> students;

    public Topic(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
}