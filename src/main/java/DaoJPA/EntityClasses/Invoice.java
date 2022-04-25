package DaoJPA.EntityClasses;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "rechnung")
public class Invoice {

    //PrimalKey
    @Id
    @Column(name = "Rechnungsnummer")
    private int rechnungsnummer;

    //Attribute
    @Column(name = "Betrag")
    private BigDecimal betrag;
    @Column(name = "Rechnungsdatum")
    private Date rechnungsdatum;
    @Column(name = "Fälligkeit")
    private Date faelligkeit;
    @Column(name = "bezahlt")
    private Boolean bezahlt;

    //ForeignKey´s

    public Invoice(int rechnungsnummer, BigDecimal betrag, Date rechnungsdatum, Date faelligkeit, Boolean bezahlt) {
        this.rechnungsnummer = rechnungsnummer;
        this.betrag = betrag;
        this.rechnungsdatum = rechnungsdatum;
        this.faelligkeit = faelligkeit;
        this.bezahlt = bezahlt;
    }

    public Invoice(){

    }

    public int getRechnungsnummer() {
        return rechnungsnummer;
    }

    public void setRechnungsnummer(int rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public Date getRechnungsdatum() {
        return rechnungsdatum;
    }

    public void setRechnungsdatum(Date rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    public Date getFaelligkeit() {
        return faelligkeit;
    }

    public void setFaelligkeit(Date faelligkeit) {
        this.faelligkeit = faelligkeit;
    }

    public Boolean getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(Boolean bezahlt) {
        this.bezahlt = bezahlt;
    }
}
