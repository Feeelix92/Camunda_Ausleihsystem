package softwarearchitektur.contentCreation;

import java.util.Map;

public interface Anlegen {
    Map<String, Object> getData();
    void setData(Map<String, Object> formularEingaben);
    void checkData(Map<String, Object> formularEingaben);
}

