import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private final List<Student> studentList = new ArrayList<Student>();

    public void setStudentList(final String studentID, final String studentName) {
        studentList.add(new Student(studentID, studentName));
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
