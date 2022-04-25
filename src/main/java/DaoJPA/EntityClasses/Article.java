package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "artikel")
public class Article {

    //--------- Constructor ---------- //
    public Article(int artikelnummer, String beschreibung, String hersteller_art_nr, BigDecimal einkaufspreis) {
        this.artikelnummer = artikelnummer;
        this.beschreibung = beschreibung;
        this.hersteller_art_nr = hersteller_art_nr;
        this.einkaufspreis = einkaufspreis;
    }

    public Article(String beschreibung, String hersteller_art_nr, BigDecimal einkaufspreis){
        this.beschreibung = beschreibung;
        this.hersteller_art_nr = hersteller_art_nr;
        this.einkaufspreis = einkaufspreis;
    }

    public Article() {}

    //---------- Primal Key ---------- //
    @Id
    @Column(name = "Artikelnummer")
    int artikelnummer;

    //---------- Properties ---------- //
    @Column(name = "Beschreibung")
    String beschreibung;
    @Column(name = "Lagernummer")
    int lagernummer;
    @Column(name = "HerstellerArtNr")
    String hersteller_art_nr;
    @Column(name = "Verfügbar")
    boolean verfuegbar;
    @Column(name = "Einkaufspreis")
    BigDecimal einkaufspreis;

    //--------- Foreign Key ---------- //
    @Column(name = "ArtikeltypID")
    short artikelTypId;
    @Column(name = "ZustandID")
    short zustandId;

    //------- Getter & Setter -------- //
    public int getArtikelnummer() {
        return artikelnummer;
    }
    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public int getLagernummer() {
        return lagernummer;
    }
    public void setLagernummer(int lagernummer) {
        this.lagernummer = lagernummer;
    }
    public String getHersteller_art_nr() {
        return hersteller_art_nr;
    }
    public void setHersteller_art_nr(String hersteller_art_nr) {
        this.hersteller_art_nr = hersteller_art_nr;
    }
    public boolean isVerfuegbar() {
        return verfuegbar;
    }
    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }
    public BigDecimal getEinkaufspreis() {
        return einkaufspreis;
    }
    public void setEinkaufspreis(BigDecimal einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }
    public short getArtikelTypId() {
        return artikelTypId;
    }
    public void setArtikelTypId(short artikelTypId) {
        this.artikelTypId = artikelTypId;
    }
    public short getZustandId() {
        return zustandId;
    }
    public void setZustandId(short zustandId) {
        this.zustandId = zustandId;
    }
}
