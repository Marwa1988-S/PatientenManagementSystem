package de.msaada.logic.db;

import de.msaada.helper.DateHelper;
import de.msaada.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoDbTblPatientRecord extends ADaoDbTbl {
    //region 0. Konstanten


    //endregion

    //region 1. Decl. and Attribute
    //endregion

    //region 2. Konstruktor

    /**
     * STandard Konstruktor zum direkten setzen
     */
    public DaoDbTblPatientRecord() {
        super("tbl_patient_records");
    }
    //endregion


    //region 3. CRUD Operationen
    /**
     * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
     *
     * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
     */
    @Override
    public void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable) {
        Statement dbStatementToExecute = null;
        try {
            //1. Db Connection ist bereits von DbManger generiert

            //2. Statement generieren lassen
            dbStatementToExecute = dbRwConnection.createStatement();

            //3.  SQL-String generieren - um Patient Daten in tbl_patient_akte zu speichern ( Patient)
            String strSqlStmtInsert =" INSERT INTO `tbl_patient_records`(`date`, `weight`, `medical_h`, `disease`, `allergie`, `vaccination`, `user_id`)"
                                    + " VALUES (\'"
                                    + DateHelper.getCurrentDateAsFormattedString()
                                    + "\',\'"
                                    + ((Patient) modelToInsertIntoDbTable).getWeight()
                                    + "\',\'"
                                    + ((Patient) modelToInsertIntoDbTable).getMedicalHistory()
                                    + "\',\'"
                                    + ((Patient) modelToInsertIntoDbTable).getDiseases()
                                    + "\',\'"
                                    + ((Patient) modelToInsertIntoDbTable).getAllergies()
                                    + "\',\'"
                                    + ((Patient) modelToInsertIntoDbTable).getVaccination()
                                    + "\',\'"
                                    + modelToInsertIntoDbTable.getId()
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

    /**
     * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
     *
     * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
     */
    @Override
    public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {

    }

    /**
     * Aendert einen einzelen Datensatz in der Datebanktabelle
     *
     * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
     */
    @Override
    public void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable) {

    }

    /**
     * Aendert eine Liste von Datensaetzen in der Datebanktabelle
     *
     * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
     */
    @Override
    public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable) {

    }

    /**
     * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
     */
    @Override
    public List<Patient> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
        //TODO
        return null;
    }

    /**
     * Gibt alle Datensaetze eine Datenbanktabelle ,die einem Patienten gehören  als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iPatientId     : int                : patient Id
     * @return eigene DataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze eines Patienten
     */
    public List<Patient> getAllDataRecordsFromDbTblByPatientId(Connection dbRwConnection, int iPatientId) {
        List<Patient> allMedicalRecordsOfPatientFromDbTable = new ArrayList<>();

        //Decl. and Init
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetAllRecordsOfPatient = "SELECT * FROM `tbl_patient_records` WHERE `tbl_patient_records`.`user_id` =\""
            + iPatientId
            + "\"";

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAllRecordsOfPatient);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            while (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                Patient MedicalRecordFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);

                //6. Modelobjekt zur passenden Liste adden
                allMedicalRecordsOfPatientFromDbTable.add(MedicalRecordFromDbTable);

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

        return allMedicalRecordsOfPatientFromDbTable;
    }

    /**
     * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iId
     *
     * @return specificDataRecordFoundById : {@link ABaseModel}  oder abgeleitet davon: Gesuchtes Objekt oder null,
     * sollte es dieses nicht geben
     */
    @Override
    public ABaseModel getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        return null;
    }

    /**
     * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
     */
    @Override
    public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {

    }
    //endregion


    //region Hilfsmethoden und Funktionen
    /**
     * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
     *
     * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
     *
     * @return aBaseModel : {@link ABaseModel} : Model abgeleitet von der Basisklasse
     *
     * @throws SQLException
     */
    @Override
    public Patient getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
        //5. Ueber den Spaltennamen die Spaltenindizes auslesen
        int iColumnIndexId          = currentResultSet.findColumn("_id");
        int iColumnIndexDate        = currentResultSet.findColumn("date");
        int iColumnIndexWeight      = currentResultSet.findColumn("weight");
        int iColumnIndexHistory     = currentResultSet.findColumn("medical_h");
        int iColumnIndexDisease     = currentResultSet.findColumn("disease");
        int iColumnIndexAllergie    = currentResultSet.findColumn("allergie");
        int iColumnIndexVaccination = currentResultSet.findColumn("vaccination");
        int iColumnIndexPatientId   = currentResultSet.findColumn("user_id");

        //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
        int    iId            = currentResultSet.getInt(iColumnIndexId);
        String strDate        = currentResultSet.getString(iColumnIndexDate);
        String strWeight      = currentResultSet.getString(iColumnIndexWeight);
        String strHistory     = currentResultSet.getString(iColumnIndexHistory);
        String strDisease     = currentResultSet.getString(iColumnIndexDisease);
        String strAllergie    = currentResultSet.getString(iColumnIndexAllergie);
        String strVaccination = currentResultSet.getString(iColumnIndexVaccination);
        int    intPatientId   = currentResultSet.getInt(iColumnIndexPatientId);

        //7. Neues Modelobjekt generieren
        Patient medicalRecordFromDbTable = new Patient(iId,strWeight,strHistory,strDisease,strAllergie,strVaccination,strDate);

        return medicalRecordFromDbTable;
    }










    //endregion
}
