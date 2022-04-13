package softwarearchitektur.erstellen;

public class ErstellenFactory {
    Erstellen neueInhalteErstellen(String art){
        switch (art){
            case "artikel":
                return new Artikel();
            case "artikeltyp":
                return new Artikeltyp();
            case "kategorie":
                return new Kategorie();
        }
        throw new RuntimeException("Es konnte kein Eintrag gefunden werden");
    }
}
