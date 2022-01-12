package de.msaada.logic;

import de.msaada.logic.db.DaoDbTblAppointment;
import de.msaada.logic.db.DaoDbTblPatientRecord;
import de.msaada.logic.db.DaoDbTblUser;
import de.msaada.model.AUser;
import de.msaada.model.Appointment;
import de.msaada.model.Doctor;
import de.msaada.model.Patient;
import de.msaada.model.enums.EUserRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Threadsicherer Zugriff auf die
 * Datenbank.
 */
public class DbManager {
    //region 0.Konstanten
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    private static final String DB_SERVER_IP_ADDRESS = "127.0.0.1";
    private static final String DB_NAME              = "/db_patient_datenbank_ms";

    private static final String DB_CONNECTION_URL = "jdbc:mariadb://" + DB_SERVER_IP_ADDRESS + DB_NAME;

    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PW   = "";
    //endregion


    //region 1. Decl. and Init Attribute
    private static DbManager instance;
    private DaoDbTblUser daoDbTblUser;
    private DaoDbTblPatientRecord daoDbTblPatientRecord;
    private DaoDbTblAppointment daoDbTblAppointment;


    private AUser loggedInUser;

    // TODO delete
    private Doctor loggedInDoctor;
    private Patient loggedInPatient;

   public List<Patient> allPatients;
    //endregion


    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     */
    private DbManager() {
        //DAO Objekte generieren
        this.daoDbTblUser = new DaoDbTblUser();
        this.daoDbTblPatientRecord = new DaoDbTblPatientRecord();
        this.daoDbTblAppointment = new DaoDbTblAppointment();
        this.allPatients = new ArrayList<>();
    }
    //endregion

    //region 3. Get Instanze

    /**
     * Gibt einzige Instanz zurück
     *
     * @return
     */
    public static synchronized DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }

        return instance;
    }

    //endregion

    //region 4. Getter und Setter


    public AUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(AUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

   /* public Doctor getLoggedInDoctor() {
        return loggedInDoctor;
    }

    public void setLoggedInDoctor(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
    }

    public Patient getLoggedInPatient() {
        return loggedInPatient;
    }

    public void setLoggedInPatient(Patient loggedInPatient) {
        this.loggedInPatient = loggedInPatient;
    }*/

    //endregion

    //region 5. Database Connection
    /**
     * Gibt eine generiert Datenbankverbindung mit Lese(r) als auch Schreibrechten(w)
     * zurueckt oder null sollte etwas schiefgelaufen sein.
     *
     * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank mit rw - Rechten
     */
    private Connection getRwDbConnection() {
        Connection rwDbConnection = null;

        try {
            //STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            rwDbConnection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_USER_PW);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rwDbConnection;
    }
    //endregion

    //region 6. Tabellen CRUD Operation

    /**
     * Doctor zum einfuegen in die Datenbank
     * @param DoctorToInsert
     */
    public void InsertNewDoctorIntoDbTable(Doctor DoctorToInsert) {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
           this.daoDbTblUser.insertDataRecordIntoDbTbl(getRwDbConnection(), DoctorToInsert);
        }
    }

    /**
     * Speichert Die Patient Daten : (Name , UserName , PassWord ... ), und auch die Medizinische Daten (vaccination ,disease ..)
     * @param patientToInsert
     */
    public void InsertNewPatientIntoDbTable(Patient patientToInsert) {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            this.daoDbTblUser.insertDataRecordIntoDbTbl(getRwDbConnection(), patientToInsert);
            int patientId = this.daoDbTblUser.getLastUserIdFromDbTb(getRwDbConnection());
            patientToInsert.setId(patientId);
            this.daoDbTblPatientRecord.insertDataRecordIntoDbTbl(dbRwConnection,patientToInsert);

        }
    }

    /**
     * Speichert Die Medizinische Daten (vaccination ,disease ..)
     * @param patientToInsert
     */
    public void InsertNewPatientRecordIntoDbTable(Patient patientToInsert) {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            this.daoDbTblPatientRecord.insertDataRecordIntoDbTbl(dbRwConnection,patientToInsert);

        }
    }

    //Table Appointment

    /**
     *
     * @param appointmentToInsert
     */
    public void InsertNewAppointmentIntoDbTable(Appointment appointmentToInsert) {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            this.daoDbTblAppointment.insertDataRecordIntoDbTbl(dbRwConnection,appointmentToInsert);

        }
    }

    /**
     * Checkt die Userdaten auf der Datenbank
     *
     * @param strUserName : {@link String} : Benutzername
     * @param strUserPw   : {@link String} : Userpasswort
     *
     * @return validUserData : true : Stimmt false : Stimmt nicht
     */
    public AUser getUserByNameAndPwFromDbTable(String strUserName, String strUserPw) {
        AUser userFromDb = null;
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            userFromDb =   this.daoDbTblUser.getSpecificUserDataRecordFromDbTbByUsernameAndPassword(getRwDbConnection(),strUserName,strUserPw);
            System.out.println("connection is reight");
        }
        return userFromDb;
        //TODO Daten nachher von der Db beschaffen und Role prüfen ob doktor oder Patient

    }

    /**
     * Checkt die Userdaten auf der Datenbank
     *
     *
     * @return validUserData : true : Stimmt false : Stimmt nicht
     */
    public AUser getUserByUserIdFromDbTable(int iUserId) {
        AUser userFromDb = null;
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            userFromDb =   this.daoDbTblUser.getSpecificDataRecordFromDbTblById(getRwDbConnection(),iUserId);
            System.out.println("connection is reight");
        }
        return userFromDb;
    }
    /**
     */
    public List<Appointment> getAllAppointmentsFromDbTable(){
       //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();
        List<Appointment> allAppointmentsfromDb = new ArrayList<>();;
        //Sicherheitscheck
        if (dbRwConnection != null) {

            allAppointmentsfromDb =  this.daoDbTblAppointment.getAllDataRecordsFromDbTbl(getRwDbConnection());
            System.out.println("connection is reight");
        }
        return allAppointmentsfromDb;
    }

    /**
     *
     * @return
     */
    public List<AUser> getAllPatientsFromDbTable() {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();
        List<AUser> allPatientsFromDb = new ArrayList<>();;
        //Sicherheitscheck
        if (dbRwConnection != null) {

            allPatientsFromDb =  this.daoDbTblUser.getAllPatientsDataRecordsFromDbTbl(getRwDbConnection());
            System.out.println("connection is reight");
        }
        return allPatientsFromDb;

        //   return this.allPatients;
    }

    public List<Appointment> getAppointmentsByPatientIdFromDbTable(int iPatientId) {

        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();
        List<Appointment> allAppointmentsOfPatientfromDb = new ArrayList<>();;
        //Sicherheitscheck
        if (dbRwConnection != null) {

            allAppointmentsOfPatientfromDb =  this.daoDbTblAppointment.getAllDataRecordsFromDbTblByPatientId(getRwDbConnection(),iPatientId);

        }
        return allAppointmentsOfPatientfromDb;
    }

    public List<Patient> getAllPatientRecordsByPatientIdFromDbTable(int iPatientId) {
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();
        List<Patient> allMedicalRecordsOfPatientfromDb = new ArrayList<>();;
        //Sicherheitscheck
        if (dbRwConnection != null) {

            allMedicalRecordsOfPatientfromDb =  this.daoDbTblPatientRecord.getAllDataRecordsFromDbTblByPatientId(getRwDbConnection(),iPatientId);
        }
        return allMedicalRecordsOfPatientfromDb;
    }
    //endregion



    //---------------------------------------------------------------------------------------------
    //TODO alle diese Funktionen mit DB verbinden und Daten von Tabellen auslesen
    //TODO und Komment
  //Table User

    /**
     *
     * @param strFirstName
     * @param strLastName
     * @return
     */
    public int getDoctorIdByFullNameFromDbTable(String strFirstName, String strLastName) {
        int userId = -1;
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            userId =   this.daoDbTblUser.getUserIdFromDbTbByFullName(getRwDbConnection(),strFirstName,strLastName);
            System.out.println("connection is reight");
        }
        return userId;
    }



    /**
     *
     * @param strFirstName
     * @param strLastName
     * @return
     */
    public int getPatientIdByFullNameFromDbTable(String strFirstName, String strLastName) {
        int userId = -1;
        //Neue Verbindung erstellen
        Connection dbRwConnection = getRwDbConnection();

        //Sicherheitscheck
        if (dbRwConnection != null) {
            userId =   this.daoDbTblUser.getUserIdFromDbTbByFullName(getRwDbConnection(),strFirstName,strLastName);

        }
        return userId;
    }





   /* public Patient getPatientByFullNameFromDbTable(String strFirstName, String strLastName){
        Patient patientFromTable = new Patient();
        return patientFromTable;
    }*/

}
