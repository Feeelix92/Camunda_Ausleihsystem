package softwarearchitektur;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NutzerSendEmailDelegate implements JavaDelegate{
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag, es tut uns leid Ihnen mitteilen zu müssen, dass Sie für das Leihen dieses Artikels nicht zugelassen sind.");
        new MailService().sendEmail((String)execution.getVariable("mail"),"Artikel-Verfügbarkeit", content);  
	}

}
