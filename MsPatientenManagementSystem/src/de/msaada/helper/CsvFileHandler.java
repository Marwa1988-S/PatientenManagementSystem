package de.msaada.helper;

import de.msaada.model.Appointment;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHandler {
    //region 0. Konstanten
    private static final String FILE_PATH_APPOINTMENTS   = "src/de/msaada/files/";


    private static final String CHARSET_UTF_8 = "UTF-8";
    //endregion

    //region 1. Decl and Init Attribute
    private static CsvFileHandler instance;
    //endregion

    //region 2 Konstruktor
    private CsvFileHandler() {
        //Nichts zu tun
    }
    //endregion

    //region Get Instance

    /**
     * Gibt die einzige Instanz zur Laufzeit zurueck
     *
     * @return instance : {@link CsvFileHandler} : Einzige Instanz
     */
    public static synchronized CsvFileHandler getInstance() {
        if (instance == null) {
            instance = new CsvFileHandler();
        }

        return instance;
    }
    //endregion

    //region Speichern von Termine

    /**
     * Speichert eine Liste von Termine in einer CSV-Datei
     * welche unter {@link CsvFileHandler#FILE_PATH_APPOINTMENTS} zu finden ist
     *
     * @param appointmentsToSave : {@link List} : Liste mit zu speichernden Kursen
     */
    public void saveAppointmentsToCsvFile(List<Appointment> appointmentsToSave,String fileName) {

        //Neues Dateiobjekt mit Pfadangabe generieren
        File appointmentsCsvFile = new File(FILE_PATH_APPOINTMENTS+fileName);

        //Tatsächliches anlegen der Datei auf der Festplatte
        try {
            if(appointmentsCsvFile.createNewFile() || appointmentsCsvFile.exists()) {

                //Datenstrom, bzw. die "Brücke" zur Datei auf Basis der Infos des File-Objektes
                FileOutputStream fos = new FileOutputStream(appointmentsCsvFile);

                //OutputStreamWriter generieren, fos kann nur ints und bytes schreiben
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

                //BufferedWriter generieren um ohne Probleme Strings schreiben zu können
                BufferedWriter out = new BufferedWriter(osw);

                for (Appointment app : appointmentsToSave) {
                    out.write(app.getAllAttributesAsCsvString() + "\n");
//                out.newLine();
                }

                //Schließt den Writer und flushed
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //endregion

    //region Lesen

    /**
     * Liest alle {@link Appointment}s aus der entsprechnden CSV-Datei aus.
     * Sollten keine Termine exisiterien wird eine leere Termineliste zurueck geliefert
     *
     * @return appFromCsvFile : {@link List} - {@link Appointment} : Termine vom CSV-File oder eine leere Liste
     * sollte es noch keine Termine geben oder alle Termine wurden geloescht
     */
    public List<Appointment> readCustomersFromCsvFile() {
        List<Appointment> appFromCsvFile = new ArrayList<>();

        File appCsvFile = new File(FILE_PATH_APPOINTMENTS);

        if (appCsvFile.exists()) {

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(appCsvFile);

                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

                BufferedReader in = new BufferedReader(isr);

                boolean endOfFile = false;

                while (!endOfFile) {
                    String strCsv = in.readLine();

                    if (strCsv == null) {
                        endOfFile = true;
                    } else {
                        Appointment app = new Appointment();
                        app.setAllAttributesFromCsvString(strCsv);
                        appFromCsvFile.add(app);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Die Terminedatei existiert nicht.");
        }

        return appFromCsvFile;
    }

    //endregion
}
