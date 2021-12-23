import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InjectionList implements Serializable {

    private final List<Injection> injectionList = new ArrayList<Injection>();

    public void setInjectionList(final String injectionID, final String firstPlace, final String secondPlace, final String firstDate, final String secondDate, final String studentID, final String vaccineID) {
        injectionList.add(new Injection(injectionID, firstPlace, secondPlace, firstDate, secondDate, studentID, vaccineID));
    }

    public List<Injection> getInjectionList() {
        return injectionList;
    }

    public void removeInjection(int location) {
        injectionList.remove(location);
    }




}
