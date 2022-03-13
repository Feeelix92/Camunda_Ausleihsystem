package softwarearchitektur;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtikelPrüfenDelegate implements JavaDelegate {
	

	public ArtikelPrüfenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		int artikelNr = (int) execution.getVariable("artikel");
		
		ArrayList<Integer> verfügbareArtikel = new ArrayList<Integer>();
		verfügbareArtikel.add(1);
		verfügbareArtikel.add(2);
		
		boolean verfügbar = false;
			
		verfügbar = verfügbareArtikel.contains(artikelNr);
		
		execution.setVariable("artikelNr", artikelNr);
		execution.setVariable("artikel_verfügbar", verfügbar);
		
	}

}