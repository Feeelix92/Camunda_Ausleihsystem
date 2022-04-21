package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Zustand")
public class Condition {

    //PrimalKey
    @Id
    @Column(name = "ZustandID")
    short zustandId;

    //Attribute
    @Column(name = "Name")
    String name;
    @Column(name = "Beschreibung")
    String beschreibung;

    //ForeignKey´s

    public Condition(short zustandId,String name){
        this.zustandId = zustandId;
        this.name = name;
    }

    public Condition(){

    }

    public short getZustandId() {
        return zustandId;
    }

    public void setZustandId(short zustandId) {
        this.zustandId = zustandId;
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
