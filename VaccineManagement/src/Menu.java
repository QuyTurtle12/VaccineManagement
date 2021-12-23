import javax.tools.Diagnostic;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu implements Serializable {
    Scanner input = new Scanner(System.in);

    private final InjectionList injectionList = new InjectionList();

    private final StudentList studentList = new StudentList();

    private final VaccineList vaccineList = new VaccineList();


    public int inputInt() {
        return Integer.parseInt(input.nextLine());
    }

    public void readData() {
        FileIO fileIO = new FileIO();
        fileIO.readInjectionData(this.injectionList);
        fileIO.readStudentData(this.studentList);
        fileIO.readVaccineData(this.vaccineList);
    }


    //Checking Area
    private boolean checkExistInjectionID(String newID) {
        for (Injection injection : injectionList.getInjectionList()) {

            if (newID.equals(injection.getInjectionID())) {
                return true;
            }

        }
        return false;
    }

    private boolean checkStudentID(String newID) {
        for (Student student : studentList.getStudentList()) {

            if (newID.equals(student.getStudentID())) {
                return true;
            }

        }
        return false;
    }

    private boolean checkStudentName(String name) {
        for (Student student : studentList.getStudentList()) {

            if (name.equals(student.getStudentName())) {
                return true;
            }

        }
        return false;
    }

    private boolean checkStudentInjection(String studentID) {
        for (Injection injection : injectionList.getInjectionList()) {

            if (studentID.equals(injection.getInjectionStudentID())) {
                return true;
            }

        }
        return false;
    }

    private boolean checkInjectionUpdate(String injectionID) {
        for (Injection injection : injectionList.getInjectionList()) {

            if (injectionID.equals(injection.getInjectionID())) {
                if (injection.getSecondPlace() == null && injection.getSecondDate() == null) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean checkConfirmation() {
        while (true) {
            int answer = inputInt();
            try {
                if (answer == 0) return false;
                if (answer == 1) {
                    return true;
                }
                System.out.println("Wrong input");
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, expect int type!");
            }
        }
    }

    private String formatDate(String newDate) {
        while (true) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(newDate, formatter);
                return date.format(formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Wrong format input!");
                newDate = input.nextLine();
            }
        }
    }


    //Function Area
    public void show() {
        for (Injection injection: injectionList.getInjectionList()) {
            System.out.println(injection.getInjectionID() + " - " + injection.getFirstPlace() + " - " + injection.getFirstDate() + " - " + injection.getSecondPlace() + " - " + injection.getSecondDate() + " - " + injection.getInjectionStudentID() + " - " + injection.getInjectionVaccineID());
            System.out.println();
        }
    }

    public void add() {

        while (true) {
            System.out.println("Enter new injection ID: ");
            System.out.println("Example: A001 (A is first letter of Astra vaccine - 001 is represent for numerical oreder)");
            String newInjectionID = input.nextLine();

            if (newInjectionID.contains(" ")) {
                System.out.println("Wrong format input!");
                break;
            }

            //CheckFirstLetterFormat
            char ch = newInjectionID.charAt(0);
            if (ch == 'A' || ch == 'S' || ch == 'V' || ch == 'P' || ch == 'M') {
                System.out.println("Approve ID!");
            } else {
                System.out.println("Wrong format input, expect first letter is A (Astra) / S (SPUTNIK V) /V (Vero Cell) / P (Pfizer) / M (Moderna)!");
                break;
            }

            //CheckStringLength
            if (newInjectionID.length() != 4) {
                System.out.println("Wrong input format, expect 4 characters");
                break;
            }

            if (checkExistInjectionID(newInjectionID)) {
                System.out.println("ID has been existed");
                break;
            }

            System.out.println("Enter student ID: ");
            String studentID = input.nextLine();

            if (!checkStudentID(studentID)) {
                System.out.println("Student not exist");
                break;
            }

            if (checkStudentInjection(studentID)) {
                System.out.println(studentID + " already has injection info in system");
                break;
            }

            String vaccineID = null;
            System.out.println("Enter your vaccine : ");

            int vaccinePosition = 1;
            for (Vaccine vaccine: vaccineList.getVaccineList()) {
                System.out.println(vaccine.getVaccineName() +"\t" + " (" + vaccinePosition + ')');
                vaccinePosition++;
            }

            int answer = inputInt();
            switch (answer) {
                case 1 -> vaccineID = "Covid-V001";
                case 2 -> vaccineID = "Covid-V002";
                case 3 -> vaccineID = "Covid-V003";
                case 4 -> vaccineID = "Covid-V004";
                case 5 -> vaccineID = "Covid-V005";
                default -> System.out.println("Error occurred!");
            }

            System.out.println("Enter name of the place that you have injected: ");
            String place = input.nextLine();

            if (place.equals(" ")) {
                System.out.println("Wrong input format!");
                return;
            }

            System.out.println("Enter date that you have injected: dd/MM/yyyy");
            String date = input.nextLine();
            date = formatDate(date);

            injectionList.setInjectionList(newInjectionID, place, null, date, null, studentID, vaccineID);

            FileIO fileIO = new FileIO();

            fileIO.writeFile(this.injectionList);

            System.out.println("Adding successfully!");

            System.out.println("Do you want to continue: continue (1)/ stop(0)");
            if (!checkConfirmation()) {
                System.out.println("Thank you for your contribution!");
                break;
            }
        }
    }

    public void update() {
        System.out.println("Enter injection ID: ");
        String injectionID = input.nextLine();

        if (!checkExistInjectionID(injectionID)) {
            System.out.println("Injection info does not exist");
            return;
        }

        if (!checkInjectionUpdate(injectionID)) {
            System.out.println("Student has completed 2 injections!");
            return;
        }

        System.out.println("Enter 2nd place you have injected: ");
        String place = input.nextLine();

        if (place.equals(" ")) {
            System.out.println("Wrong input format!");
            return;
        }

        System.out.println("Enter 2nd date you have injected: dd/MM/yyyy");
        String date = input.nextLine();
        date = formatDate(date);

        for (Injection injection : injectionList.getInjectionList()) {
            if (injectionID.equals(injection.getInjectionID())) {

                injection.setSecondPlace(place);
                injection.setSecondDate(date);

                System.out.println("Updating successfully");
                System.out.println("Student has completed 2 injections!");
            }
        }
        FileIO fileIO = new FileIO();
        fileIO.writeFile(this.injectionList);
    }

    public void delete() {
        System.out.println("Enter injection ID you want to delete: ");
        String injectionID = input.nextLine();

        if (!checkExistInjectionID(injectionID)) {
            System.out.println("Injection does not exist");
            return;
        }

        int injectionPosition = 0;
        for (Injection injection : injectionList.getInjectionList()) {

            if (injectionID.equals(injection.getInjectionID())) {

                System.out.println("Do you really want to delete " + injectionID + " : confirm(1)/ cancel(0)");
                if (!checkConfirmation()) break;

                injectionList.removeInjection(injectionPosition);

                FileIO fileIO = new FileIO();
                fileIO.writeFile(this.injectionList);

                System.out.println("Deleting successfully");
                return;
            }
            injectionPosition++;
        }
    }

    public void search() {
        String studentID = null;
        String studentName;

        try {
            System.out.println("Search by student ID or student name: ID(0)/ Name(1)");

            //Search by ID
            if (!checkConfirmation()) {
                System.out.println("Enter student ID: ");
                studentID = input.nextLine();

                if (!checkStudentInjection(studentID)) {
                    System.out.println(studentID + " does not have injection info in system");
                    return;
                }
            }

            //Search by Name
            else  {
                System.out.println("Enter student Name: ");
                studentName = input.nextLine();

                if (!checkStudentName(studentName)) {
                    System.out.println(studentName + " does not exist");
                    return;
                }

                for (Student student : studentList.getStudentList()) {

                    if (studentName.equals(student.getStudentName())) {
                        studentID = student.getStudentID();
                        break;
                    }
                }
            }

            int result = 0; //Checking if student have injection info

            for (Injection injection : injectionList.getInjectionList()) {
                if (studentID.equals(injection.getInjectionStudentID())) {

                    System.out.println(injection.getInjectionID() + " - " + injection.getFirstPlace() + " - " + injection.getSecondPlace() + " - " + injection.getFirstDate() + " - " + injection.getSecondDate() + " - " + injection.getInjectionStudentID() + " - " + injection.getInjectionVaccineID());
                    result = 1;
                    break;

                }
            }
            if (result == 0) System.out.println(studentID + " does not have injection info in system.");

        } catch (NumberFormatException e) {
            System.out.println("Wrong input, expect int type!");
        }
    }
}