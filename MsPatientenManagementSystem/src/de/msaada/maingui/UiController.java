package de.msaada.maingui;

import de.msaada.helper.CsvFileHandler;
import de.msaada.logic.DbManager;
import de.msaada.model.AUser;
import de.msaada.model.Appointment;
import de.msaada.model.Doctor;
import de.msaada.model.Patient;
import de.msaada.model.enums.EUserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Nimmit alle Klicks und Events
 * von der Gui entegen und leitet
 * die weitere Logik ein
 */
public class UiController {

    //region temporal

    List<Appointment> allAppointment;
    List<Doctor> allDoctors;
    //endregion
    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init Attribute
    //---------Algemein benutzt------------------
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtUserPw;

   @FXML
   private Label lblLogin;


    @FXML
    private TextField txtPatientFName;

    @FXML
    private TextField txtPatientLName;

    //diese werden in add_patient_view benutzt---

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtWeight;

   @FXML
    private TextArea txtMHistory;

    @FXML
    private TextArea txtDiseases;

    @FXML
    private TextArea txtAllergies;

    @FXML
    private TextArea txtVaccination;

    //----------werden in register_view (neuen Doktor) benutzt--------
    @FXML
    private TextField txtDoctorFName;
    @FXML
    private TextField txtDoctorLName;
    @FXML
    private TextField txtDoctorSpecialization;
    @FXML
    private TextField txtDoctorEmail;
    //----------------------------------------------------------------

    //diese werden in doctor_view benutzt---
    @FXML
    private DatePicker dpAppointmentDate;

    @FXML
    private TextField txtTime;

    @FXML
    private Label lblAppointment;

    @FXML
    private ListView<Appointment> lvAppointments;
    private ObservableList<Appointment> observableAppointmentsFromDb;

    @FXML
    private ListView<AUser> lvAllPatients;
    private ObservableList<AUser> observablePatientsFromDb;

    @FXML
    private ListView<Patient> lvAllMedicalRecordsOFPatient;
    private ObservableList<Patient> observablePatientRecordsFromDb;

    @FXML
    private ListView<Appointment> lvAppsPlan;
    private ObservableList<Appointment> observableAppointmentplanByPatientFromDb;

    //--------------------------------------

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnAddnewPatient;

    @FXML
    private Label lblAngelegt;

    @FXML
    private Button btnAddnewAppointment;

    @FXML
    private Label lblAllPatients;

    @FXML
    private HBox hbPatientName;

    @FXML
    private Label lblUserMsg;

    @FXML
    private TabPane tabPanePatientsData;

    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblStreet;
    @FXML
    private Label lblHnumber;

    private GuiRegisterView     guiRegisterView;
    private GuiDoctorView       guiDoctorView;
    private GuiAddPatientView   guiAddPatientView;
    private GuiPatientView      guiPatientView;
    private GuiAllPatientsView  guiAllPatientsView;

    /*
     * Kann nach bedarf ausgetauscht werden.
     * Die Objekte jedoch braucht mann nur einmal
     */
    private static Stage primaryStage;
    private static Parent rootParent;
    private static Scene mainScene;
    //endregion

    //region 2. Konstruktoren

    /**
     * Standardkonstruktor
     */
    public UiController() {
        //allPatients    = new ArrayList<>();
        allAppointment = new ArrayList<>();
        allDoctors     = new ArrayList<>();
        /////////
    this.guiRegisterView    = new GuiRegisterView();
    this.guiDoctorView      = new GuiDoctorView();
    this.guiAddPatientView  = new GuiAddPatientView();
    this.guiPatientView     = new GuiPatientView();
    this.guiAllPatientsView = new GuiAllPatientsView();
    }



    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        //GUI Elemente befuellen vor der Sichtbarkeit des Fensters

    }
*/

    //endregion


    //region 3. Open Guis
    /**
     * Oeffnet die LoginMaske
     *
     * @param rootStage : {@link Stage} : Hauptbuehne aus der {@link Main}
     */
    public static void openLogInScreen(Stage rootStage) {
        try {
            primaryStage = rootStage;
            rootParent   = FXMLLoader.load(UiController.class.getResource("/log_in_layout.fxml"));

            mainScene = new Scene(rootParent, 400,300);

           // loadLogInScreenCssStyles();

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Login  PatientenDaten MS");

            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void showRegisterView(String strRegisterText) {
        this.guiRegisterView.openRegisterView(rootParent, mainScene, primaryStage, strRegisterText);
    }

    /**
     * Aendert die Gui ,mit der neuen Gui kann ein Arzt neuen Termin einlegen ,
     * alle Termine sehen
     */
    private void showDoctorView(String strDoctorname){
       this.guiDoctorView.openDoctorView(rootParent, mainScene, primaryStage, strDoctorname);
    }

    /**
     * Aendert die Gui ,mit der neuen Gui kann ein Arzt neuen Patienten einlegen , alle Patienten sehen
     */
    private void showAddPatientView(String strViewTitle){
        this.guiAddPatientView.openAddPatientView(rootParent, mainScene, primaryStage, strViewTitle);
    }


    private void showPatientView(String strViewTitle) {
        this.guiPatientView.openPatientView(rootParent, mainScene, primaryStage, strViewTitle);
    }

    private void showAllPatientsView(String strViewTitle) {
        this.guiAllPatientsView.openAllPatientsView(rootParent, mainScene, primaryStage, strViewTitle);
    }

    //endregion


    //region 4. Klickevents
    public void login() {

        String strUserName = txtUserName.getText();
        String strUserPw   = txtUserPw.getText();

        if (!strUserName.isEmpty() && !strUserPw.isEmpty())
        {
            AUser userFromDbTbl = DbManager.getInstance().getUserByNameAndPwFromDbTable(strUserName,this.getSha256HashValueAsHexStringFromPw(strUserPw));
            if (userFromDbTbl!=null)
            {
                this.lblLogin.setText("");
                if(userFromDbTbl.getUserRole().equals(EUserRole.Doctor)) {
                    showDoctorView("Hallo Doctor " + userFromDbTbl.getFullName());
                    DbManager.getInstance().setLoggedInUser(userFromDbTbl);
                }
                else if(userFromDbTbl.getUserRole().equals(EUserRole.PATIENT)) // userFromDbTbl is Patient
                {
                    showPatientView("Hallo " + userFromDbTbl.getFullName());
                    DbManager.getInstance().setLoggedInUser( userFromDbTbl);
                }
            }
            else
            {
                this.lblLogin.setText("Email or Password is wrong");
            }

        }
         else
        {
            this.lblLogin.setText("Email and Password are required");
        }
    }


    /**
     * Oeffnet die Registrierurng Gui ,damit ein Doktor ihre Daten eingeben kann, dann wird in diesem System registriert
     */
    public void register() {
       showRegisterView("neuen Doktor registrieren");
    }

    /**
     * Oeffnet die AddPatientView Gui
     */
    public void changeToAddPatientView(){
        showAddPatientView("Neuen Patienten einlegen");
    }

    /**
     * Oeffnet die AllPatientsView Gui
     */
    public void changeToAllPatientsView(){
        showAllPatientsView("Alle Patienten");
       // this.updateLvAllPatients();
    }

    /**
     * zeigt alle Patienten diesre Klinik - wenn RadioButton rdbshowAll geklickt wird
     */
    public void showAllPatients()
    {
        this.lvAllPatients.setVisible(true);
        tabPanePatientsData.getSelectionModel().select(0);
        this.updateLvAllPatients(DbManager.getInstance().getAllPatientsFromDbTable());
        this.hbPatientName.setVisible(false);
        this.lblUserMsg.setVisible(false);
    }

    /**
     *  Wenn RadioButton rdbshowByName geklickt wird
     * zeigt die daten eines Patienten , dessen Name eingegeben wurde
     */
    public void showPatientDataByName(){
        String strFirstName = this.txtPatientFName.getText();
        String strLastName = this.txtPatientLName.getText();
        if ((!strFirstName.isEmpty()) && (!strLastName.isEmpty())) {
            int  iPatientId = DbManager.getInstance().getPatientIdByFullNameFromDbTable(strFirstName, strLastName);
            if(iPatientId != -1)
            {
                this.lvAllMedicalRecordsOFPatient.setVisible(true);
                tabPanePatientsData.getSelectionModel().select(1);

                List<Patient> allDatenOfPatient = new ArrayList<>();
                allDatenOfPatient = DbManager.getInstance().getAllPatientRecordsByPatientIdFromDbTable(iPatientId);

                // befeullt
                this.updateLvAllMedicalRecordsOfPatient(allDatenOfPatient);

                //befeullt die Termine Liste mit den Termine Daten dieses Patienten
                this.updateLvAllAppointmentsPlan(DbManager.getInstance().getAppointmentsByPatientIdFromDbTable(iPatientId));
            }else this.lblUserMsg.setText("Die Name Des Patienten muss richtig sein");
        }else
            this.lblUserMsg.setText("Die Name Des Patienten muss richtig sein");

    }

    /**
     * zeigt Alle Termine eines Patienten wenn er eingelogt ist
     */
    public void showMyAppointments(){
        tabPanePatientsData.getSelectionModel().select(1);
        //befeullt die Termine Liste mit den Termine Daten dieses Patienten
        int loggedPatientenId = DbManager.getInstance().getLoggedInUser().getId();
        this.updateLvAllAppointmentsPlan(DbManager.getInstance().getAppointmentsByPatientIdFromDbTable(loggedPatientenId));
    }

    public void saveAllAppointmentsOfpatientInCsvFile()
    {
        int loggedPatientenId = DbManager.getInstance().getLoggedInUser().getId();
        List<Appointment> appointmentsOfPatient = new ArrayList<>();
        appointmentsOfPatient = DbManager.getInstance().getAppointmentsByPatientIdFromDbTable(loggedPatientenId);
        CsvFileHandler.getInstance().saveAppointmentsToCsvFile(appointmentsOfPatient,"appointments"+loggedPatientenId+".csv");
        lblUserMsg.setText(" Die Termintabelle wurde in der Datei gespeichert");
    }
    /**
     * zeigt Personal Daten eines Patienten wenn er eingelogt ist
     */
    public void showMyPersonalData(){
        tabPanePatientsData.getSelectionModel().select(0);
        AUser patient = DbManager.getInstance().getLoggedInUser();

        lblFirstName.setText(patient.getFullName().split(" ")[0]);
        lblLastName.setText(patient.getFullName().split(" ")[1]);
        lblUsername.setText(patient.getUserName());
        lblStreet.setText(patient.getStreet());
        lblHnumber.setText(String.valueOf(patient.getHouseNr()));
    }

    /**
     *Speichert der Patient
     */
    public void saveNewPatient(){


        Patient newPatient = getPatientDataFromGui();
        if(newPatient != null){
           int patientId= DbManager.getInstance().getPatientIdByFullNameFromDbTable(newPatient.getFullName().split(" ")[0],newPatient.getFullName().split(" ")[1]);
           if(patientId == -1) // dieser Patient existiert noch nicht in DB , neuer Patient
           {
               DbManager.getInstance().InsertNewPatientIntoDbTable(newPatient);
               lblAngelegt.setText("der neue Patient wurde erfolgreich angelegt");
           }
           else{
               newPatient.setId(patientId);
               DbManager.getInstance().InsertNewPatientRecordIntoDbTable(newPatient);
               lblAngelegt.setText("der Patient ist schon in der System existiert, die neue Daten wurden erfolgreich angelegt");
           }

            DbManager.getInstance().allPatients.add(newPatient);
            this.lblUserMsg.setText("");
            resetPatientTextFields();

        }



        /*for (Patient p : DbManager.getInstance().allPatients) {
            System.out.println(p);
            System.out.println("----------------------");
        }*/
    }


    /**
     * Speichert der Termin
     */
    public void saveNewAppointment(){

        Appointment newAppointment = getAppointmentDataFromGui();
        if(newAppointment != null){
            DbManager.getInstance().InsertNewAppointmentIntoDbTable(newAppointment);

            List<Appointment> allApointments = new ArrayList<>();
            allApointments = DbManager.getInstance().getAllAppointmentsFromDbTable();
            //Listview update
            this.updateLvAppointments(allApointments);

            resetAppointmentTextFields();
            this.lblAppointment.setText("der neuen Termin wurde angelegt");
        }else
            this.lblAppointment.setText("Geben Sie die im System registriertenn PatientenName ein");


       /* //TODO delete
        for (Appointment p : allAppointment) {
            System.out.println(p);
            System.out.println("----------------------");
        }*/
    }

    public void showAllAppointments(){
        List<Appointment> allApointments = new ArrayList<>();
        allApointments = DbManager.getInstance().getAllAppointmentsFromDbTable();
        //Listview update
        this.updateLvAppointments(allApointments);
    }


    /**
     * Speichert der Doktor in der Db
     * diese Methode wird aufgeruft in Doctor View wenn die Daten Des neuen Doktor eingegeben wird,
     * dann submit Button geklickt
     */
    public void registerNewDoctor(){
        Doctor newDoctor = getDoctorDataFromGui();

        if(newDoctor != null){
            DbManager.getInstance().InsertNewDoctorIntoDbTable(newDoctor);
            System.out.println("der neuen Arzt wurde angelegt");
            int doctorId = DbManager.getInstance().getDoctorIdByFullNameFromDbTable(newDoctor.getFullName().split(" ")[0],newDoctor.getFullName().split(" ")[1]);
            newDoctor.setId(doctorId);
            DbManager.getInstance().setLoggedInUser(newDoctor);
            showDoctorView("Willkommen Doktor " + DbManager.getInstance().getLoggedInUser().getFullName());
            allDoctors.add(newDoctor);
        }

        //TODO delete
        for (Doctor d : allDoctors) {
            System.out.println(d);
            System.out.println("----------------------");
        }


    }



    public void backToDoctorView(){
        showDoctorView(DbManager.getInstance().getLoggedInUser().getFullName());
    }

    public void switchBackToLoggedInScreen() {
        openLogInScreen(primaryStage);
    }

    /**
     * ListView Click abfangen,
     * wenn Einen Patienten ausgewaehlt wurde ( der Patient wird geklickt in der Patienten Liste)
     * ,wird die Liste von Termine mit Termine Daten dieses Patienten befuellt
     */
    public void getListViewItem() {
        AUser selectedPatient = this.lvAllPatients.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            System.out.println(selectedPatient.getId());
            this.updateLvAllAppointmentsPlan(DbManager.getInstance().getAppointmentsByPatientIdFromDbTable(selectedPatient.getId()));
        }
    }

    public void getListViewAppointmentItem(){
        Appointment selectedApp = this.lvAppointments.getSelectionModel().getSelectedItem();

        if (selectedApp != null) {
            System.out.println(selectedApp.getPatientId());
            AUser patient = DbManager.getInstance().getUserByUserIdFromDbTable(selectedApp.getPatientId());

            this.txtPatientFName.setText(patient.getFullName().split(" ")[0]);
            this.txtPatientLName.setText(patient.getFullName().split(" ")[1]);
        }
    }

    //endregion


    //region Hilfsmethoden und Funktionen

    /**
     * Liest den {@link Patient} aus der Formular
     * und gibt diese als befuellter {@link Patient} zureuck
     * sollten die Eingabedaten ausgefuellt worden sein.
     *
     * @return patientFromGui : {@link Patient} : Befulltes {@link Patient}
     * oder <b>null</b> wenn die Eingabedaten nicht ausgefeullt worden sind
     */
    private Patient getPatientDataFromGui() {
        Patient patientFromGui = null;

        String strFirstName = this.txtPatientFName.getText();
        String strLastName = this.txtPatientLName.getText();
        String strGender = this.txtGender.getText();
        String strWeight = this.txtWeight.getText();

        String strMedicalHistory = this.txtMHistory.getText();
        String strDiseases = this.txtDiseases.getText();
        String strAllergies = this.txtAllergies.getText();
        String strVaccination = this.txtVaccination.getText();

        String strUserName = this.txtUserName.getText();
        String strUserPw = this.txtUserPw.getText();

        if ((!strFirstName.isEmpty()) && (!strLastName.isEmpty()) && (!strUserName.isEmpty()) && (!strUserPw.isEmpty())) {
            patientFromGui = new Patient();
            patientFromGui.setFullName(strFirstName + " " + strLastName);
            patientFromGui.setUserName(strUserName);
            patientFromGui.setPassword(this.getSha256HashValueAsHexStringFromPw(strUserPw));
            patientFromGui.setUserRole(EUserRole.PATIENT);
            if (!strGender.isEmpty())
                patientFromGui.setGender(strGender);
            if (!strWeight.isEmpty())
                patientFromGui.setWeight(strWeight);
            if (!strMedicalHistory.isEmpty())
                patientFromGui.setMedicalHistory(strMedicalHistory);
            if (!strDiseases.isEmpty())
                patientFromGui.setDiseases(strDiseases);
            if (!strAllergies.isEmpty())
                patientFromGui.setAllergies(strAllergies);
            if (!strVaccination.isEmpty())
                patientFromGui.setVaccination(strVaccination);

        }
        else
            this.lblUserMsg.setText("Name, Geschlecht, Gewicht, Username, Passwort sind nötig");
        return patientFromGui;
    }

    /**
     * Liest den {@link Appointment} aus der Formular
     * und gibt diese als befuellter {@link Appointment} zureuck
     * sollten die Eingabedaten ausgefuellt worden sein.
     *
     * @return appointmentFromGui : {@link Appointment} : Befulltes {@link Appointment}
     * oder <b>null</b> wenn die Eingabedaten nicht ausgefeullt worden sind
     */
    private Appointment getAppointmentDataFromGui(){
            Appointment appointmentFromGui = null;

            String strFirstName = this.txtPatientFName.getText();
            String strLastName  = this.txtPatientLName.getText();
            String strAppDate;
            String strTime      = this.txtTime.getText();
        /*//get java.sql.Date straight away to your database table :
        java.sql.Date sqlDate =java.sql.Date.valueOf(datepicker.getValue());*/

            if((!strFirstName.isEmpty()) && (!strLastName.isEmpty()) && (this.dpAppointmentDate.getValue()!=null ) &&(!strTime.isEmpty())){

                strAppDate =  this.dpAppointmentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int patientId =  DbManager.getInstance().getPatientIdByFullNameFromDbTable(strFirstName,strLastName);
                if(patientId != -1) {
                    appointmentFromGui = new Appointment();
                    appointmentFromGui.setDoctorId(DbManager.getInstance().getLoggedInUser().getId());
                    appointmentFromGui.setPatientId(patientId);
                    appointmentFromGui.setDate(strAppDate);
                    appointmentFromGui.setTime(strTime);
                }
            }
            return appointmentFromGui;
    }

    /**
     * Liest den {@link Doctor} aus der Formular
     * und gibt diese als befuellter {@link Doctor} zureuck
     * sollten die Eingabedaten ausgefuellt worden sein.
     *
     * @return doctorFromGui : {@link Doctor} : Befulltes {@link Doctor}
     * oder <b>null</b> wenn die Eingabedaten nicht ausgefeullt worden sind
     */
    private Doctor getDoctorDataFromGui() {
        Doctor doctorFromGui = null;

        String strFirstName      = this.txtDoctorFName.getText();
        String strLastName       = this.txtDoctorLName.getText();
        String strSpecialization = this.txtDoctorSpecialization.getText();
        String strEmail          = this.txtDoctorEmail.getText();

        String strUserName       = this.txtUserName.getText();
        String strUserPw         = this.txtUserPw.getText();

        if ((!strFirstName.isEmpty()) && (!strLastName.isEmpty()) && (!strUserName.isEmpty()) && (!strUserPw.isEmpty())) {

            doctorFromGui = new Doctor();
            doctorFromGui.setFullName(strFirstName + " " + strLastName);
            doctorFromGui.setUserName(strUserName);
            doctorFromGui.setPassword(this.getSha256HashValueAsHexStringFromPw(strUserPw));
            doctorFromGui.setUserRole(EUserRole.Doctor);
            if ((!strSpecialization.isEmpty()) && (!strEmail.isEmpty())) {
                doctorFromGui.setSpecialization(strSpecialization);
                doctorFromGui.setEmail(strEmail);
            }
            //TODO set alle attribute
        }
        return doctorFromGui;
    }


    private void resetPatientTextFields() {
        this.txtPatientFName.setText("");
        this.txtPatientLName.setText("");
        this.txtGender.setText("");
        this.txtWeight.setText("");
        this.txtMHistory.setText("");
        this.txtDiseases.setText("");
        this.txtAllergies.setText("");
        this.txtVaccination.setText("");
        this.txtUserName.setText("");
        this.txtUserPw.setText("");
    }

    private void resetDoctorTextFields() {

    }

    private void resetAppointmentTextFields() {
        this.txtPatientFName.setText("");
        this.txtPatientLName.setText("");
        //this.dpAppointmentDate.setValue();

    }

    /**
     * Befuellt die ListView mit neuen Daten-> Appointments
     */
    private void updateLvAppointments(List<Appointment> appointments) {
        this.observableAppointmentsFromDb = FXCollections.observableArrayList(appointments);

        //Appointments zur ListView hinzufuegen
        this.lvAppointments.setItems(this.observableAppointmentsFromDb);
    }


    /**
     * Befuellt die ListView mit neuen Daten-> Patients
     */
    private void updateLvAllPatients(List<AUser> patientsToShow) {

        if(!(patientsToShow.isEmpty())) {
            this.observablePatientsFromDb = FXCollections.observableArrayList(patientsToShow);

            //Patients zur ListView hinzufuegen
            this.lvAllPatients.setItems(this.observablePatientsFromDb);
        }
    }

    /**
     * Befuellt die ListView mit neuen Daten-> Appointments for a patient
     */
    private void updateLvAllAppointmentsPlan(List<Appointment> appointmentsList) {

            this.observableAppointmentplanByPatientFromDb = FXCollections.observableArrayList(appointmentsList);

            //Patients zur ListView hinzufuegen
            this.lvAppsPlan.setItems(this.observableAppointmentplanByPatientFromDb);
        System.out.println("update List of Appointment");
    }

    /**
     * Befuellt die ListView mit neuen Daten-> Patients
     */
    private void updateLvAllMedicalRecordsOfPatient(List<Patient> medicalRecordsToShow) {

        if(!(medicalRecordsToShow.isEmpty())) {
            this.observablePatientRecordsFromDb = FXCollections.observableArrayList(medicalRecordsToShow);

            //Patients zur ListView hinzufuegen
            this.lvAllMedicalRecordsOFPatient.setItems(this.observablePatientRecordsFromDb);
        }else
            this.lvAllMedicalRecordsOFPatient.getItems().clear();
    }
    public void switchComponentVisibility() {
        this.lblUserMsg.setVisible(true);
        this.hbPatientName.setVisible(true);
        this.lvAllPatients.setVisible(false);
    }

    //TODO passwort muss gehashed werden
    private String getSha256HashValueAsHexStringFromPw(String strPwToHash) {


        MessageDigest digest           = null;
        StringBuffer  hexStringBuilder = null;
        try {


            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(strPwToHash.getBytes(StandardCharsets.UTF_8));
            hexStringBuilder = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);

                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex);
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexStringBuilder.toString();
    }

    //endregion

}
