package softwarearchitektur;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class ArtikelPrüfenDelegate implements JavaDelegate {
	

	public ArtikelPrüfenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String artikelNr = (String) execution.getVariable("artikel");
		
		String artikelNr_geprüft = artikelNr + "_geprüft";
		
		execution.setVariable("artikel_geprüft", artikelNr_geprüft);
		execution.setVariable("artikel_verfügbar", false);
		
		
	}

}