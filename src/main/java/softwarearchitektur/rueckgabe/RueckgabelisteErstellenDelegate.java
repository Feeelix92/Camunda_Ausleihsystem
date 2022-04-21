package softwarearchitektur.rueckgabe;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import softwarearchitektur.artikelverwaltung.ArtikelDataHandler;
import softwarearchitektur.ausleihverwaltung.AusleihDataHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RueckgabelisteErstellenDelegate implements JavaDelegate {

    private ArtikelDataHandler artikelHandler;
    private AusleihDataHandler ausleihHandler;
    private int benutzerId;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init(delegateExecution);
        erstelleRueckgabeliste(delegateExecution);
    }

    private void init(DelegateExecution delegateExecution){
        benutzerId = (int) delegateExecution.getVariable("BenutzerId");
        artikelHandler = new ArtikelDataHandler();
        ausleihHandler = new AusleihDataHandler();
    }

    private void erstelleRueckgabeliste(DelegateExecution delegateExecution){
        String lable = "";
        String value = "";

        Map<String,String> rueckgabeArtikel = new HashMap<String,String>();

        //Alle ausleihen des Nutzers holen
        //List<Borrow> allBorrows = ausleihHandler.getAllUserBorrows(benutzerId);

        //Alle Ausleihdetails des Nutzers holen, damit nur die Artikelnummern
        List<Integer> allArticleNumbers = ausleihHandler.getAllBorrowedArticleNumbers(benutzerId);

        //Alle Artikelbeschreibungen holen
        //In Hashmap hinterlegen (Artikelnummer, Beschreibung)
        for (Integer integer : allArticleNumbers){
            lable = (String)artikelHandler.getById(integer).getBeschreibung();
            value = (String)integer.toString();

            System.out.println(lable + "  " + value);

            rueckgabeArtikel.put(value,lable);
            System.out.println(rueckgabeArtikel.isEmpty());
        }

        System.out.println(lable.getClass());
        System.out.println(rueckgabeArtikel.get(value) + "   " + value);

        //dann als Select setzen !!!!
        delegateExecution.setVariable("RUECKGABE", Spin.JSON(rueckgabeArtikel));
    }

}
