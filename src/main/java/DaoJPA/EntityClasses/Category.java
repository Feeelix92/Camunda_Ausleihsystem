package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kategorie")
public class Category {

    //--------- Constructor ---------- //
    public Category(short kategorienummer, String name, String beschreibung) {
        this.kategorienummer = kategorienummer;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Category(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Category() {}

    //---------- Primal Key ---------- //
    @Id
    @Column(name = "Kategorienummer")
    short kategorienummer;

    //---------- Properties ---------- //
    @Column(name = "Name")
    String name;
    @Column(name = "Beschreibung")
    String beschreibung;

    //--------- Foreign Key ---------- //
    @Column(name = "Übergeordnete Kategorie")
    String uebergeordnete_Kategorie;

    //------- Getter & Setter -------- //
    public short getKategorienummer() {
        return kategorienummer;
    }
    public void setKategorienummer(short kategorienummer) {
        this.kategorienummer = kategorienummer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    public String getUebergeordnete_Kategorie() {
        return uebergeordnete_Kategorie;
    }
    public void setUebergeordnete_Kategorie(String uebergeordnete_Kategorie) { this.uebergeordnete_Kategorie = uebergeordnete_Kategorie; }
}
