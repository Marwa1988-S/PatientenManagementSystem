package de.msaada.logic.db;

import de.msaada.model.ABaseModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Definiert alle Eigenschaften
 * und Methoden sowie Funktionen vor
 * die benoetigt werden um auf
 * Daten einer Datenbanktabelle zuzugreifen,
 * diese zu aendern oder zu loeschen.
 */
public abstract class ADaoDbTbl {

    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init Attribute
    protected String strTableName;
    //endregion
    //region 2. Konstruktoren

    /**
     * STandard Konstruktor zum direkten setzen
     *
     * @param strTableName : {@link String} : Name der Db Tabelle
     */
    protected ADaoDbTbl(String strTableName) {
        this.strTableName = strTableName;
    }

    //endregion


    //region 3. CRUD Operationen

    /**
     * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
     *
     * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
     */
    public abstract void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable);

    /**
     * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
     *
     * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
     */
    public abstract void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable);

    /**
     * Aendert einen einzelen Datensatz in der Datebanktabelle
     *
     * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
     */
    public abstract void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable);

    /**
     * Aendert eine Liste von Datensaetzen in der Datebanktabelle
     *
     * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
     */
    public abstract void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable);

    /**
     * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
     */
    public abstract List<? extends ABaseModel> getAllDataRecordsFromDbTbl(Connection dbRwConnection);

    /**
     * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     *
     * @return specificDataRecordFoundById : {@link ABaseModel}  oder abgeleitet davon: Gesuchtes Objekt oder null,
     * sollte es dieses nicht geben
     */
    public abstract ABaseModel getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId);


    /**
     * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
     *
     * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
     * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
     */
    public abstract void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId);
    //endregion


    //region 4. Hilfsmethoden und Funktione

    /**
     * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
     *
     * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
     *
     * @return aBaseModel : {@link ABaseModel} : Model abgeleitet von der Basisklasse
     *
     * @throws SQLException
     */
    public abstract ABaseModel getModelFromResultSet(ResultSet currentResultSet) throws SQLException;
    //endregion
}
