package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mina on 30.06.15.
 */
public class FpaMessageLoaderImpl extends FpaMessageLoader {

    //
    public ObservableList<Message> getMessages(String path) {
        File file = new File(path);
        ObservableList<Message> liste = FXCollections.observableArrayList();

        if (file.listFiles() != null && file != null) {
            for (File each : file.listFiles()) {
                liste.add(readMessage(each));
            }
        }
        return liste;
    }

    private Message readMessage(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Message.class);
            Unmarshaller um = jc.createUnmarshaller();
            return (Message) um.unmarshal(file);

        } catch (JAXBException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}