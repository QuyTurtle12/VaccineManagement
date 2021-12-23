import java.awt.*;
import java.io.Serializable;

public class Injection implements Serializable {

    private String injectionID;
    private String firstPlace;
    private String secondPlace;
    private String firstDate;
    private String secondDate;
    private String studentID;
    private String vaccineID;

    public Injection(String injectionID, String firstPlace, String secondPlace, String firstDate, String secondDate, String studentID, String vaccineID) {

        this.injectionID = injectionID;

        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;

        this.firstDate = firstDate;
        this.secondDate = secondDate;

        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return this.injectionID;
    }

    public String getFirstPlace() {
        return this.firstPlace;
    }

    public String getSecondPlace() {
        return this.secondPlace;
    }

    public void setSecondPlace(String secondPlace){
        this.secondPlace = secondPlace;
    }

    public String getFirstDate() {
        return this.firstDate;
    }

    public String getSecondDate() {
        return this.secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public String getInjectionStudentID() {
        return this.studentID;
    }

    public String getInjectionVaccineID() {
        return this.vaccineID;
    }

}
