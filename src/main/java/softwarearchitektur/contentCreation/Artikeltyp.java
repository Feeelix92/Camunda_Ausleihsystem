package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.BpmnError;

import java.util.Map;

public class Artikeltyp implements Anlegen{
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

        // ToDo statischen Artikeltyp in dynamische ändern
        String artikeltyp = "Die Bibel";

        String artikelTypName = (String) formularEingaben.get("artikelTypName");
        String artikelTypBeschreibung = (String) formularEingaben.get("artikelTypBeschreibung");

        // ToDo Artikeltypen mit Datenbank vergleichen
        if(artikelTypName.equals(artikeltyp)) {
            throw new BpmnError("Artikeltyp_Vorhanden", "Der gewünschte Artikeltyp " + artikelTypName + " ist bereits vorhanden");
            //}else{
            // ToDo neue Kategorie in der Datenbank speichern
            //setData(formularEingaben);
        }
    }
}
