package de.msaada.maingui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Neue GUI repreasentativ
 * für den Arzt wenn er eingloggt ist,damit er die hauptaufgaben machen kann
 */
public class GuiDoctorView {

    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init attribute
    //endregion

    //region 2. Konstruktor
    //endregion

    //region 3. Oeffnen/Darstellen der neuen Gui
    public void openDoctorView(Parent rootParent, Scene mainScene, Stage primaryStage, String strDoctorname) {
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/doctor_view.fxml"));

            mainScene = new Scene(rootParent, 600, 450);

            //  loadLoggedInViewCssStyles(mainScene);

            primaryStage.setTitle(strDoctorname);

            primaryStage.setScene(mainScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

}
