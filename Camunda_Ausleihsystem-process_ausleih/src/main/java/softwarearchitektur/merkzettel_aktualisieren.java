package softwarearchitektur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class merkzettel_aktualisieren implements JavaDelegate{
	private int merkzettelnummer;
	private int ArtikeltypID;
    public merkzettel_aktualisieren(){

    }
    @Override
    public void execute(DelegateExecution execution) throws Exception{
        String Artikelname = (String) execution.getVariable("Artikelname");
        System.out.println(Artikelname);
        int BenutzerID = (int) execution.getVariable("BenutzerID");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ausleihe?user=root&password=root");

        // Merkzettel generieren
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO Ausleihe.Merkzettel (BenutzerID) VALUES ('" + BenutzerID + "')";
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs_Merkzettelnummer = stmt.getGeneratedKeys();
		if(rs_Merkzettelnummer != null && rs_Merkzettelnummer.next()){
			merkzettelnummer = rs_Merkzettelnummer.getInt(1);
	       }
        execution.setVariable("Merkzettelnummer", merkzettelnummer);

        // ArtikeltypID ermitteln
        Statement typ_stmt = connection.createStatement();
        String typ_sql = "SELECT ArtikeltypID FROM Ausleihe.Artikeltyp WHERE Name="+'"'+Artikelname+'"';
        ResultSet rs_artikeltypID = typ_stmt.executeQuery(typ_sql);
        if(rs_artikeltypID.next()) {
        	ArtikeltypID = rs_artikeltypID.getInt(1);
        	System.out.println(ArtikeltypID);
        }


        // Merkzettel aktualisieren
        Statement dt_stmt = connection.createStatement();
        String dt_sql = "INSERT INTO Ausleihe.Merkzetteldetails (Merkzettelnummer, ArtikeltypID) VALUES (" + merkzettelnummer +", "+ ArtikeltypID + ")";
        System.out.println(dt_sql);
        dt_stmt.executeUpdate(dt_sql);
        
		// Datenbank schließen
		connection.close();

    }
}
