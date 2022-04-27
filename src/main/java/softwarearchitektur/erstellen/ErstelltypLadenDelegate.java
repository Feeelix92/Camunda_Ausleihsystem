package softwarearchitektur.erstellen;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
public class ErstelltypLadenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution){

        Map<String, String> erstellTypen = new HashMap<String, String>();
        erstellTypen.put("artikel", "Artikel");
        erstellTypen.put("artikeltyp", "Artikeltyp");
        erstellTypen.put("kategorie", "Kategorie");
        // Erstelltyp
        delegateExecution.setVariable("erstellTyp", Variables.objectValue(erstellTypen)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create());
    }
}
