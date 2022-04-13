package softwarearchitektur.erstellen;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

@Named
public class ErstellenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution){
        ErstellenFactory factory = new ErstellenFactory();

        Map<String, Object> formularEingaben = delegateExecution.getVariables();
        String erstellTyp = (String) formularEingaben.get("erstellTyp");
        Erstellen erstellen = factory.neueInhalteErstellen(erstellTyp);
        erstellen.lade_entitaeten(formularEingaben);
        formularEingaben.put("speichern", false);
        delegateExecution.setVariables(formularEingaben);

        if((boolean) formularEingaben.get("speichern")){
            erstellen.erstellen_und_speichern(formularEingaben);
        }
    }
}
