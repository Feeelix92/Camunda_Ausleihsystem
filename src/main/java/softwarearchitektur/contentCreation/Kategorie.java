package softwarearchitektur.contentCreation;

import DaoJPA.DaoClasses.Category_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Category;

import java.util.Map;

public class Kategorie implements Anlegen{
    //Hier die Dao definieren die benötigt werden
    private static final DaoJPA<Category> categoryDaoJPA = new Category_DaoJpa();

    @Override
    public void erstellen_und_speichern(Map<String, Object> formularEingaben){
        //Hier wird einfach ein Java Objekt von Kategorie erstellt und initialisiert mit den angegebenen werten
        Category category = new Category();
        category.setName((String) formularEingaben.get("kategorieName"));
        category.setBeschreibung((String) formularEingaben.get("kategorieBeschreibung"));
        category.setUebergeordnete_Kategorie((String) formularEingaben.get("uebergeordneteKategorie"));

        //Speichern in der Datenbank über Dao Pattern
        categoryDaoJPA.save(category); //<- save methode Nutzen um die Kategorie dann in der DB zu speichern
    }
    @Override
    public void aus_DB_holen_und_eigenschaften_aendern(Map<String, Object> formularEingaben){
        //Die Methode getById aufrufen um den gegenstand mit der jeweiligen Id zu erhalten
        Category category = categoryDaoJPA.getById((Short) formularEingaben.get("kategorieNummer")).get(); //.get() nutzen um aus dem aufruf das Objekt zu erhalten
        System.out.println(category.getBeschreibung());

        //Mithilfe der Getter bzw. Setter Methoden können dann Eigenschaften geändert werden
        category.setBeschreibung("Das ist die Neue Beschreibung !!!!!");
        //category.setVerfuegbar(false);

        //mit der update Methode der Dao API wir der artikel in der Datenbank mit dem neuen überschrieben
        categoryDaoJPA.update(category);
    }

    @Override
    public void erstellen_und_verbinden(){
        //Erstellen einer Kategorie wie in der Methode Kategorie erstellen
        Category aboveCategory = new Category("Test Kategorie","Das ist eine Testbeschreibung !!!!!");

        Category category = categoryDaoJPA.getById(99).get();

        category.setUebergeordnete_Kategorie(aboveCategory.getName());

        categoryDaoJPA.save(aboveCategory);
        categoryDaoJPA.update(category);
    }

    @Override
    public Map<String, Object> lade_Entities(Map<String, Object> formularEingaben) {
        formularEingaben.put("kategorieName", "");
        formularEingaben.put("kategorieBeschreibung", "");
        formularEingaben.put("uebergeordneteKategorie", "");
        return formularEingaben;
    }

}
