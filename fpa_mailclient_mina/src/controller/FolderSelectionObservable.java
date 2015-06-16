package controller;

import java.util.Observable;
import model.FileSystemItem;
import javafx.scene.control.TreeView;

/**
 * Created by mina on 09.06.15.
 */
public class FolderSelectionObservable extends Observable {

    private static TreeView<FileSystemItem> treeView;
    private static FolderSelectionObservable instance = new FolderSelectionObservable();

    private FolderSelectionObservable() {
    }

    public static FolderSelectionObservable getInstance() {
        if (instance == null) {
           return FolderSelectionObservable.instance;
        }
        return instance;
    }

    public void changeSomething(String pfad) {

        setChanged();
        notifyObservers(pfad);
    }


}
