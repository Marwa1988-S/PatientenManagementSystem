package de.msaada.model;


/**
 * Stellt einen Termin für einen Patienten da.
 * Dies ist eine Modelklasse.
 * @author Marwa Saada
 */
public class Appointment extends ABaseModel{
    //region 0.Konstanten

    //endregion


    //region 1. Decl. and Init Attribute
    private int iPatientId;
    private int iDoctorId;
    private String strDate;
    private String strTime;
    //endregion


    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     * beleget alle Attribute
     * mit einem selbst definierten
     * Standardwert , die in ABasemodel definiert wurden
     */
     public Appointment()
     {
         super();
         this.iPatientId = DEF_VALUE_INT;
         this.iDoctorId = DEF_VALUE_INT;
         this.strDate = DEF_VALUE_STR;
         this.strTime = DEF_VALUE_STR;

     }

    /**
     * 1.Ueberlader Konstruktor
     * zum setzen der Hauptattribute
     * @param iId          : int : DatenbankId eines AppointmentObjektes
     * @param iPatientId   : int : DatenbankId des Patienten zu welchem dieses Termin (Appointment) gehört
     * @param iDoctorId    : int : DatenbankId des Doktors zu welchem dieses Termin (Appointment) gehört
     * @param strDate      : {@link String} : Das Datum dieses Termin (Appointment)
     * @param strTime    : {@link String} : Das Status dieses Termin (Appointment)
     */
     public Appointment(int iId, int iPatientId, int iDoctorId, String strDate, String strTime)
     {
       super(iId);
       this.iPatientId = iPatientId;
       this.iDoctorId = iDoctorId;
       this.strDate = strDate;
       this.strTime = strTime;
     }
    //endregion


    //region 3. getter und setter

    public int getPatientId() {
        return iPatientId;
    }

    public void setPatientId(int iPatientId) {
        this.iPatientId = iPatientId;
    }

    public int getDoctorId() {
        return iDoctorId;
    }

    public void setDoctorId(int iDoctorId) {
        this.iDoctorId = iDoctorId;
    }

    public String getDate() {
        return strDate;
    }

    public void setDate(String strDate) {
        this.strDate = strDate;
    }

    public String getTime() {
        return strTime;
    }

    public void setTime(String strStatus) {
        this.strTime = strStatus;
    }

    public String getAllAttributesAsCsvString() {

        return this.iId + SPLIT_CHAR
                + this.iPatientId + SPLIT_CHAR
                + this.iDoctorId + SPLIT_CHAR
                + this.strDate + SPLIT_CHAR
                + this.strTime;
    }

    public void setAllAttributesFromCsvString(String strCsvString) {
        String[] strAttributeValues = strCsvString.split(SPLIT_CHAR);

        this.iId        = Integer.valueOf(strAttributeValues[0]);
        this.iPatientId = Integer.valueOf(strAttributeValues[1]);
        this.iDoctorId  = Integer.valueOf(strAttributeValues[2]);;
        this.strDate    = strAttributeValues[3];
        this.strTime    = strAttributeValues[4];

    }

    //endregion


    //region 4. toString

    @Override
    public String toString() {
        return super.toString()
                + "Patient Nummer: " + iPatientId
                + "    Datum: " + strDate +
                  "    um: " + strTime;
    }

    //endregion
}
