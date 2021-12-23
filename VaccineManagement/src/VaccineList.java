import java.util.ArrayList;
import java.util.List;

public class VaccineList {

    private final List<Vaccine> vaccineList = new ArrayList<Vaccine>();

    public void setVaccineList(final String vaccineID, final String vaccineName) {
        vaccineList.add(new Vaccine(vaccineID, vaccineName));
    }

    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }
}
