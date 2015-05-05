package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private MenuItem fileExit;

    @FXML
    private MenuItem editSetBasePath;

    @FXML
    private MenuItem filterSetFilter;

    @FXML
    private MenuItem helpAbout;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        fileExit.setOnAction((e) -> handleFileExit()) ;

        editSetBasePath.setOnAction((e) -> handleEditSetBasePath());

        filterSetFilter.setOnAction((e) -> handleFilterSetFilter());

        helpAbout.setOnAction((e) -> handleHelpAbout());

    }

    public void handleFileExit () {
        System.out.println("Bye Bye");
        System.exit(0);
    }

    public void handleEditSetBasePath() {
        System.out.println("Basepath set");
    }

    public void handleFilterSetFilter() {
        System.out.println("Filter set");
    }

    public void handleHelpAbout() {
        System.out.println("There is so much to know");
    }
}
