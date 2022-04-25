package softwarearchitektur.ausleihsystem;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtikelPruefenDelegate implements JavaDelegate {
	

	public ArtikelPruefenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		int artikelNr = (int) execution.getVariable("artikel");
		
		ArrayList<Integer> verf�gbareArtikel = new ArrayList<Integer>();
		verf�gbareArtikel.add(1);
		verf�gbareArtikel.add(2);
		
		boolean verf�gbar = false;
			
		verf�gbar = verf�gbareArtikel.contains(artikelNr);
		
		execution.setVariable("artikelNr", artikelNr);
		execution.setVariable("artikel_verfuegbar", verf�gbar);
		
	}

}
