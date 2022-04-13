package softwarearchitektur.erstellen;

import java.util.Map;

public class Artikeltyp implements Erstellen {

    @Override
    public void erstellen_und_speichern(Map<String, Object> formularEingaben){

    }
    @Override
    public void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben){
    }

    @Override
    public void erstellen_und_verbinden(Map<String, Object> formularEingaben){
    }

    @Override
    public void lade_entitaeten(Map<String, Object> formularEingaben) {
        formularEingaben.put("artikelTypName", "");
        formularEingaben.put("artikelTypBeschreibung", "");
    }
}
