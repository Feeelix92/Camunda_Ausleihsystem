package softwarearchitektur.erstellen;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import javax.inject.Named;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class ErstelltypLadenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution){

        List<String> erstellTypen = Arrays.asList("artikel", "artikeltyp", "kategorie");
        // Erstelltyp
        delegateExecution.setVariable("erstellTyp", erstellTypen);
    }
}
