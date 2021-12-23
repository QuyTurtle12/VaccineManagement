import java.io.Serializable;

public class Vaccine implements Serializable {

    private String vaccineID;
    private String vaccineName;

    public Vaccine(String vaccineID, String vaccineName) {

        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;

    }

    public String getVaccineID() {
        return this.vaccineID;
    }

    public String getVaccineName() {
        return this.vaccineName;
    }
}
