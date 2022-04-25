package softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RueckgabedatumAktualisierenDelegate implements JavaDelegate {

    private AusleihDataHandler ausleihHandler;
    private int ausleihnummer;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init(delegateExecution);
        ausleihHandler.updateAusleihe_mitRueckgabedatum(ausleihnummer);
    }

    private void init(DelegateExecution delegateExecution){
        ausleihHandler = new AusleihDataHandler();
        ausleihnummer = (int)delegateExecution.getVariable("ausleihnummer");
    }
}
