package de.msaada.model;


import de.msaada.model.enums.EUserRole;

/**
 * Stellt einen Arzt  da.
 * Dies ist eine Modelklasse.
 * @author Marwa Saada
 */

public class Doctor extends AUser {
    //region 0.Konstanten

    //endregion


    //region 1. Decl. and Init Attribute
    private String strSpecialization;
    private String strEmail;
    //endregion


    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     * beleget alle Attribute
     * mit einem selbst definierten
     * Standardwert , die in ABasemodel definiert wurden
     */
    public Doctor()
    {
        super();
        this.strSpecialization = DEF_VALUE_STR;
        this.strEmail = DEF_VALUE_STR;
    }

    /**
     * 1.Ueberlader Konstruktor
     * zum setzen der Hauptattribute
     *
     * @param iId                : int : DatenbankId eines DoctorObjektes
     * @param strUserName        : {@link String} : Benutzername eines Arztes
     * @param strPassword        : {@link String} : Benutzerpasswort eines Arztes
     * @param strFullName        : {@link String} : Komplette Benutzername eines Arztes
     * @param strBdate           : {@link String} : Benutzergeburtstag eines Arztes
     * @param strSpecialization : {@link String} :  Spezialisierung eines Arzt
     */
    public Doctor(int iId, String strUserName, String strPassword, String strFullName, String strBdate, String strSpecialization,String strEmail) {
         super(iId,strUserName,strPassword,strFullName,strBdate, EUserRole.Doctor);
         this.strSpecialization = strSpecialization;
         this.strEmail = strEmail;

    }
    //endregion


    //region 3. getter und setter

    public String getSpecialization() {
        return strSpecialization;
    }

    public void setSpecialization(String strSpecialization) {
        this.strSpecialization = strSpecialization;
    }

    public String getEmail() {
        return strEmail;
    }

    public void setEmail(String strEmail) {
        this.strEmail = strEmail;
    }
//endregion


    //region 4. toString

    @Override
    public String toString() {
        return  super.toString()
                + " - Specialization: " + strSpecialization
                + " - Email: " + strEmail;
    }


    //endregion
}
