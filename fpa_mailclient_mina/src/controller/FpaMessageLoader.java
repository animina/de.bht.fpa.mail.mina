package controller;

import javafx.collections.ObservableList;
import model.Message;

/**
 * Created by mina on 07.07.15.
 */
public abstract class FpaMessageLoader {

    public abstract ObservableList<Message> getMessages(String path);
}
