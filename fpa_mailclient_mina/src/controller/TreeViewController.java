package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DirectoryItem;
import model.FileSystemItem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mina on 05.05.15.
 */
public class TreeViewController implements Initializable {

    @FXML
    private TreeView<String> fileTree;
    private DirectoryItem rootDir = new DirectoryItem(new File("TreeRoot"));
    private final Node folderIcon = new ImageView (new Image (getClass().getResourceAsStream("../Mail-icon.png")));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            TreeItem<String> root = new TreeItem<>("TreeRoot");
            root.setGraphic(folderIcon);
            for(int i = 1; i < 6; ++i) {
                TreeItem<String> subFolder = new TreeItem<>("Subfolder " + i);
                root.getChildren().add(subFolder);
                subFolder.setGraphic(new ImageView (new Image (getClass().getResourceAsStream("../Mail-icon.png"))));
            }
            root.setExpanded(true);
            FolderSelectionObservable.getInstance();
                fileTree.setRoot(new DirectoryItem(new File("TreeRoot")));
            //fileTree.setRoot(root);
            treeListener();
    }

    public void treeListener(){
        fileTree.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends TreeItem<String>> observable,
                            TreeItem<String> old_val, TreeItem<String> new_val) {
                        TreeItem<String> selectedItem = new_val;
                        System.out.println("Selected Text : " + selectedItem.getValue());
                        FolderSelectionObservable.getInstance().changeSomething(selectedItem.getValue());
                        // do what ever you want
                    }

                });
    }

}
