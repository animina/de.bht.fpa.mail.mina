package controller;

import java.util.Observable;
import model.FileSystemItem;
import javafx.scene.control.TreeView;

/**
 * Created by mina on 09.06.15.
 */
public class FolderSelectionObservable extends Observable {

    private static TreeView<FileSystemItem> treeView;
    private static FolderSelectionObservable instance;

    private FolderSelectionObservable(TreeView<FileSystemItem> treeView) {
        FolderSelectionObservable.treeView = treeView;
        treeView.getSelectionModel().selectedItemProperty().addListener((e) -> changedSomething());

    }

    public static FolderSelectionObservable getInstance(TreeView<FileSystemItem> treeView) {
        if (instance == null) {
            instance = new FolderSelectionObservable(treeView);
        }
        return instance;
    }

    public void changedSomething() {
        setChanged();
        notifyObservers();
    }
}
