package softwarearchitektur.mailverwaltung;

import DaoJPA.DaoClasses.User_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.User;
import org.apache.commons.mail.EmailException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RechnungSendenDelegate implements JavaDelegate {

    private DaoJPA<User> userDaoJpa;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        userDaoJpa = new User_DaoJpa();
        int benutzerId = (int)delegateExecution.getVariable("BenutzerId");
        String nutzer = (userDaoJpa.getById(benutzerId).get().getVorname()) + " " + (userDaoJpa.getById(benutzerId).get().getName());
        String email = (String) delegateExecution.getVariable("email");
        sendMail(nutzer,email);
    }

    private void sendMail(String name,String email){
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag " + name + "im Anhang finden sie eine Rechnung zu einer Ausleihe die sie in letzer Zeit zurückgegeben haben. " +
                "Wir bitten Sie die Rechnung nächst möglich zu zahlen, sodass keine extra Kosten anfallen.");
        try {
            new MailService().sendEmail("dennis.zimmer450@gmail.com","Leihservice Rechnung",content);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
