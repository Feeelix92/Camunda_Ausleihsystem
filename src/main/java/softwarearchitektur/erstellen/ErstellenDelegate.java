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

        // Erstelltyp
        String erstellTyp = (String) delegateExecution.getVariable("Erstelltyp");
        Erstellen erstellen = factory.neueInhalteErstellen(erstellTyp);

        // Entitäten aus DB laden
        Map<String, Object> formularEingaben = delegateExecution.getVariables();
        erstellen.erstellen_und_speichern(formularEingaben);
        System.out.println("OK");
    }
}
