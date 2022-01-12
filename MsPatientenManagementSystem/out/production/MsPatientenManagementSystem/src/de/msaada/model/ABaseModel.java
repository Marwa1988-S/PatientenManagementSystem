package de.msaada.model;

/**
 * Definiert alle Eigenschaften
 * Methoden und Funktion die
 * Modelklassen brauchen.
 * Direkte Subklassen:
 * <ul>
 *     <li>{@link AUser}</li>
 *     <li>{@link Appointment}</li>
 *     <li>{@link Behandlung}</li>
 * </ul>
 * <p>
 * Indirekte Subkalssen:
 * <ul>
 *     <li>{@link Doctor}</li>
 *     <li>{@link Patient}</li>
 * </ul>
 *
 */
public class ABaseModel {

    //region 0.Konstanten
    /**
     * Standardkonstante fuer String Attribute
     */
    protected static final String DEF_VALUE_STR = ">noValueYet<";
    protected static final int    DEF_VALUE_INT = -1;
    protected static final double DEF_VALUE_DBL = -1D;
    protected static final String SPLIT_CHAR              = ";";
    //endregion


    //region 1. Decl. and Init Attribute
    protected int iId;
    //endregion


    //region 2. Konstruktoren
    /**
     * Standardkonstruktor
     * zum direkten intialisieren
     * der Attribute
     */
    public ABaseModel()
    {
        this.iId = DEF_VALUE_INT;
    }

    /**
     * 1. Ueberladener Konstruktor
     * zum direkten setzen der Id
     *
     * @param iId : int : id des Objekts
     */
    protected ABaseModel(int iId) {
        this();
        this.iId = iId;
    }
    //endregion


    //region 3. Getter und Setter

    public int getId() {
        return iId;
    }

    public void setId(int iId) {
        this.iId = iId;
    }



    //endregion


    //region 4. toString


    public String toString1() {
        return "ABaseModel{" +
                "iId=" + iId +
                '}';
    }
    @Override
    public String toString() {
        //TODO check
        return this.iId + " - " ;
    }
    //endregion


}
