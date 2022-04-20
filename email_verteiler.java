package softwarearchitektur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

public class email_verteiler implements JavaDelegate {
    public email_verteiler() {

    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int cur_ArtikelID = (int) execution.getVariable("ArtikeltypID");
        ArrayList<String> email_list = new ArrayList<String>();

        Connection connection;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ausleihe?user=root&password=root");
        Statement stm_mzn = connection.createStatement();
        String query = "SELECT Merkzettelnummer FROM Ausleihe.Merkzetteldetails WHERE ArtikeltypID ="+ cur_ArtikelID;
        System.out.println(query);
        ResultSet rs_Merkzettelnummer = stm_mzn.executeQuery(query);
        while(rs_Merkzettelnummer.next()){
            Statement stm_user = connection.createStatement();
            // BenutzerID Query
            String user_query = "SELECT BenutzerID FROM Ausleihe.Merkzettel WHERE Merkzettelnummer=" +
                     + rs_Merkzettelnummer.getInt(1);
            System.out.println(user_query);

            ResultSet get_user = stm_user.executeQuery(user_query);
            
            Statement stm_email = connection.createStatement();
            // Email Query
            if(get_user.next()) {
            String email_query = "SELECT Email FROM Ausleihe.Benutzer WHERE BenutzerID=" + get_user.getInt(1);
            System.out.println(email_query);
            
            ResultSet get_email = stm_email.executeQuery(email_query);
            get_email.next();
            String cur_email = get_email.getString(1);
            email_list.add(cur_email);
            System.out.println(cur_email);
            }



        execution.setVariable("email_list",Variables.objectValue(email_list)
                .create());

    }
    }
    }
