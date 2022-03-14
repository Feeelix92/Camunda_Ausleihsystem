package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

@Named
public class ProzessAnlegen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        AnlegenFactory factory = new AnlegenFactory();

        Map<String, Object> formularEingaben = delegateExecution.getVariables();
        String anlageTyp = (String) formularEingaben.get("anlageTyp");

        // ToDo Prozess anpassen, Auswahl Kategorie, Gegenstand oder Artikeltyp ergänzen
        Anlegen anlegen = factory.neueInhalteAnlegen(anlageTyp);
        anlegen.checkData(formularEingaben);
    }
}
