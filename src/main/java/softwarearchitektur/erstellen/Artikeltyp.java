package softwarearchitektur.erstellen;

import DaoJPA.DaoClasses.ArticleType_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.ArticleType;
import org.camunda.bpm.engine.delegate.BpmnError;

import java.util.Map;

public class Artikeltyp implements Erstellen {
    //Hier die Dao definieren die benötigt werden
    private static final DaoJPA<ArticleType> articleTypeDaoJPA = new ArticleType_DaoJpa();

    @Override
    public void erstellen_und_speichern(Map<String, Object> formularEingaben){
        //Hier wird einfach ein Java Objekt von Artikel erstellt und initialisiert mit den angegebenen werten
        String artikelTypName = (String) formularEingaben.get("artikelTypName");
        String artikelTyBeschreibung = (String) formularEingaben.get("artikelTypBeschreibung");

        try{
            ArticleType artikelTyp = new ArticleType(artikelTypName, artikelTyBeschreibung);
            //Speichern in der Datenbank über Dao Pattern
            articleTypeDaoJPA.save(artikelTyp); //<- save methode Nutzen um den artikel dann in der DB zu speichern
        } catch (Exception e) {
            e.printStackTrace();
            throw new BpmnError("Artikeltyp_Vorhanden", "Der gewünschte Artikeltyp ist bereits vorhanden");
        }
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
