package softwarearchitektur.ausleihverwaltung;

import DaoJPA.EntityClasses.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class VerfuegbarkeitAendern implements JavaDelegate {

    private ArtikelDataHandler artikelDataHandler;
    public VerfuegbarkeitAendern() {

    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        int artikelNr = (int) execution.getVariable("artikelNr");

        artikelDataHandler = new ArtikelDataHandler();

        Article article = artikelDataHandler.getById(artikelNr);
        article.setVerfuegbar(false);

        artikelDataHandler.updateArticle(article);

        //TODO: ausleih und auslehdetails

    }

}
