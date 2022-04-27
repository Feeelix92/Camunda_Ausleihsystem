package softwarearchitektur.ausleihverwaltung;

import DaoJPA.EntityClasses.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.w3c.dom.UserDataHandler;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;
import softwarearchitektur.userverwaltung.NutzerDataHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class VerfuegbarkeitAendern implements JavaDelegate {

    private ArtikelDataHandler artikelDataHandler;
    private AusleihDataHandler ausleihDataHandler;
    private NutzerDataHandler nutzerDataHandler;
    public VerfuegbarkeitAendern() {

    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        int artikelNr = (int) execution.getVariable("artikelNr");
        String nutzername = (String) execution.getVariable("nutzername");
        String mail = (String) execution.getVariable("mail");

        artikelDataHandler = new ArtikelDataHandler();
        nutzerDataHandler = new NutzerDataHandler();
        ausleihDataHandler = new AusleihDataHandler();

        int benutzerId = nutzerDataHandler.userByUsernameAndEMail(nutzername,mail).getBenutzerID();

        Article article = artikelDataHandler.getById(artikelNr);
        article.setVerfuegbar(false);

        artikelDataHandler.updateArticle(article);

        Date ausleihDatum = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(ausleihDatum);
        cal.add(Calendar.MONTH, 1);
        Date faelligkeitsdatum = cal.getTime();
        java.sql.Date ausleihDatumSQL = new java.sql.Date(ausleihDatum.getTime());
        java.sql.Date faelligkeitsdatumSQL = new java.sql.Date(faelligkeitsdatum.getTime());
        ausleihDataHandler.insertNewAusleihe( benutzerId ,artikelNr, ausleihDatumSQL, faelligkeitsdatumSQL);


    }

}
