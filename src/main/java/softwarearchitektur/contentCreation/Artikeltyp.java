package softwarearchitektur.contentCreation;

import java.util.Map;

public class Artikeltyp implements Anlegen{

    @Override
    public void erstellen_und_speichern(Map<String, Object> formularEingaben){

    }
    @Override
    public void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben){
    }

    @Override
    public void erstellen_und_verbinden(){
    }

    @Override
    public Map<String, Object> lade_Entities(Map<String, Object> formularEingaben) {
        formularEingaben.put("artikelTypName", "");
        formularEingaben.put("artikelTypBeschreibung", "");
        return formularEingaben;
    }
}
