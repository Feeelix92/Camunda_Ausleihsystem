package DaoJPA.Test;


import DaoJPA.DaoClasses.ArticleType_DaoJpa;
import DaoJPA.DaoClasses.Article_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Article;
import DaoJPA.EntityClasses.ArticleType;

import java.math.BigDecimal;

public class Example_for_Using_Dao {

    //Hier die Dao definieren die benötigt werden
    private static DaoJPA<Article> articleDaoJPA;
    private static DaoJPA<ArticleType> articleTypeDaoJPA;

    //Hier ein Beispiel wie man das DAO Object nutzt aktuell Implementiert sind die Klassen bzw. Tabellen:
    //Artikel (Article) / Artikeltyp (ArticleType) / Kategorie (Category) / Benutzer (User)
    public static void main(String[] args) {
        //Beim start des Programms bzw. Prozesses sollten die dao dann erstellt werden mit den entsprechenden Dao Klassen
        articleDaoJPA = new Article_DaoJpa();
        articleTypeDaoJPA = new ArticleType_DaoJpa();

        //Ausführen der TestMethoden
        artikel_erstellen_und_speichern();
        artikel_aus_DB_holen_und_eigenschaften_aendern();
        artikeltyp_erstellen_und_mit_artikel_verbinden();
    }

    private static void artikel_erstellen_und_speichern(){
        //Hier wird einfach ein Java Objekt von Artikel erstellt und initialisiert mit den angegebenen werten
        Article article = new Article(123,"Das ist ein Testartikel","Test Hersteller Art- Nummer", BigDecimal.valueOf(12.99));

        //Das objekt kann danach noch weiter angepasst bzw. geändert werden
        article.setZustandId((short) 1);
        article.setVerfuegbar(true);

        //Speichern in der Datenbank über Dao Pattern
        articleDaoJPA.save(article); //<- save methode Nutzen um den artikel dann in der DB zu speichern
    }

    private static void artikel_aus_DB_holen_und_eigenschaften_aendern(){
        //Die Methode getById aufrufen um den gegenstand mit der jeweiligen Id zu erhalten
        Article article = articleDaoJPA.getById(99).get(); //.get() nutzen um aus dem aufruf das Objekt zu erhalten
        System.out.println(article.getBeschreibung());

        //Mithilfe der Getter bzw. Setter Methoden können dann Eigenschaften geändert werden
        article.setBeschreibung("Das ist die Neue Beschreibung !!!!!");
        article.setVerfuegbar(false);

        //mit der update Methode der Dao API wir der artikel in der Datenbank mit dem neuen überschrieben
        articleDaoJPA.update(article);
    }

    public static void artikeltyp_erstellen_und_mit_artikel_verbinden(){
        //Erstellen eines Artikeltyps wie in der Methode zum Artikel erstellen
        ArticleType articleType = new ArticleType((short)10,"Test Type","Das ist eine Testbeschreibung !!!!!");

        Article article = articleDaoJPA.getById(99).get();

        article.setArtikelTypId(articleType.getArtikelTypId());

        articleTypeDaoJPA.save(articleType);
        articleDaoJPA.update(article);
    }
}
