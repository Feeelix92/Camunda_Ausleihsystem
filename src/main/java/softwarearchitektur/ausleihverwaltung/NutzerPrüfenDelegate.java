package src.main.java.softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutzerPr�fenDelegate implements JavaDelegate {
	

	public NutzerPr�fenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		
		boolean nutzer_zugelassen = false;
			
		
		execution.setVariable("nutzer_zugelassen", nutzer_zugelassen);
		
	}

}