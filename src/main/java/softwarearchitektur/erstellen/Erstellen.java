package softwarearchitektur.erstellen;

import java.util.Map;

public interface Erstellen {
    void erstellen_und_speichern(Map<String, Object> formularEingaben);
    void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben);
    void erstellen_und_verbinden(Map<String, Object> formularEingaben);
    void lade_entitaeten(Map<String, Object> formularEingaben);
}

