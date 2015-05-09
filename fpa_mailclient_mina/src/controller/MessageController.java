package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Message;
import model.MessageImportance;
import model.MessageStakeholder;

import javafx.scene.control.TableColumn;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mina on 05.05.15.
 */
public class MessageController implements Initializable {

    private ObservableList<Message> messageContent = FXCollections.observableArrayList();

    @FXML
    private TableView<Message> messageTable;

    @FXML
    private TableColumn<Message, MessageImportance> importanceOfMessage;

    @FXML
    private TableColumn<Message, LocalDateTime> receivedAt;

    @FXML
    private TableColumn<Message, Boolean> readStatus;

    @FXML
    private TableColumn<Message, String> sender;

    @FXML
    private TableColumn<Message, String> subject;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        importanceOfMessage.setCellValueFactory(cellData -> cellData.getValue().importanceOfMessageProperty());
        receivedAt.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        readStatus.setCellValueFactory(cellData -> cellData.getValue().readStatusProperty().asObject());
        sender.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().nameProperty());
        subject.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        createMessage();
       // System.out.println(messageContent.toSting());
        System.out.println("Test");
        messageTable.setItems(messageContent);



    }

    public void createMessage() {
        Message test1 = new Message();
        test1.setId("a12");
        test1.setSender(new MessageStakeholder("Muddi", "muddi@home.de"));
        test1.setSubject("Erinnerung");
        test1.setImportanceOfMessage(MessageImportance.NORMAL);
        test1.setReceivedAt(LocalDateTime.now());
        test1.setReadStatus(true);
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);

    }

}
