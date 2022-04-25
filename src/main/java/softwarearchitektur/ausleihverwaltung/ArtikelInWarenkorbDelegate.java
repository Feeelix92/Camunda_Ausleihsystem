package softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtikelInWarenkorbDelegate implements JavaDelegate {
	

	public ArtikelInWarenkorbDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		ArrayList<Integer> warenkorb = new ArrayList<Integer>();
		warenkorb.add((int) execution.getVariable("artikelNr"));
		
		HashMap<Integer, String> artikelListe = (HashMap<Integer, String>) execution.getVariable("ARTIKEL_HASH");
		
		String artikelBestaetigen = artikelListe.get((int) execution.getVariable("artikelNr"));
		execution.setVariable("artikelBestaetigen", artikelBestaetigen);
		
	}

}
