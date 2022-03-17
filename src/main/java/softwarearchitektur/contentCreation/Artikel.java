package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.BpmnError;

import java.util.Map;

public class Artikel implements Anlegen{
    @Override
    public Map<String, Object> getData() {
        // ToDo Daten aus Datenbank holen
        return null;
    }

    @Override
    public void setData(Map<String, Object> formularEingaben) {
        // ToDo Daten in Datenbank speichern
    }

    @Override
    public void checkData(Map<String, Object> formularEingaben) {
        // ToDo Daten aus Datenbank holen vergleichen
        //getData();
        // ToDo statischen Artikel in dynamische ändern
        String artikel = "Die Bibel - Auflage 1";

        String artikelName = (String) formularEingaben.get("artikelName");
        String artikelBeschreibung = (String) formularEingaben.get("artikelBeschreibung");
//        int artikelTypID = (int) formularEingaben.get("artikelTypID");
//        int lagernummer = (int) formularEingaben.get("lagernummer");
//        int zustandID = (int) formularEingaben.get("zustandID");
//        String herstellerArtNr = (String) formularEingaben.get("herstellerArtNr");
//        String verfuegbar = (String) formularEingaben.get("verfuegbar");
//        int einkaufspreis = (int) formularEingaben.get("einkaufspreis");

        // ToDo Artikeltypen mit Datenbank vergleichen
        if(artikelName.equals(artikel)) {
            throw new BpmnError("Artikel_Vorhanden", "Der gewünschte Artikel " + artikel + " ist bereits vorhanden");
            //}else{
            // ToDo neue Kategorie in der Datenbank speichern
            //setData(formularEingaben);
        }
    }
}
