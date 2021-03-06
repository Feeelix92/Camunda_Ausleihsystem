package softwarearchitektur.erstellen;

import DaoJPA.DaoClasses.ArticleType_DaoJpa;
import DaoJPA.DaoClasses.Article_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Article;
import DaoJPA.EntityClasses.ArticleType;

import java.math.BigDecimal;
import java.util.Map;

public class Artikel implements Erstellen {

    //Hier die Dao definieren die benötigt werden
    private static final DaoJPA<Article> articleDaoJPA = new Article_DaoJpa();
    private static final DaoJPA<ArticleType> articleTypeDaoJPA = new ArticleType_DaoJpa();

    @Override
    public void erstellen_und_speichern(Map<String, Object> formularEingaben){
        // Hier wird einfach ein Java Objekt von Artikel erstellt und initialisiert mit den angegebenen werten
        String artikelName = (String) formularEingaben.get("artikelName");
        String artikelBeschreibung = (String) formularEingaben.get("artikelBeschreibung");
        short lagernummer = (short) formularEingaben.get("lagernummer");
        short zustandID = (short) formularEingaben.get("zustandID");
        String herstellerArtNr = (String) formularEingaben.get("herstellerArtNr");
        boolean verfuegbar = (boolean) formularEingaben.get("verfuegbar");
        Double einkaufspreis = (Double) formularEingaben.get("einkaufspreis");
        short artikelTypID = (short) formularEingaben.get("artikelTypID");

        // Artikel Objekt erstellen
        Article article = new Article(artikelBeschreibung, herstellerArtNr, BigDecimal.valueOf(einkaufspreis));

        // Ergänzende Attribute
        article.setZustandId(zustandID);
        article.setArtikelTypId(artikelTypID);
        article.setVerfuegbar(verfuegbar);
        article.setLagernummer(lagernummer);

        // Speichern in der Datenbank über Dao Pattern
        articleDaoJPA.save(article); //<- save methode Nutzen um den artikel dann in der DB zu speichern
    }
    @Override
    public void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben){
        //Die Methode getById aufrufen um den gegenstand mit der jeweiligen Id zu erhalten
        Article article = articleDaoJPA.getById(99).get(); //.get() nutzen um aus dem aufruf das Objekt zu erhalten
        System.out.println(article.getBeschreibung());

        //Mithilfe der Getter bzw. Setter Methoden können dann Eigenschaften geändert werden
        article.setBeschreibung("Das ist die Neue Beschreibung !!!!!");
        article.setVerfuegbar(false);

        //mit der update Methode der Dao API wir der artikel in der Datenbank mit dem neuen überschrieben
        articleDaoJPA.update(article);
    }

    @Override
    public void erstellen_und_verbinden(Map<String, Object> formularEingaben){
        //Erstellen eines Artikeltyps wie in der Methode zum Artikel erstellen
        ArticleType articleType = new ArticleType("Test Type","Das ist eine Testbeschreibung !!!!!");

        Article article = articleDaoJPA.getById(99).get();

        article.setArtikelTypId(articleType.getArtikelTypId());

        articleTypeDaoJPA.save(articleType);
        articleDaoJPA.update(article);
    }

    @Override
    public void lade_entitaeten(Map<String, Object> formularEingaben) {
        formularEingaben.put("artikelBeschreibung", "");
        formularEingaben.put("einkaufspreis", 0.0);
        formularEingaben.put("lagernummer", (short) 0);
        formularEingaben.put("herstellerArtNr", "");
        formularEingaben.put("verfuegbar", true);
        formularEingaben.put("artikelTypID", (short) 0);
        formularEingaben.put("zustandID", (short) 0);
    }
}
