package model;

import javafx.scene.control.TreeItem;
import java.io.File;

/**
 * Created by mina on 07.06.15.
 */
public abstract class FileSystemItem extends TreeItem{

    protected File file;
    public boolean fileIsLeaf = false;

    public FileSystemItem (File file) {
        super(file.getName());
        this.file = file;
        fileIsLeaf = isLeaf();
    }

    //Monas isLeaf() verhindert mehrmaliges Durchlaufen einzelner Elemente

    @Override
    public boolean isLeaf() {
        if (file.isFile()) {
            return true;
        } else {
            return false;
        }
        //return super.isLeaf();
    }
}
