package softwarearchitektur.ausleihverwaltung;

import DaoJPA.EntityClasses.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ArtikellisteErstellenDelegate implements JavaDelegate {

	private ArtikelDataHandler artikelDataHandler;

	public ArtikellisteErstellenDelegate() {

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		artikelDataHandler = new ArtikelDataHandler();

		int artikelNummer;
		String artikelBeschreibung;

		Map<Integer, String> artikelliste = new HashMap<Integer, String>();

		List<Article> alleArtikel = artikelDataHandler.getAll();

		for (Article article : alleArtikel){
			artikelNummer = article.getArtikelnummer();
			artikelBeschreibung = article.getBeschreibung();
			artikelliste.put(artikelNummer,artikelBeschreibung);
		}
		

		execution.setVariable("ALLE_ARTIKEL",Variables.objectValue(artikelliste)
				.serializationDataFormat(SerializationDataFormats.JSON)
				.create());
		
		execution.setVariable("ARTIKEL_HASH", artikelliste);

	}
}
