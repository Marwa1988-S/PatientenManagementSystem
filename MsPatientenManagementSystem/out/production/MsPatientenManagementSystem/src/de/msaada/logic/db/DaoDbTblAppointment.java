package de.msaada.logic.db;

import de.msaada.helper.DateHelper;
import de.msaada.model.ABaseModel;
import de.msaada.model.AUser;
import de.msaada.model.Appointment;
import de.msaada.model.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoDbTblAppointment extends ADaoDbTbl {

    //region 0. Konstanten


    //endregion

    //region 1. Decl. and Attribute
    //endregion

    //region 2. Konstruktor
    /**
     * STandard Konstruktor zum direkten setzen
     */
    public DaoDbTblAppointment() {
        super("tbl_appointment");
    }

    //endregion

    //region 3. CRUD Operationen
    @Override
    public void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable) {
        Statement dbStatementToExecute = null;
        try {
            //1. Db Connection ist bereits von DbManger generiert

            //2. Statement generieren lassen
            dbStatementToExecute = dbRwConnection.createStatement();

            //3.  SQL-String generieren - um Patient Daten in tbl_patient_akte zu speichern ( Patient)
            String strSqlStmtInsert =" INSERT INTO `tbl_appointment` ( `user_id_doctor`, `user_id_patient`, `appointment_date`, `time`)"
                    + " VALUES (\'"
                    + ((Appointment) modelToInsertIntoDbTable).getDoctorId()
                    + "\',\'"
                    + ((Appointment) modelToInsertIntoDbTable).getPatientId()
                    + "\',\'"
                    + ((Appointment) modelToInsertIntoDbTable).getDate()
                    + "\',\'"
                    + ((Appointment) modelToInsertIntoDbTable).getTime()
                    + "\')";
            //4. SQL - String an Satement objekt zum ausfuerhren geben
            dbStatementToExecute.execute(strSqlStmtInsert);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //5. Schliessen der des Statements
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //6. Schliessen der Verbindung
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }

    }

    @Override
    public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {

    }

    @Override
    public void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable) {

    }

    @Override
    public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable) {

    }


    /**
     * Gibt alle Datensaetze eine Appointment tabelle als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return allDataRecordsFromDbTbl : {@link List}  {@link Appointment} : Liste aller Datensaetze
     */
    @Override
    public List<Appointment> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
        List<Appointment> allAppointmentsFromDbTable = new ArrayList<>();

        //Decl. and Init
         Statement dbStatementToExecute = null;
         try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetAll = "SELECT * FROM `tbl_appointment` ";

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAll);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            while (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                Appointment appointmentFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);

                //6. Modelobjekt zur passenden Liste adden
                allAppointmentsFromDbTable.add(appointmentFromDbTable);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //5. Schliessen der des Statements
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //6. Schliessen der Verbindung
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }

        return allAppointmentsFromDbTable;
    }

    @Override
    public Appointment getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        return null;
    }

    @Override
    public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {

    }

    public List<Appointment> getAllDataRecordsFromDbTblByPatientId(Connection dbRwConnection, int iPatientId) {
        List<Appointment> allAppointmentsFromDbTable = new ArrayList<>();

        //Decl. and Init
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetAllByPatientID = "SELECT * FROM `tbl_appointment` WHERE `tbl_appointment`.`user_id_patient` = \""
                    + iPatientId
                    +"\"";
            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAllByPatientID);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            while (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                Appointment appointmentFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);

                //6. Modelobjekt zur passenden Liste adden
                allAppointmentsFromDbTable.add(appointmentFromDbTable);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dbStatementToExecute != null) {
                //5. Schliessen der des Statements
                try {
                    dbStatementToExecute.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (dbRwConnection != null) {
                //6. Schliessen der Verbindung
                try {
                    dbRwConnection.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }

        return allAppointmentsFromDbTable;
    }

    //endregion

    //region Hilfsmethoden und Funktionen
    /**
     * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
     *
     * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
     *
     * @return aBaseModel : {@link ABaseModel} : Model  abgeleitet von der Basisklasse
     *
     * @throws SQLException
     */
    @Override
    public Appointment getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
        //5. Ueber den Spaltennamen die Spaltenindizes auslesen
        int iColumnIndexId          = currentResultSet.findColumn("_id");
        int iColumnIndexDoctorId    = currentResultSet.findColumn("user_id_doctor");
        int iColumnIndexPatientId   = currentResultSet.findColumn("user_id_patient");
        int iColumnIndexDate        = currentResultSet.findColumn("appointment_date");
        int iColumnIndexTime        = currentResultSet.findColumn("time");

        //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
        int    iId             = currentResultSet.getInt(iColumnIndexId);
        int    iDoctorId       = currentResultSet.getInt(iColumnIndexDoctorId);
        int    iPatientId      = currentResultSet.getInt(iColumnIndexPatientId);
        String strDate   = currentResultSet.getString(iColumnIndexDate);
        String strTime   = currentResultSet.getString(iColumnIndexTime);

        //7. Neues Modelobjekt generieren
        Appointment appointmentFromDbTable = new Appointment(iId,iPatientId,iDoctorId,strDate,strTime);

        return appointmentFromDbTable;
    }
    //endregion
}
