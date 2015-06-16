package model;

import java.io.File;

/**
 * Created by mina on 07.06.15.
 */
public class Directory extends FileSystemItem {

    public Directory (File file) {
        super(file);
        init();

    }

    public void init() {

        File [] files = file.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                Directory neu = new Directory(file);
                super.getChildren().add(neu);
            }
        }
    }
}
