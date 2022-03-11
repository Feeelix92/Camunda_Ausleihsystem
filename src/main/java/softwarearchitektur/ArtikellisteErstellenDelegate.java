package softwarearchitektur;

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
		
		Map<String, String> artikelliste = new HashMap<String, String>();
		
		artikelliste.put("Laserscanning und Virtuelle Realität: Ein Impuls für die Zukunft von 3D", "Franke, Axel");
		artikelliste.put("Prozess zur Entwicklung optomechatronischer Systeme", "Knöchelmann, Marvin");
		artikelliste.put("Prozess- und Anlagensicherheit", "Hauptmanns, Ulrich");
		

		execution.setVariable("ALLE_ARTIKEL",Variables.objectValue(artikelliste)
				.serializationDataFormat(SerializationDataFormats.JSON)
				.create());

	}

}