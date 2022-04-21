package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "ausleihe")
public class Borrow {

    //PrimalKey
    @Id
    @Column(name = "Ausleihnummer")
    private int ausleihnummer;

    //Attribute
    @Column(name = "Ausleihdatum")
    private Date ausleihdatum;
    @Column(name = "Rückgabedatum")
    private Date rueckgabedatum;
    @Column(name = "Fälligkeitsdatum")
    private Date faelligkeitsdatum;

    //ForeignKey´s
    @Column(name = "BenutzerID")
    private int benutzerId;
    @Column(name = "Gutschriftnummer")
    private Integer gutschriftnummer;
    @Column(name = "Rechnungsnummer")
    private Integer rechnungsnummer;
    @Column(name = "Reklamationsnummer")
    private Integer reklamationsnummer;
    @Column(name = "Mahnungsnummer")
    private Integer mahnungsnummer;

    public Borrow(int ausleihnummer, int benutzerId, Date ausleihdatum, Date faelligkeitsdatum){
        this.ausleihnummer = ausleihnummer;
        this.benutzerId = benutzerId;
        this.ausleihdatum = ausleihdatum;
        this.faelligkeitsdatum = faelligkeitsdatum;
    }

    public Borrow(){

    }

    public int getAusleihnummer() {
        return ausleihnummer;
    }

    public void setAusleihnummer(int ausleihnummer) {
        this.ausleihnummer = ausleihnummer;
    }

    public Date getAusleihdatum() {
        return ausleihdatum;
    }

    public void setAusleihdatum(Date ausleihdatum) {
        this.ausleihdatum = ausleihdatum;
    }

    public Date getRueckgabedatum() {
        return rueckgabedatum;
    }

    public void setRueckgabedatum(Date rueckgabedatum) {
        this.rueckgabedatum = rueckgabedatum;
    }

    public Date getFaelligkeitsdatum() {
        return faelligkeitsdatum;
    }

    public void setFaelligkeitsdatum(Date faelligkeitsdatum) {
        this.faelligkeitsdatum = faelligkeitsdatum;
    }

    public int getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(int benutzerId) {
        this.benutzerId = benutzerId;
    }

    public Integer getGutschriftnummer() {
        return gutschriftnummer;
    }

    public void setGutschriftnummer(Integer gutschriftnummer) {
        this.gutschriftnummer = gutschriftnummer;
    }

    public Integer getRechnungsnummer() {
        return rechnungsnummer;
    }

    public void setRechnungsnummer(Integer rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    public Integer getReklamationsnummer() {
        return reklamationsnummer;
    }

    public void setReklamationsnummer(Integer reklamationsnummer) {
        this.reklamationsnummer = reklamationsnummer;
    }

    public Integer getMahnungsnummer() {
        return mahnungsnummer;
    }

    public void setMahnungsnummer(Integer mahnungsnummer) {
        this.mahnungsnummer = mahnungsnummer;
    }
}
