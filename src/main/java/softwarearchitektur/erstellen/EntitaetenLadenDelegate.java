package softwarearchitektur.erstellen;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

@Named
public class EntitaetenLadenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution){
        ErstellenFactory factory = new ErstellenFactory();

        // Erstelltyp
        String erstellTyp = (String) delegateExecution.getVariable("erstellTyp");
        Erstellen erstellen = factory.neueInhalteErstellen(erstellTyp);

        // Entitäten aus DB laden
        Map<String, Object> formularEingaben = delegateExecution.getVariables();
        erstellen.lade_entitaeten(formularEingaben);
        delegateExecution.setVariables(formularEingaben);
    }
}
