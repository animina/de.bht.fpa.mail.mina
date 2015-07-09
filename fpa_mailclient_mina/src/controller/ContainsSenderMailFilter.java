package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;

/**
 * Created by mina on 09.07.15.
 */
public class ContainsSenderMailFilter extends Filter {

    public ContainsSenderMailFilter (FpaMessageLoader fpaMessageLoader, String filterKriterium) {
        super(fpaMessageLoader, filterKriterium);
    }

    @Override
    public ObservableList<Message> getMessages(String path) {
        ObservableList<Message> liste1 = fpaMessageLoader.getMessages(path);
        // noch eine Liste, um Messages aufzufangen!
        ObservableList<Message> zwischenListe = FXCollections.observableArrayList();
        //mit For-Schleife über Einträge der liste1 iterieren, dann Prüfung mit
        //equalsIgnoreCase()

        for (Message a: liste1 ) {
            if (a.getSender().getMailAddress().contains(filterKriterium)) {
                zwischenListe.add(a);
            }
        }


        System.out.println("in getMessages() von ContainsSenderMailFilter");
        return zwischenListe;
    }
}
