package de.msaada.maingui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class GuiRegisterView {
    //region 0.Konstanten
    //endregion

    //region 1. Decl. and Init attribute
    //endregion

    //region 2. Konstruktor
    //endregion

    //region 3. Oeffnen/Darstellen der neuen Gui
    public void openRegisterView(Parent rootParent, Scene mainScene, Stage primaryStage, String strRegisterText) {
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/register_view.fxml"));

            mainScene = new Scene(rootParent, 600, 300);

          //  loadLoggedInViewCssStyles(mainScene);

            primaryStage.setTitle(strRegisterText);

            primaryStage.setScene(mainScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region 4. CSS Loading
  /*  private void loadLoggedInViewCssStyles(Scene mainScene) {
        URL urlToStyleSheet = GuiLoggedInView.class.getResource("/stylesLoggedInView.css");

        if (urlToStyleSheet != null) {
            String strUrlToStyleSheet = urlToStyleSheet.toExternalForm();
            mainScene.getStylesheets().add(strUrlToStyleSheet);

        }
    }*/
    //endregopm1

}
