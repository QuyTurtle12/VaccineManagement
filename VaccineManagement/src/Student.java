import java.io.Serializable;

public class Student implements Serializable {

    private String studentID;
    private String studentName;


    public Student(String studentID, String studentName) {

        this.studentID = studentID;
        this.studentName = studentName;

    }

    public String getStudentID() {
        return this.studentID;
    }

    public String getStudentName() {
        return this.studentName;
    }

}
