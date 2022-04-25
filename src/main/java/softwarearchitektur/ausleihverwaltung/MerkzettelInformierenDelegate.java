package softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;

import java.util.HashMap;
import java.util.Map;

public class MerkzettelInformierenDelegate implements JavaDelegate {

    private ArtikelDataHandler artikelHandler;
    private int artikelnummer;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init(delegateExecution);
        benachrichtigeMerkzettel(delegateExecution);
    }

    private void init(DelegateExecution delegateExecution){
        artikelHandler = new ArtikelDataHandler();
        artikelnummer = Integer.parseInt((String)delegateExecution.getVariable("artikelnummer"));
    }

    private void benachrichtigeMerkzettel(DelegateExecution delegateExecution){
        int artikelTypId = artikelHandler.getById(artikelnummer).getArtikelTypId();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("artikelTypId", artikelTypId);
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();

        //TODO das richtige muss noch auskommentiert werden !!!!!!!
//        //Wenn Process gestartet werden soll
//        runtimeService.startProcessInstanceByMessage("Merkzettel_artikelnummer",variables);
//
//        //Wenn Process nur auf antwort wartet...
//        runtimeService.correlateMessage("Merkzettel_artikelTypId",variables);
    }
}
