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

    public void changeArticleAvailability(boolean availability, Article article){
        article.setVerfuegbar(availability);
    }

    public void changeCondition(short conditionId, Article article){
        article.setZustandId(conditionId);
    }

    public void updateArticle(Article article){
        artikelDao.save(article);
    }

    public String getConditionName(short conditionId){
        return conditionDao.getById(conditionId).get().getName();
    }

    public String getConditionDescription(short conditionId){
        return conditionDao.getById(conditionId).get().getBeschreibung();
    }
}
