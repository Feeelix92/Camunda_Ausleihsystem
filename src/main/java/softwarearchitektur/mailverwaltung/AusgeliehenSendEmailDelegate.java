package softwarearchitektur.mailverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AusgeliehenSendEmailDelegate implements JavaDelegate{


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag, wir freuen uns, dass Sie sich entschieden haben unseren Leihservice zu nutzen! Hiermit bestätigen wir, dass Ihr Artikel erfolgreich ausgeliehen wurde");
        new MailService().sendEmail((String)execution.getVariable("mail"),"Ausleihbestätigung", content);
    }

}
