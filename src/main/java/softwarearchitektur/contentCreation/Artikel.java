package softwarearchitektur.contentCreation;

import java.util.Map;

public class Artikel implements Anlegen{
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
        // ToDo Artikeltypen mit Datenbank vergleichen

        // ToDo neue Artikeltypen in der Datenbank speichern
        //setData();
    }
}
