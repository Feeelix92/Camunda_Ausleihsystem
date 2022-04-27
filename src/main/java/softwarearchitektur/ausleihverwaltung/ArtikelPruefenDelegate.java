package softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtikelPruefenDelegate implements JavaDelegate {

	private ArtikelDataHandler artikelDataHandler;

	public ArtikelPruefenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		artikelDataHandler = new ArtikelDataHandler();

		int artikelNr = (int) execution.getVariable("artikel");

		boolean verfuegbar = artikelDataHandler.getById(artikelNr).isVerfuegbar();
		
		execution.setVariable("artikelNr", artikelNr);
		execution.setVariable("artikel_verfuegbar", verfuegbar);
		
	}

}
