package softwarearchitektur.merkzettel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class MerkzettelArtikelAuswaehlen implements JavaDelegate {
    public MerkzettelArtikelAuswaehlen(){

    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ausleihe?user=root&password=root");

        Statement stm = connection.createStatement();
        String query = "SELECT Name FROM Ausleihe.Artikeltyp";
        ResultSet Artikel = stm.executeQuery(query);

        Map<String, String> alle_Artikel = new HashMap<String, String>();

        while(Artikel.next()) {
            alle_Artikel.put(Artikel.getString(1), Artikel.getString(1));
        }

        connection.close();
        execution.setVariable("ALLE_ARTIKEL",Variables.objectValue(alle_Artikel)
                .serializationDataFormat(SerializationDataFormats.JSON)
                .create());

    }
}
