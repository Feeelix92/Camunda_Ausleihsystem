package softwarearchitektur;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class KategorieAnlegen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String kategorieName = "kategorieName";
        String kategorieBeschreibung = "";

        // ToDo Kategorien aus Datenbank prüfen
        String kategorien = "Fernseher";

        kategorieName = (String) delegateExecution.getVariable("kategorieName");
        kategorieBeschreibung = (String) delegateExecution.getVariable("kategorieBeschreibung");

        System.out.println("Kategoriename: " + kategorieName + " Beschreibung: " + kategorieBeschreibung);
        if(kategorieName.equals(kategorien)){
            throw new BpmnError("Kategorie_Vorhanden", "Der gewünschte Kategoriename ist bereits vorhanden");
        //}else{
            // ToDo neue Kategorie in der Datenbank speichern
        }
    }
}
