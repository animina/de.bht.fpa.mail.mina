package view;

import controller.AboutViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Main extends Application {

    //private Stage primaryStage;



    @Override
    public void start(Stage primaryStage) throws Exception{

        //initializes rootLayout, including pic
        BorderPane root = FXMLLoader.load(getClass().getResource("window.fxml"));
        primaryStage.getIcons().add(new Image("Mail-icon.png"));
        primaryStage.setTitle("Endlich Mails");

        //initializes the Layout for TreeView
        FXMLLoader tree = new FXMLLoader();
        tree.setLocation(getClass().getResource("treeView.fxml"));
        TreeView treeViewWindow = tree.load();
        root.setLeft(treeViewWindow);

        //initializes Layout for MessageView
        SplitPane contentWindow = FXMLLoader.load(getClass().getResource("messageView.fxml"));
        root.setCenter(contentWindow);

        //shows the Scene containing the rootLayout
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Opens a dialog and shows details about the creator of this project. If the user
     * clicks OK, the Window is closed and true is returned.
     *
     *
     * @return true if the user clicked OK, false otherwise.
     */





}
