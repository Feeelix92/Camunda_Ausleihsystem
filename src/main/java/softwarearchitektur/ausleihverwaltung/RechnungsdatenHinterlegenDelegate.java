package softwarearchitektur.ausleihverwaltung;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class RechnungsdatenHinterlegenDelegate implements JavaDelegate {
    private AusleihDataHandler ausleihHandler;
    private double preis;
    private int ausleihnummer;
    private int artikelnummer;
    private HashMap<Integer,Integer> zuordnungsListe;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init(delegateExecution);
        datenInDatenbank(delegateExecution);
    }

    private void init(DelegateExecution delegateExecution){
        ausleihHandler = new AusleihDataHandler();
        preis = (Double) delegateExecution.getVariable("preis");
        artikelnummer = Integer.parseInt((String)delegateExecution.getVariable("artikelnummer"));

        String zuordnungsString = (String)delegateExecution.getVariable("ausleihnummerList");

        //convert JSON to map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            zuordnungsListe = objectMapper.readValue(zuordnungsString, new TypeReference<HashMap<Integer, Integer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        ausleihnummer = zuordnungsListe.get(artikelnummer);
        delegateExecution.setVariable("ausleihnummer",ausleihnummer);
    }

    private void datenInDatenbank(DelegateExecution delegateExecution){
        //wie bekomme ich jetzt noch die ausleihnummer !!!!
        ausleihHandler.sichereRechnungDaten(BigDecimal.valueOf(preis),ausleihnummer);
    }
}
