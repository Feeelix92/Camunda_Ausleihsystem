package softwarearchitektur.mailverwaltung;

import DaoJPA.DaoClasses.User_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

public class RechnungSendenDelegate implements JavaDelegate {

    private DaoJPA<User> userDaoJpa;
    private File rechnung;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        userDaoJpa = new User_DaoJpa();
        int benutzerId = (int)delegateExecution.getVariable("BenutzerId");
        String nutzer = (userDaoJpa.getById(benutzerId).get().getVorname()) + " " + (userDaoJpa.getById(benutzerId).get().getName());
        String email = (String) delegateExecution.getVariable("email");

        ByteArrayInputStream inputStream = (ByteArrayInputStream) delegateExecution.getVariable("RechnungDokument");

        String path = "src/main/resources/" + benutzerId + "_RechnungDokument.pdf";
        File targetFile = new File(path);
        FileUtils.copyInputStreamToFile(inputStream,targetFile);
        rechnung = targetFile;
        sendMail(nutzer,email);
        deletePDF(path);
    }

    private void sendMail(String name,String email){
        String content = MailTemplate.mailTemplate;
        content = content.replace("<#content>","Guten Tag " + name + " im Anhang finden sie eine Rechnung zu einer Ausleihe die sie in letzer Zeit zurückgegeben haben. " +
                "Wir bitten Sie die Rechnung nächst möglich zu zahlen, sodass keine extra Kosten anfallen.");
        try {
            new MailService().sendEmail("iegruppe3testmail@gmail.com","Leihservice Rechnung",content,rechnung);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    private void deletePDF(String filePath) {
        File deleteFile = new File(filePath);
        try {
            if (deleteFile.delete()) {
                System.out.println(deleteFile.getName() + "deleted");
            } else {
                System.out.println("Zahlungseingangs PDF gesendet Deletion failed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("PDF gelöscht");
    }
}
