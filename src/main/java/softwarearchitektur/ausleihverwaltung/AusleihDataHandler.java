package softwarearchitektur.ausleihverwaltung;

import DaoJPA.DaoClasses.BorrowDetails_DaoJpa;
import DaoJPA.DaoClasses.Borrow_DaoJpa;
import DaoJPA.DaoClasses.Invoice_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Borrow;
import DaoJPA.EntityClasses.BorrowDetails;
import DaoJPA.EntityClasses.Invoice;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.spin.Spin;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class AusleihDataHandler {

    private static DaoJPA<Borrow> borrowDao;
    private static DaoJPA<BorrowDetails> borrowDetailsDao;
    private static Invoice_DaoJpa invoiceDao;

    public AusleihDataHandler(){
        borrowDao = new Borrow_DaoJpa();
        borrowDetailsDao = new BorrowDetails_DaoJpa();
        invoiceDao = new Invoice_DaoJpa();
    }

    public List<Borrow> getAllUserBorrows(int benutzerId){
        List<Borrow> borrowList = borrowDao.getAll();
        List<Borrow> userBorrowList = new ArrayList<Borrow>();

        for(Borrow borrow : borrowList){
//            System.out.println(borrow.getBenutzerId());
//            System.out.println(benutzerId);
//            System.out.println(borrow.getRueckgabedatum());
            if (borrow.getBenutzerId() == benutzerId && borrow.getRueckgabedatum() == null){
                userBorrowList.add(borrow);
            }
        }
        return userBorrowList;
    }

    public List<Integer> getAllBorrowedArticleNumbers(int benutzerId, DelegateExecution delegateExecution){
        List<Integer> articleNumbers = new ArrayList<Integer>();
        List<Borrow> borrows = getAllUserBorrows(benutzerId);

        Map<Integer,Integer> ausleihnummerList = new HashMap<>();

        for (Borrow borrow : borrows){
            BorrowDetails borrowDetails = borrowDetailsDao.getById(borrow.getAusleihnummer()).get();
            Integer articlenumber = borrowDetails.getArtikelnummer();
            articleNumbers.add(articlenumber);
            ausleihnummerList.put(articlenumber,borrowDetails.getAusleihnummer());
        }

        delegateExecution.setVariable("ausleihnummerList", Spin.JSON(ausleihnummerList));

        return  articleNumbers;
    }

    public void sichereRechnungDaten (BigDecimal betrag,int ausleihnummer){
        //get max rechnungsnummer
        int highest = 0;
        List<Invoice> list = invoiceDao.getAll();
        for (Invoice invoice : list){
            if (invoice.getRechnungsnummer() > highest){
                highest = invoice.getRechnungsnummer();
            }
        }


        Invoice invoice = new Invoice();
        //set Rechnungsnummer --> sollte vllt. automatisch gemacht werden oder ggf. auch aus sql direkt genommen werden
        invoice.setRechnungsnummer(highest+1);

        //set Rechnungsdatum mit aktuellem datum
        //Date rechnungsdatum = (Date)Calendar.getInstance().getTime();
        Date rechnungsdatum = new Date(Calendar.getInstance().getTimeInMillis());
        invoice.setRechnungsdatum(rechnungsdatum);

        //set Fälligkeitsdatum mit aktuellem datum + 14 Tagen (2 Wochen)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,14);
        Date faelligkeitsdatum = new Date(calendar.getTimeInMillis());
        invoice.setFaelligkeit(faelligkeitsdatum);

        //set betrag und bezahlt value
        invoice.setBetrag(betrag);
        invoice.setBezahlt(false);

        invoiceDao.save(invoice);

        //set Rechnungsnummer to ausleihe
        Borrow borrow = borrowDao.getById(ausleihnummer).get();
        borrow.setRechnungsnummer(highest+1);
        borrowDao.save(borrow);
    }

    public void updateAusleihe_mitRueckgabedatum(int ausleihnummer){
        Borrow borrow = borrowDao.getById(ausleihnummer).get();
        Date rueckgabedatum = new Date(Calendar.getInstance().getTimeInMillis());

        borrow.setRueckgabedatum(rueckgabedatum);
        borrowDao.save(borrow);
    }
}
