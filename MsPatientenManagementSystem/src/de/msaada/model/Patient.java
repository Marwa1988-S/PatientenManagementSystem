package de.msaada.model;


import de.msaada.model.enums.EUserRole;

/**
 * Stellt einen PatientAkte  da.
 * Dies ist eine Modelklasse.
 * @author Marwa Saada
 */

public class Patient extends AUser {
    //region 0.Konstanten

    //endregion


    //region 1. Decl. and Init Attribute
    private String strGender;            // Das Geschlecht eines Patienten
    private String strWeight;            // Das Gewicht eines Patienten
    private String strMedicalHistory;    // Die Krankengeschichte eines Patienten
    private String strDiseases;          // Die aktuelle Erkrankungen eines Patienten
    private String strAllergies;         // Die Allergien eines Patienten
    private String strVaccination;       // Der Impfung eines Patienten

    private String strDate;              // das Datum ,wenn es eine neue Aktenänderung gibt : neuer Empfung, neue Krankheit ....
    //endregion


    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     * beleget alle Attribute
     * mit einem selbst definierten
     * Standardwert , die in ABasemodel definiert wurden
     */
    public Patient()
    {
        super();
        this.strGender = DEF_VALUE_STR;
        this.strWeight = DEF_VALUE_STR;
        this.strMedicalHistory = DEF_VALUE_STR;
        this.strDiseases = DEF_VALUE_STR;
        this.strAllergies = DEF_VALUE_STR;
        this.strVaccination = DEF_VALUE_STR;
        this.strDate = DEF_VALUE_STR;
    }



    /**
     *
     * @param iId                : int : DatenbankId eines PatientObjektes
     * @param strWeight
     * @param strMedicalHistory
     * @param strDiseases
     * @param strAllergies
     * @param strVaccination
     */
    public Patient(int iId,String strWeight, String strMedicalHistory, String strDiseases, String strAllergies, String strVaccination, String strDate ) {
        //super(iId);
        this.strWeight = strWeight;
        this.strMedicalHistory = strMedicalHistory;
        this.strDiseases = strDiseases;
        this.strAllergies = strAllergies;
        this.strVaccination = strVaccination;
        this.strDate= strDate;

    }

    /**
     * 2.Ueberlader Konstruktor
     * zum setzen der Hauptattribute
     *
     * @param iId                : int : DatenbankId eines PatientObjektes
     * @param strUserName        : {@link String} : Benutzername eines Patienten
     * @param strPassword        : {@link String} : Benutzerpasswort eines Patienten
     * @param strFullName        : {@link String} : Komplette Benutzername eines Patienten
     * @param strBdate           : {@link String} : Benutzergeburtstag eines Patienten
     * @param strGender          : {@link String} :  Geschlecht eines Patienten
     */
    public Patient(int iId, String strUserName, String strPassword, String strFullName, String strBdate, String strGender, EUserRole eUserRole) {
        super(iId,strUserName,strPassword,strFullName,strBdate,eUserRole);
        this.strGender = strGender;

    }
    //endregion


    //region 3. getter und setter

    public String getGender() {
        return strGender;
    }

    public void setGender(String strGender) {
        this.strGender = strGender;
    }

    public String getWeight() {
        return strWeight;
    }

    public void setWeight(String strWeight) {
        this.strWeight = strWeight;
    }

    public String getMedicalHistory() {
        return strMedicalHistory;
    }

    public void setMedicalHistory(String strMedicalHistory) {
        this.strMedicalHistory = strMedicalHistory;
    }

    public String getDiseases() {
        return strDiseases;
    }

    public void setDiseases(String strDiseases) {
        this.strDiseases = strDiseases;
    }

    public String getAllergies() {
        return strAllergies;
    }

    public void setAllergies(String strAllergies) {
        this.strAllergies = strAllergies;
    }

    public String getVaccination() {
        return strVaccination;
    }

    public void setVaccination(String strVaccination) {
        this.strVaccination = strVaccination;
    }


    //endregion


    //region 4. toString

    @Override
    public String toString() {
        //super.toString()
        return  "  Weight: " + strWeight
                + " - MedicalHistory: " + strMedicalHistory
                + " - Diseases: " + strDiseases
                + " - Allergies: " + strAllergies
                + " - Vaccination: " + strVaccination
                ;
    }


    //endregion
}
