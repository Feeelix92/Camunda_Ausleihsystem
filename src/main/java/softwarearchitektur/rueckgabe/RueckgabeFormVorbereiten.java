package softwarearchitektur.rueckgabe;

import DaoJPA.EntityClasses.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;

public class RueckgabeFormVorbereiten implements JavaDelegate {
    private ArtikelDataHandler artikelHandler;

    private int artikelnummer;
    private String artikelBeschreibung;
    private String zustandName;
    private String zustandBeschreibung;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init(delegateExecution);
        getRestVariablen(delegateExecution);
        erstelleProzessVariablen(delegateExecution);
    }

    private void init(DelegateExecution delegateExecution){
        artikelHandler = new ArtikelDataHandler();
        String uebergang =  (String)delegateExecution.getVariable("artikelnummer");
        artikelnummer = Integer.parseInt(uebergang);
    }

    private void getRestVariablen(DelegateExecution delegateExecution){
        Article article = artikelHandler.getById(artikelnummer);

        artikelBeschreibung = article.getBeschreibung();
        zustandName = artikelHandler.getConditionName(article.getZustandId());
        zustandBeschreibung = artikelHandler.getConditionDescription(article.getZustandId());
    }

    private void erstelleProzessVariablen(DelegateExecution delegateExecution){
        delegateExecution.setVariable("artikel",artikelBeschreibung);
        delegateExecution.setVariable("alter_Zustand",zustandName);
        delegateExecution.setVariable("zustand_Beschreibung",zustandBeschreibung);
    }
}
