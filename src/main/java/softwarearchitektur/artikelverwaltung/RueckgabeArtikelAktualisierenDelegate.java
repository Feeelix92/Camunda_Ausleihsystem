package softwarearchitektur.artikelverwaltung;

import DaoJPA.EntityClasses.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RueckgabeArtikelAktualisierenDelegate implements JavaDelegate {
    private ArtikelDataHandler artikelHandler;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init();
//        artikelWiederVerfuegbar((int)delegateExecution.getVariable("artikelnummer"));
//        artikelZustandAktualisieren((short)delegateExecution.getVariable("new_condition"),
//                                    (int)delegateExecution.getVariable("artikelnummer"));
        artikelAktualisieren(Integer.parseInt((String)delegateExecution.getVariable("artikelnummer")),
                            Short.parseShort((String)delegateExecution.getVariable("neuer_Zustand")));
    }

    private void init(){
        artikelHandler = new ArtikelDataHandler();
    }


    private void artikelAktualisieren(int artikelnummer,short conditionId){
        Article article = artikelHandler.getById(artikelnummer);
        if (article.getZustandId() != conditionId){
            article.setZustandId(conditionId);
        }
        article.setVerfuegbar(true);

        artikelHandler.updateArticle(article);
    }
}
