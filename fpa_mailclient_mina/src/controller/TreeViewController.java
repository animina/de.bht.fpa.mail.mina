package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.tree.TreeNode;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mina on 05.05.15.
 */
public class TreeViewController implements Initializable {

    @FXML
    private TreeView<String> fileTree;

    private final Node folderIcon = new ImageView (new Image (getClass().getResourceAsStream("../Mail-icon.png")));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            TreeItem<String> root = new TreeItem<>("Folder");
            root.setGraphic(folderIcon);
            for(int i = 1; i < 6; ++i) {
                TreeItem<String> subFolder = new TreeItem<>("Subfolder " + i);
                root.getChildren().add(subFolder);
                subFolder.setGraphic(new ImageView (new Image (getClass().getResourceAsStream("../Mail-icon.png"))));
            }
            root.setExpanded(true);
            fileTree.setRoot(root);
        }

}
