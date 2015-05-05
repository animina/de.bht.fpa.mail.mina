package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("window.fxml"));
        primaryStage.getIcons().add(new Image("Mail-icon.png"));
        primaryStage.setTitle("Endlich Mails");
        TreeView treeViewWindow = FXMLLoader.load(getClass().getResource("../view/treeView.fxml"));
        root.setLeft(treeViewWindow);
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}