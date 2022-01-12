package de.msaada.maingui;

import de.msaada.logic.DbManager;
import de.msaada.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiAllPatientsView {
    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init attribute
    @FXML
    private ListView<Patient> lvAllPatients;
    private ObservableList<Patient> observablePatientsFromDb;
    //endregion

    //region 2. Konstruktor
    //endregion

    //region 3. Oeffnen/Darstellen der neuen Gui
    public void openAllPatientsView(Parent rootParent, Scene mainScene, Stage primaryStage, String viewTitle) {
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/all_patients_view.fxml"));

            mainScene = new Scene(rootParent, 600, 600);

            //  loadLoggedInViewCssStyles(mainScene);

            primaryStage.setTitle(viewTitle);

            primaryStage.setScene(mainScene);

            //updateLvAllPatients();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* private void updateLvAllPatients() {
        //TODO Patients von Db Auslesen
        //List<Appoi>
        if(!(DbManager.getInstance().allPatients.isEmpty())) {
            this.observablePatientsFromDb = FXCollections.observableArrayList(DbManager.getInstance().allPatients);

            //Appointments zur ListView hinzufuegen
            this.lvAllPatients.setItems(this.observablePatientsFromDb);
        }
    }*/
    //endregion
}
