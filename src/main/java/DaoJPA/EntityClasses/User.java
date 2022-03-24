package DaoJPA.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "benutzer")
public class User {
    //PrimalKey
    @Id
    @Column(name = "BenutzerID")
    private int benutzerID;

    //Attribute
    @Column(name = "Name")
    private String name;
    @Column(name = "Vorname")
    private String vorname;
    @Column(name = "Email")
    private String email;
    @Column(name = "Geburtsdatum")
    private String geburtsdatum;
    @Column(name = "Postleitzahl")
    private String postleitzzahl;
    @Column(name = "Stadt")
    private String stadt;
    @Column(name = "Strasse")
    private String strasse;
    @Column(name = "Hausnummer")
    private String hausnummer;
    @Column(name = "Telefonnummer")
    private String telefonnummer;
    @Column(name = "Verifiziert")
    private boolean verifiziert;

    //TODO da PW eigentlich nicht so gespeichert und Abgerufen werden sollte
    @Column(name = "Passwort")
    private String passwort;

    //ForeignKey´s
    @Column(name = "BenutzerstatusID")
    private int benutzerstatusID;
    @Column(name = "BenutzerscoreID")
    private int benutzerscoreID;
    @Column(name = "Rollennummer")
    private int rollennummer;

    public User(int benutzerID, String name, String vorname, String email) {
        this.benutzerID = benutzerID;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
    }

    public User() {

    }

    public int getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(int benutzerID) {
        this.benutzerID = benutzerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getPostleitzzahl() {
        return postleitzzahl;
    }

    public void setPostleitzzahl(String postleitzzahl) {
        this.postleitzzahl = postleitzzahl;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public boolean isVerifiziert() {
        return verifiziert;
    }

    public void setVerifiziert(boolean verifiziert) {
        this.verifiziert = verifiziert;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getBenutzerstatusID() {
        return benutzerstatusID;
    }

    public void setBenutzerstatusID(int benutzerstatusID) {
        this.benutzerstatusID = benutzerstatusID;
    }

    public int getBenutzerscoreID() {
        return benutzerscoreID;
    }

    public void setBenutzerscoreID(int benutzerscoreID) {
        this.benutzerscoreID = benutzerscoreID;
    }

    public int getRollennummer() {
        return rollennummer;
    }

    public void setRollennummer(int rollennummer) {
        this.rollennummer = rollennummer;
    }
}
