package de.msaada.maingui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Neue GUI repreasentativ
 * für den Arzt wenn er einen neuen Patienten einlegen möchte
 */
public class GuiAddPatientView {

    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init attribute
    //endregion

    //region 2. Konstruktor
    //endregion

    //region 3. Oeffnen/Darstellen der neuen Gui
    public void openAddPatientView(Parent rootParent, Scene mainScene, Stage primaryStage, String viewTitle) {
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/add_patient_view.fxml"));

            mainScene = new Scene(rootParent, 600, 600);

            //  loadLoggedInViewCssStyles(mainScene);

            primaryStage.setTitle(viewTitle);

            primaryStage.setScene(mainScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
}
