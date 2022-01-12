package de.msaada.maingui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Neue GUI repreasentativ
 * für den Patienten wenn er eingloggt ist,damit er seine Termine sehen kann
 */
public class GuiPatientView {
    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init attribute
    //endregion

    //region 2. Konstruktor
    //endregion

    //region 3. Oeffnen/Darstellen der neuen Gui
    public void openPatientView(Parent rootParent, Scene mainScene, Stage primaryStage, String strPatientname) {
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/patient_view.fxml"));

            mainScene = new Scene(rootParent, 600, 450);

            //  loadLoggedInViewCssStyles(mainScene);

            primaryStage.setTitle(strPatientname);

            primaryStage.setScene(mainScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
}
