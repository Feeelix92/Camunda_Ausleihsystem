package softwarearchitektur;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendEmailDelegate implements JavaDelegate{
	
    private static final String MAIL = "erik.damm97@googlemail.com";
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag, es tut uns leid Ihnen mitteilen zu müssen, dass Ihr gewählter Artikel nicht verfügbar ist.");
        new MailService().sendEmail(MAIL,"Artikel-Verfügbarkeit", content);  
	}

}
