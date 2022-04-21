package softwarearchitektur.artikelverwaltung;

import DaoJPA.DaoClasses.Article_DaoJpa;
import DaoJPA.DaoClasses.Condition_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Article;
import DaoJPA.EntityClasses.Condition;

public class ArtikelDataHandler {

    private static DaoJPA<Article> artikelDao;
    private static DaoJPA<Condition> conditionDao;

    public ArtikelDataHandler(){
        artikelDao = new Article_DaoJpa();
        conditionDao = new Condition_DaoJpa();
    }

    public Article getById(int artikelnummer){
        //TODO einen isPresent() check !?!?
        Article article = artikelDao.getById(artikelnummer).get();
        return article;
    }

    public void changeArticleAvailability(boolean availability, int artikelnummer){
        Article article = artikelDao.getById(artikelnummer).get();
        article.setVerfuegbar(availability);
        artikelDao.save(article);
    }

    public void changeCondition(short conditionId, int arikelnummer){
        Article article = artikelDao.getById(arikelnummer).get();
        article.setZustandId(conditionId);
        artikelDao.save(article);
    }

    public String getConditionName(short conditionId){
        return conditionDao.getById(conditionId).get().getName();
    }

    public String getConditionDescription(short conditionId){
        return conditionDao.getById(conditionId).get().getBeschreibung();
    }
}
