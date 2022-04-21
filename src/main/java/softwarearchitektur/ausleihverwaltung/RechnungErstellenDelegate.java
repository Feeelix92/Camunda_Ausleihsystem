package softwarearchitektur.ausleihverwaltung;

import DaoJPA.DaoClasses.User_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;
import softwarearchitektur.userverwaltung.NutzerDataHandler;

import java.util.ArrayList;
import java.util.List;

public class RechnungErstellenDelegate implements JavaDelegate {

    private ArtikelDataHandler artikelHandler;
    private NutzerDataHandler nutzerHandler;
    private DaoJPA<User> userDaoJpa = new User_DaoJpa();

    private String nuzter;
    private String email;
    private List<String> artikel;
    private List<Integer> artikelnummer;
    private List<Double> preis;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init();
        sammleRechnungDaten(delegateExecution);
    }

    private void init(){
        artikelHandler = new ArtikelDataHandler();
        nutzerHandler = new NutzerDataHandler();
        artikel = new ArrayList<>();
        artikelnummer = new ArrayList<>();
        preis = new ArrayList<>();
    }

    //TODO Daten die ich hier brauche!!!!
    //Voller Name des Benutzers --> habe ich als variable
    //Email                     --> habe ich als variable
    //Artikel als Liste<String> --> habe ich auch als vairable
    //Artikelnummer als Liste<Integer> ---->
    //Preise als Liste<Double>  --> hab ich nicht !?!?!?

    private void sammleRechnungDaten(DelegateExecution delegateExecution){
        int benutzerId = (int)delegateExecution.getVariable("BenutzerId");
        nuzter = (userDaoJpa.getById(benutzerId).get().getVorname()) + " " + (userDaoJpa.getById(benutzerId).get().getName());
        email = (String)delegateExecution.getVariable("email");

        int artikelnummerUebergang = (int)delegateExecution.getVariable("artikelnummer");
        artikelnummer.add(artikelnummerUebergang);
        preis.add(artikelHandler.getById(artikelnummerUebergang).getEinkaufspreis().doubleValue());
    }

    private void erstelleRechnung(){
        invoice invoice = new invoice();
        invoice.setData(nuzter,email,artikel,artikelnummer,preis);
        invoice.WriteInvoice();
    }
}
