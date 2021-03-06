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
        System.out.println(artikelTypId);
        delegateExecution.setVariable("ArtikeltypID",artikelTypId);

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        int cur_ArtikelID = (int) delegateExecution.getVariable("ArtikeltypID");
        variables.put("ArtikeltypID", cur_ArtikelID);

        rtm.startProcessInstanceByMessage("MerkzettelStarten", variables);
        variables.clear();

        

//        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();
//
//        //TODO das richtige muss noch auskommentiert werden !!!!!!!
////        //Wenn Process gestartet werden soll
////        runtimeService.startProcessInstanceByMessage("ArtikeltypID",variables);
////
////        //Wenn Process nur auf antwort wartet...
////        runtimeService.correlateMessage("Merkzettel_artikelTypId",variables);
    }
}
