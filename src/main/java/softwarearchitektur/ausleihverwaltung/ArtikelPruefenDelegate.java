package softwarearchitektur.ausleihverwaltung;

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
		
		ArrayList<Integer> verfuegbareArtikel = new ArrayList<Integer>();
		verfuegbareArtikel.add(1);
		verfuegbareArtikel.add(2);
		
		boolean verfuegbar = false;
			
		verfuegbar = verfuegbareArtikel.contains(artikelNr);
		
		execution.setVariable("artikelNr", artikelNr);
		execution.setVariable("artikel_verfuegbar", verfuegbar);
		
	}

}
