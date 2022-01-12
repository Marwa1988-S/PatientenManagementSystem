package de.msaada.logic.db;

import de.msaada.model.ABaseModel;
import de.msaada.model.AUser;
import de.msaada.model.Doctor;
import de.msaada.model.Patient;
import de.msaada.model.enums.EUserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoDbTblUser extends ADaoDbTbl {
    //region 0. Konstanten


    //endregion

    //region 1. Decl. and Attribute
    //endregion

    //region 2. Konstruktor

    /**
     * STandard Konstruktor zum direkten setzen
     */
    public DaoDbTblUser() {
        super("tbl_user");
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


       // if (modelToInsertIntoDbTable instanceof Doctor) {
            //Decl and Init
            Statement dbStatementToExecute = null;

            try {
                String strEmail= ">noValueYet<";
                if (modelToInsertIntoDbTable instanceof Doctor) {
                   strEmail=  ((Doctor) modelToInsertIntoDbTable).getEmail();
                }
                //1. Db Connection ist bereits von DbManger generiert

                //2. Statement generieren lassen
                dbStatementToExecute = dbRwConnection.createStatement();

                //3. SQL-String generieren - um User Daten in tbl_user zu speichern (doctor or Patient)
                String strSqlStmtInsert =" INSERT INTO `tbl_user`"
                        + "(`userName`, `userPw`, `firstName`, `lastName`, `birthDate`, `userRole`, `email`, `street`, `houseNumber`)"
                        + " VALUES (\'"
                        + ((AUser) modelToInsertIntoDbTable).getUserName()
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getHashedPassword()
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getFullName().split(" ")[0]
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getFullName().split(" ")[1]
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getBdate()
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getUserRole().toString()
                        + "\',\'"
                        + strEmail
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getStreet()
                        + "\',\'"
                        + ((AUser) modelToInsertIntoDbTable).getHouseNr()
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
     //   }
    }

    /**
     * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
     *
     * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
     */
    @Override
    public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {
      /*  if (!modelsToInsertIntoDbTable.isEmpty()) {
            //Decl and Init
            Statement dbStatementToExecute = null;

            try {
                //1. Db Connection ist bereits von DbManger generiert

                //2. Statement generieren lassen
                for (ABaseModel modelToInsert : modelsToInsertIntoDbTable) {
                    User userToInsert = (User) modelToInsert;

                    dbStatementToExecute = dbRwConnection.createStatement();

                    //3. SQL-String generieren
                    String strSqlStmtInsert = INSERT_TBL + this.strTableName
                            + CHAR_OPEN_BRACKET
                            + COL_NAME_USER_NAME_INC_COLUMN_BACK_TICKS + CHAR_COMMA
                            + COL_NAME_USER_PW_INC_COLUMN_BACK_TICKS
                            + CHAR_CLOSE_BRACKET
                            + VALUES_OPERATOR
                            + CHAR_OPEN_BRACKET
                            + CHAR_VALUE_BACK_TICK + userToInsert.getUserName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                            + CHAR_VALUE_BACK_TICK + userToInsert.getUserPwSha256HashValue() + CHAR_VALUE_BACK_TICK
                            + CHAR_CLOSE_BRACKET_SEMICOLON;

                    //4. SQL - String an Satement objekt zum ausfuerhren geben
                    dbStatementToExecute.execute(strSqlStmtInsert);
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
        }*/
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
    public List<AUser> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
     return null;
    }

    /**
     * Gibt alle Patienten eine Datenbanktabelle als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
     */
    public List<AUser> getAllPatientsDataRecordsFromDbTbl(Connection dbRwConnection) {
        List<AUser> allPatientsFromDbTable = new ArrayList<>();

        //Decl. and Init
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetAllPatients = "SELECT * FROM `tbl_user` WHERE `tbl_user`.`userRole` = \"Patient\"";

            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAllPatients);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            while (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                AUser PatientFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);

                //6. Modelobjekt zur passenden Liste adden
                allPatientsFromDbTable.add(PatientFromDbTable);

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

        return allPatientsFromDbTable;

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
    public AUser getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
        //Decl. and Init
        AUser userFromDbTable = null;
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetSpecificUser = "SELECT * FROM `tbl_user` WHERE `_id`= \""
                    + iId
                    +"\"";
            System.out.println(strSqlStmtGetSpecificUser);
            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetSpecificUser);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            if (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                userFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);
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

        return userFromDbTable;
    }

    //TODO comment
    public AUser getSpecificUserDataRecordFromDbTbByUsernameAndPassword(Connection dbRwConnection, String strUserName, String strUserPassword)
    {
          //Decl. and Init
        AUser userFromDbTable = null;
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetSpecificUser = "SELECT * FROM `tbl_user` WHERE `userName`= \""
                    + strUserName
                    + "\" AND `userPw`= \""
                    + strUserPassword
                    +"\"";
            System.out.println(strSqlStmtGetSpecificUser);
            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetSpecificUser);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            if (resultSetFromExecutedQuery.next()) {

                //5. Aus der Ergebenismenge einen User beschafften
                 userFromDbTable = getModelFromResultSet(resultSetFromExecutedQuery);
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

        return userFromDbTable;
    }

    //TODO comment
    public int getUserIdFromDbTbByFullName(Connection dbRwConnection, String strFirstName, String strLastName) {
        int userId = -1;
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetUserId = "SELECT _id FROM `tbl_user` WHERE `firstName`= \""
                    + strFirstName
                    + "\" AND `lastName`= \""
                    + strLastName
                    +"\"";
            System.out.println(strSqlStmtGetUserId);
            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetUserId);

            //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
            if (resultSetFromExecutedQuery.next()) {
                //5. Ueber den Spaltennamen die Spaltenindizes auslesen
                int iColumnIndexId       = resultSetFromExecutedQuery.findColumn("_id");

                //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
                userId= resultSetFromExecutedQuery.getInt(iColumnIndexId);

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
        return userId;
    }

    //SELECT max(_id) FROM `tbl_user`
    //TODO comment
    public int getLastUserIdFromDbTb(Connection dbRwConnection) {
        int userId = 0;
        Statement dbStatementToExecute = null;
        try {
            //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert
            //2. Geneieren des Statenements
            dbStatementToExecute = dbRwConnection.createStatement();

            //3. Query generieren und  absetzen und Ergebnismenge merken
            String strSqlStmtGetLastUserId = "SELECT max(_id) FROM `tbl_user`";
            ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetLastUserId);

            //4. ResultSet == ein Record
            if (resultSetFromExecutedQuery.next()) {
                //5. Ueber den Spaltennamen die Spaltenindizes auslesen
                int iColumnIndexId       = resultSetFromExecutedQuery.findColumn("max(_id)");

                //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
                userId= resultSetFromExecutedQuery.getInt(iColumnIndexId);

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
        return userId;
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
     * @return aBaseModel : {@link ABaseModel} : Model  abgeleitet von der Basisklasse
     *
     * @throws SQLException
     */
    @Override
    public AUser getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
        //TODO alter data

        //5. Ueber den Spaltennamen die Spaltenindizes auslesen
        int iColumnIndexId       = currentResultSet.findColumn("_id");
        int iColumnIndexUserName = currentResultSet.findColumn("userName");
        int iColumnIndexUserPw   = currentResultSet.findColumn("userPw");
        int iColumnIndexFirstName       = currentResultSet.findColumn("firstName");
        int iColumnIndexLastName = currentResultSet.findColumn("lastName");
        int iColumnIndexBirthDate   = currentResultSet.findColumn("birthDate");
        int iColumnIndexUserRole       = currentResultSet.findColumn("userRole");
        int iColumnIndexEmail = currentResultSet.findColumn("email");
        int iColumnIndexStreet   = currentResultSet.findColumn("street");
        int iColumnIndexHouseNumber   = currentResultSet.findColumn("houseNumber");

        //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
        int    iId          = currentResultSet.getInt(iColumnIndexId);
        String strUserName  = currentResultSet.getString(iColumnIndexUserName);
        String strUserPw    = currentResultSet.getString(iColumnIndexUserPw);
        String strFirstName = currentResultSet.getString(iColumnIndexFirstName);
        String strLastName  = currentResultSet.getString(iColumnIndexLastName);
        String strBirthDate   = currentResultSet.getString(iColumnIndexBirthDate);
        String strUserRole    = currentResultSet.getString(iColumnIndexUserRole);
        String strEmail       = currentResultSet.getString(iColumnIndexEmail);
        String strStreet      = currentResultSet.getString(iColumnIndexStreet);
        int    intHouseNumber = currentResultSet.getInt(iColumnIndexHouseNumber);

        //7. Neues Modelobjekt generieren
        AUser userFromDbTable = new AUser(iId, strUserName, strUserPw,strFirstName + " " + strLastName,strBirthDate, EUserRole.valueOf(strUserRole) );

        return userFromDbTable;

    }


    //endregion
}
