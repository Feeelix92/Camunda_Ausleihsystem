package softwarearchitektur.ausleihverwaltung;

import DaoJPA.EntityClasses.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import softwarearchitektur.userverwaltung.NutzerDataHandler;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutzerPruefenDelegate implements JavaDelegate {
	
	private NutzerDataHandler nutzerDataHandler;

	public NutzerPruefenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {		

		nutzerDataHandler = new NutzerDataHandler();
		String nutzername = execution.getVariable("nutzername").toString();
		String mail = execution.getVariable("mail").toString();
		User user = nutzerDataHandler.userByUsernameAndEMail(nutzername, mail);

		int benutzerscore = user.getBenutzerscoreID();
		boolean nutzer_zugelassen;
		nutzer_zugelassen = benutzerscore != 2;
			
		
		execution.setVariable("nutzer_zugelassen", nutzer_zugelassen);
		
	}

}