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

        String artikel = "Samsung XYZ";
        String artikelName = (String) formularEingaben.get("artikelName");
        String artikelBeschreibung = (String) formularEingaben.get("artikelBeschreibung");
        int artikelTypID = (int) formularEingaben.get("artikelTypID");
        int lagernummer = (int) formularEingaben.get("lagernummer");
        int zustandID = (int) formularEingaben.get("zustandID");
        String herstellerArtNr = (String) formularEingaben.get("herstellerArtNr");
        String verfuegbar = (String) formularEingaben.get("verfuegbar");
        int einkaufspreis = (int) formularEingaben.get("einkaufspreis");

        // ToDo Artikeltypen mit Datenbank vergleichen

        // ToDo neue Artikeltypen in der Datenbank speichern
        //setData();
    }
}
