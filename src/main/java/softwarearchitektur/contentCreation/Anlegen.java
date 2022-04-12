package softwarearchitektur.contentCreation;

import java.util.Map;

public interface Anlegen {
    void erstellen_und_speichern(Map<String, Object> formularEingaben);
    void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben);
    void erstellen_und_verbinden();
    Map<String, Object> lade_Entities(Map<String, Object> formularEingaben);
}

