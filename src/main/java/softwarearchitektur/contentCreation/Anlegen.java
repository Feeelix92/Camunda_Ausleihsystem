package softwarearchitektur.contentCreation;

import java.util.Map;

public interface Anlegen {
    void getData();
    void setData();
    void checkData(Map<String, Object> formularEingaben);
}

