package de.msaada.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Generiert eine Zeitstempel
 * @author Chris Loch
 */
public class DateHelper {

    //region Konstanten
    private static final String DATE_PATTERN = "dd.MM.yyyy hh:mm:ss";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktor
    private DateHelper() {

    }
    //endregion

    //region Date-Methoden
    public static String getCurrentDateAsFormattedString() {
        Date currentDate = new Date();
        SimpleDateFormat sdf         = new SimpleDateFormat(DATE_PATTERN);

        return sdf.format(currentDate);
    }
    //endregion
}
