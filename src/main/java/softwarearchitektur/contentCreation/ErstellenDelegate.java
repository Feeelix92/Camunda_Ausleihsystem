package softwarearchitektur.contentCreation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

@Named
public class ErstellenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution){
        AnlegenFactory factory = new AnlegenFactory();

        Map<String, Object> formularEingaben = delegateExecution.getVariables();
        String anlageTyp = (String) formularEingaben.get("anlageTyp");
        Anlegen anlegen = factory.neueInhalteAnlegen(anlageTyp);
        anlegen.lade_Entities(formularEingaben);
        formularEingaben.put("speichern", false);
        delegateExecution.setVariables(formularEingaben);

        if((boolean) formularEingaben.get("speichern")){
            anlegen.erstellen_und_speichern(formularEingaben);
        }
    }
}
