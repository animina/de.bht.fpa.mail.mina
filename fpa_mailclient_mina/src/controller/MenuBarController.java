package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController  {

    @FXML
    private MenuItem fileExit;

    @FXML
    private MenuItem editSetBasePath;

    @FXML
    private MenuItem filterSetFilter;

    @FXML
    private MenuItem helpAbout;

    private Stage aboutWindow;
    private boolean okClicked = false;



    //private Main main;

   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileExit.setOnAction((e) -> clickedFile());

        editSetBasePath.setOnAction((e) -> clickedEdit());

        filterSetFilter.setOnAction((e) -> clickedFilter());

        helpAbout.setOnAction((e) -> clickedHelp());

    }
*/


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void clickedFile() {
        System.out.println("Bye Bye");
        System.exit(0);
    }

    @FXML
    public void clickedEdit(){
        System.out.println("Set Base Path...");
    }

    @FXML
    public void clickedFilter(){
        System.out.println("Set Filter...");
    }

    @FXML
    public void clickedHelp(){
        System.out.println("Endlich Mails");
        AnchorPane root;

            Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
            aboutDialog.initOwner(aboutWindow);
            aboutDialog.setTitle("About Endlich Mails");
            aboutDialog.setHeaderText("About");
            aboutDialog.setContentText("Author: Franzi \n\nVersion: 2.3.5\n\n info@franzi.net");
         //   aboutDialog.setContentText("info@franzi.net");
            aboutDialog.showAndWait();

    }

    @FXML
    public void handleOk() {
        aboutWindow.close();
    }


}
