package softwarearchitektur;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

public class SendEmailDelegate implements JavaDelegate{
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
        String content = MailTemplate.mailTemplate;
        Integer artikelNr =  (Integer) execution.getVariable("artikelNr");
        HashMap<Integer, String> alleArtikel = (HashMap<Integer, String>) execution.getVariable("ALLE_ARTIKEL"); 
        String artikelTitel = alleArtikel.get(artikelNr);
        System.out.println(artikelTitel);
        content = content.replace("<#content>","Guten Tag, es tut uns leid Ihnen mitteilen zu müssen, dass Ihr gewählter Artikel nicht verfügbar ist.");
        new MailService().sendEmail((String)execution.getVariable("mail"),"Artikel-Verfügbarkeit", content);  
	}

}
