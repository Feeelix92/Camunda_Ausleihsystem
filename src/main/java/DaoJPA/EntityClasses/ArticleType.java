package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artikeltyp")
public class ArticleType {

    //--------- Constructor ---------- //
    public ArticleType(String name, String beschreibung){
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public ArticleType(){}

    //---------- Primal Key ---------- //
    @Id
    @Column(name = "ArtikeltypID")
    short artikelTypId;

    //---------- Properties ---------- //
    @Column(name = "Name")
    String name;
    @Column(name = "Beschreibung")
    String beschreibung;
    //@Column(name = "Bild")
    //TODO Bild Blob (siehe ggf. IE Prozess da habe ich das auch schonmal gemacht)!!!!!!

    //--------- Foreign Key ---------- //

    //------- Getter & Setter -------- //
    public short getArtikelTypId() {
        return artikelTypId;
    }
    public void setArtikelTypId(short artikelTypId) {
        this.artikelTypId = artikelTypId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
