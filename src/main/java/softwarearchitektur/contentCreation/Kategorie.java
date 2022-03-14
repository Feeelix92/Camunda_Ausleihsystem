package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.BpmnError;

import java.util.Map;

public class Kategorie implements Anlegen{
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
        // ToDo statische Kategorie in dynamische ändern
        String kategorien = "Fernseher";

        String kategorieName = (String) formularEingaben.get("kategorieName");
        String kategorieBeschreibung = (String) formularEingaben.get("kategorieBeschreibung");
        String uebergeordneteKategorie = (String) formularEingaben.get("uebergeordneteKategorie");

        // ToDo Kategoriename mit Datenbank vergleichen
        if(kategorieName.equals(kategorien)){
            throw new BpmnError("Kategorie_Vorhanden", "Der gewünschte Kategoriename " + kategorieName + " ist bereits vorhanden");
        //}else{
            // ToDo neue Kategorie in der Datenbank speichern
            //setData(formularEingaben);
        }
    }
}
