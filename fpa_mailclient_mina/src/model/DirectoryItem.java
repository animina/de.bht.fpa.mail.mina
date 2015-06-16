package model;

import java.io.File;

/**
 * Created by mina on 07.06.15.
 */
public class DirectoryItem extends FileSystemItem {

    public DirectoryItem(File file) {
        super(file);
        init();

    }

    public void init() {

        File [] files = file.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                DirectoryItem neu = new DirectoryItem(file);
                super.getChildren().add(neu);
            }
        }
    }
}
