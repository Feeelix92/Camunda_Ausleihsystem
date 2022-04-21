package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ausleihdetails")
public class BorrowDetails {

    //PrimalKey
    @Id
    @Column(name = "Ausleihnummer")
    private int ausleihnummer;
    //Id noch hin !!!!
    @Column(name = "Artikelnummer")
    private int artikelnummer;

    //Attribute
    @Column(name = "Preis")
    private BigDecimal preis;

    //ForeignKey´s

    public BorrowDetails(int ausleihnummer, int artikelnummer, BigDecimal preis) {
        this.artikelnummer = artikelnummer;
        this.ausleihnummer = ausleihnummer;
        this.preis = preis;
    }

    public BorrowDetails() {

    }

    public int getAusleihnummer() {
        return ausleihnummer;
    }

    public void setAusleihnummer(int ausleihnummer) {
        this.ausleihnummer = ausleihnummer;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }
}
