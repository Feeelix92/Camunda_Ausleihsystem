package softwarearchitektur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class notifyObserver implements JavaDelegate {
    private static final String HOST = "smtp.googlemail.com";
    private static final String USER = "iegruppe3testmail@gmail.com";
    private static final String PWD = "qwertz!23";
    private static final Integer PORT = 465;

    public notifyObserver(){

    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int idbewerber = (int) execution.getVariable("idbewerber");
        String Studiengang = (String) execution.getVariable("Studiengang");
        String bearbeitungsstatus = (String) execution.getVariable("bearbeitungsstatus");

        switch(bearbeitungsstatus) {
            case "Bewerbung nicht vorhanden":
                Connection connection;
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Immatrikulation?user=root&password=root");
                Statement stmt = connection.createStatement();

                break;

            case "Bewerbung vorhanden":
                break;
    }
}
}
