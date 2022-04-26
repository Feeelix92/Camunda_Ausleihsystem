package softwarearchitektur.mailverwaltung;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MerkzettelBenachrichtigungDelegate implements JavaDelegate{


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int cur_ArtikelID = (int) execution.getVariable("ArtikeltypID");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ausleihe?user=root&password=root");
        Statement stm_mzn = connection.createStatement();
        String query = "SELECT Name FROM Ausleihe.Artikeltyp WHERE ArtikeltypID ="+ cur_ArtikelID;
        System.out.println(query);
        ResultSet rs_name = stm_mzn.executeQuery(query);
        rs_name.next();
        String cur_name = rs_name.getString(1);
        ArrayList<String> email_list = (ArrayList<String>) execution.getVariable("email_list");
        if (email_list != null) {
            for (String cur_email : email_list) {
                String content = MailTemplate.mailTemplate;
                content = content.replace("<#content>", "Guten Tag, der von Ihnen vermerkte Artikel, " + cur_name + " ist wieder verfügbar.");
                new MailService().sendEmail(cur_email, "Artikel-Verfügbarkeit", content);
            }
        }else{
            System.out.println("Kein Kunde hat diesen Artikel auf seinem Merkzettel!!!");
        }

    }
}
