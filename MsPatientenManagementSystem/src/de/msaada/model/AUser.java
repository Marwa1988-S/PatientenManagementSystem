package de.msaada.model;

import de.msaada.model.enums.EUserRole;

/**
 * Stellt einen User des Programms da.
 * Dies ist eine Abstraktklasse , davon erben die Doctor und Patient klassen.
 * @author Marwa Saada
 */
public class AUser extends ABaseModel {

    //region 0.Konstanten


    //endregion


    //region 1. Decl. and Init Attribute
    protected String strUserName;
    protected String strPassword;
    protected String strFullName;
    protected String strBdate;
    protected EUserRole eUserRole;
    protected String strStreet;
    protected int iHouseNr;
    protected int iZip;
    protected String strCity;
    //endregion


    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     * beleget alle Attribute
     * mit einem selbst definierten
     * Standardwert , die in ABasemodel definiert wurden
     */
    public AUser()
    {
        super();
        this.strUserName = DEF_VALUE_STR;
        this.strPassword = DEF_VALUE_STR;
        this.strFullName = DEF_VALUE_STR;
        this.strBdate = DEF_VALUE_STR;
        this.eUserRole = EUserRole.DEFAULT;
        this.strStreet = DEF_VALUE_STR;
        this.iHouseNr = DEF_VALUE_INT;
        this.iZip = DEF_VALUE_INT;
        this.strCity = DEF_VALUE_STR;

    }

    /**
     * 1.Ueberlader Konstruktor
     * zum setzen der Hauptattribute
     *
     * @param iId          : int : DatenbankId eines UserObjektes
     * @param strUserName  : {@link String} : Benutzername eines Arztes, eines Patienten (User)
     * @param strPassword  : {@link String} : Benutzerpasswort eines Arztes, eines Patienten (User)
     * @param strFullName  : {@link String} : Komplette Benutzername eines Arztes, eines Patienten (User)
     * @param strBdate     : {@link String} : Benutzergeburtstag eines Arztes, eines Patienten (User)
     */
    public AUser(int iId, String strUserName, String strPassword, String strFullName, String strBdate , EUserRole eUserRole)
    {
        super(iId);
        this.strUserName = strUserName;
        this.strPassword = strPassword;
        this.strFullName = strFullName;
        this.strBdate = strBdate;

        this.eUserRole = eUserRole;
        this.strStreet = DEF_VALUE_STR;
        this.iHouseNr = DEF_VALUE_INT;
        this.iZip = DEF_VALUE_INT;
        this.strCity = DEF_VALUE_STR;

    }
    //endregion


    //region 3. getter und setter

    public String getUserName() {
        return strUserName;
    }

    public void setUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getHashedPassword() {
        return strPassword;
    }

    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getFullName() {
        return strFullName;
    }

    public void setFullName(String strFullName) {
        this.strFullName = strFullName;
    }

    public String getBdate() {
        return strBdate;
    }

    public void setBdate(String strBdate) {
        this.strBdate = strBdate;
    }

    public EUserRole getUserRole() {
        return eUserRole;
    }

    public void setUserRole(EUserRole eUserRole) {
        this.eUserRole = eUserRole;
    }

    public String getStreet() {
        return strStreet;
    }

    public void setStreet(String strStreet) {
        this.strStreet = strStreet;
    }

    public int getHouseNr() {
        return iHouseNr;
    }

    public void setHouseNr(int iHouseNr) {
        this.iHouseNr = iHouseNr;
    }

    public int getZip() {
        return iZip;
    }

    public void setZip(int iZip) {
        this.iZip = iZip;
    }

    public String getCity() {
        return strCity;
    }

    public void setCity(String strCity) {
        this.strCity = strCity;
    }

    //endregion


    //region 4. toString


    public String toString1() {
        return "AUser{" +
                "strUserName='" + strUserName + '\'' +
                ", strPassword='" + strPassword + '\'' +
                ", strFullName='" + strFullName + '\'' +
                ", strBdate='" + strBdate + '\'' +
                ", eUserRole=" + eUserRole +
                ", strStreet='" + strStreet + '\'' +
                ", iHouseNr=" + iHouseNr +
                ", iZip=" + iZip +
                ", strCity='" + strCity + '\'' +
                "} " + super.toString();
    }

    @Override
    public String toString() {
        return   super.toString()
                + strFullName
                + " - " ;

    }
    //endregion
}
