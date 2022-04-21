package softwarearchitektur.ausleihverwaltung;

import DaoJPA.DaoClasses.BorrowDetails_DaoJpa;
import DaoJPA.DaoClasses.Borrow_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Borrow;
import DaoJPA.EntityClasses.BorrowDetails;

import java.util.ArrayList;
import java.util.List;

public class AusleihDataHandler {

    private static DaoJPA<Borrow> borrowDao;
    private static DaoJPA<BorrowDetails> borrowDetailsDao;

    public AusleihDataHandler(){
        borrowDao = new Borrow_DaoJpa();
        borrowDetailsDao = new BorrowDetails_DaoJpa();
    }

    public List<Borrow> getAllUserBorrows(int benutzerId){
        List<Borrow> borrowList = borrowDao.getAll();
        List<Borrow> userBorrowList = new ArrayList<Borrow>();

        for(Borrow borrow : borrowList){
            if (borrow.getBenutzerId() == benutzerId && borrow.getRueckgabedatum() == null){
                userBorrowList.add(borrow);
            }
        }
        return borrowList;
    }

    public List<Integer> getAllBorrowedArticleNumbers(int benutzerId){
        List<Integer> articleNumbers = new ArrayList<Integer>();
        List<Borrow> borrows = getAllUserBorrows(benutzerId);

        for (Borrow borrow : borrows){
            Integer articlenumber = borrowDetailsDao.getById(borrow.getAusleihnummer()).get().getArtikelnummer();
            articleNumbers.add(articlenumber);
        }

        return  articleNumbers;
    }
}
