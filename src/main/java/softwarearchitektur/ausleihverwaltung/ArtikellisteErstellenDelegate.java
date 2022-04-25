package softwarearchitektur.ausleihverwaltung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import java.util.HashMap;
import java.util.Map;

public class ArtikellisteErstellenDelegate implements JavaDelegate {
	

	public ArtikellisteErstellenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Map<Integer, String> artikelliste = new HashMap<Integer, String>();
		
		artikelliste.put(1, "Laserscanning und Virtuelle Realität: Ein Impuls für die Zukunft von 3D");
		artikelliste.put(2, "Prozess zur Entwicklung optomechatronischer Systeme");
		artikelliste.put(3, "Prozess- und Anlagensicherheit");
		

		execution.setVariable("ALLE_ARTIKEL",Variables.objectValue(artikelliste)
				.serializationDataFormat(SerializationDataFormats.JSON)
				.create());
		
		execution.setVariable("ARTIKEL_HASH", artikelliste);

	}
}
