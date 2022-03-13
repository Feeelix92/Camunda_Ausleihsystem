package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.BpmnError;

import java.util.Map;

public class Artikeltyp implements Anlegen{
    @Override
    public void getData() {
        // ToDo Daten aus Datenbank holen
    }

    @Override
    public void setData() {
        // ToDo Daten in Datenbank speichern
    }

    @Override
    public void checkData(Map<String, Object> formularEingaben) {
        // ToDo Daten aus Datenbank holen vergleichen
        //getData();

        String artikelTypName = (String) formularEingaben.get("artikelTypName");
        String artikelTypBeschreibung = (String) formularEingaben.get("artikelTypBeschreibung");

        // ToDo Artikeltypen mit Datenbank vergleichen

         // ToDo neue Artikeltypen in der Datenbank speichern
            //setData();
    }
}
