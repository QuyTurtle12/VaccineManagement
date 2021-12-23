import java.io.*;

public class FileIO {
    public void readInjectionData(InjectionList injectionList) {
        File file = new File("injection.dat");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            long fileSize = file.length();

            for (int i = 0; i < fileSize; i++) {
                Injection injection = (Injection) ois.readObject();
                injectionList.setInjectionList(injection.getInjectionID(), injection.getFirstPlace(), injection.getSecondPlace(), injection.getFirstDate(), injection.getSecondDate(), injection.getInjectionStudentID(), injection.getInjectionVaccineID());
            }

        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readStudentData(StudentList studentList) {
        File file = new File("student.dat");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            long fileSize = file.length();

            for (int i = 0; i < fileSize; i++) {
                Student student = (Student) ois.readObject();
                studentList.setStudentList(student.getStudentID(), student.getStudentName());
            }

        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readVaccineData(VaccineList vaccineList) {
        File file = new File("vaccine.dat");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            long fileSize = file.length();

            for (int i = 0; i < fileSize; i++) {
                Vaccine vaccine = (Vaccine) ois.readObject();
                vaccineList.setVaccineList(vaccine.getVaccineID(), vaccine.getVaccineName());
            }

        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(InjectionList injectionList) {
        File file = new File("injection.dat");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Injection injection: injectionList.getInjectionList()) {
                oos.writeObject(injection);
            }

            fos.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
