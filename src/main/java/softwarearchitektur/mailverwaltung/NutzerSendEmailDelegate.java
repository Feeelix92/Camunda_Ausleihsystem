package softwarearchitektur.mailverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NutzerSendEmailDelegate implements JavaDelegate{
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag, es tut uns leid Ihnen mitteilen zu m�ssen, dass Sie f�r das Leihen dieses Artikels nicht zugelassen sind.");
        new MailService().sendEmail((String)execution.getVariable("mail"),"Artikel-Verf�gbarkeit", content);  
	}

}