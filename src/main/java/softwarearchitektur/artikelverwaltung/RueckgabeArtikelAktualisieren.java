package softwarearchitektur.artikelverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RueckgabeArtikelAktualisieren implements JavaDelegate {
    private ArtikelDataHandler artikelHandler;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init();
        artikelWiederVerfuegbar((int)delegateExecution.getVariable("artikelnummer"));
        artikelZustandAktualisieren((short)delegateExecution.getVariable("new_condition"),
                                    (int)delegateExecution.getVariable("artikelnummer"));
    }

    private void init(){
        artikelHandler = new ArtikelDataHandler();
    }

    private void artikelWiederVerfuegbar(int artikelnummer){
        artikelHandler.changeArticleAvailability(true,artikelnummer);
    }

    private void artikelZustandAktualisieren(short conditionId,int artikelnummer){
        artikelHandler.changeCondition(conditionId,artikelnummer);
    }
}
