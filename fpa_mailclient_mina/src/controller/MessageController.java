package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Message;
import model.MessageImportance;
import model.MessageStakeholder;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mina on 05.05.15.
 */
public class MessageController implements Initializable {

    private ObservableList<Message> messageContent = FXCollections.observableArrayList();

    private final Node highPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../highprio-icon.png")));
    private final Node mediumPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../normalprio-icon.png")));
    private final Node lowPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../lowprio-icon.png")));



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


    @FXML
    private Label toLabel;

    @FXML
    private Label fromLabel;

    @FXML
    private Label betreffLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextArea contentTextArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receivedAt.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        sender.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().nameProperty());
        subject.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        createMessage();
        System.out.println(messageContent.toString());
        System.out.println("Test");
        messageTable.setItems(messageContent);

        showMessageDetails(null);

        // Listen for selection changes and show the person details when changed.
        messageTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMessageDetails(newValue));

     //   createExampleMessages();

    }

    public void createMessage() {
        Message test1 = new Message();
        test1.setId("a12");
        test1.setSender(new MessageStakeholder("Muddi", "muddi@home.de"));
        test1.setSubject("Erinnerung");
        test1.setImportanceOfMessage(MessageImportance.NORMAL);
        test1.setReceivedAt(LocalDateTime.now());
        test1.setReadStatus(true);
        test1.setText("Hallo!!");
        loadMessageIcons();
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);
        Message test2 = new Message();
        test2.setId("a13");
        test2.setSender(new MessageStakeholder("Muddi", "muddi@home.de"));
        test2.setSubject("Erinnerung2");
        test2.setImportanceOfMessage(MessageImportance.HIGH);
        test2.setReceivedAt(LocalDateTime.now());
        test2.setReadStatus(true);
        test2.setText("Wie geht es dir?");
        loadMessageIcons();
        messageContent.add(test2);



    }




    public void loadMessageIcons() {
        importanceOfMessage.setCellValueFactory(cellData -> cellData.getValue().importanceOfMessageProperty());
        importanceOfMessage.setCellFactory(cellData -> new TableCell<Message, MessageImportance>() {
            @Override
            protected void updateItem(MessageImportance item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    ImageView priorityIconView = null;
                    if (item == MessageImportance.HIGH) {
                        priorityIconView = new ImageView(new Image("highprio-icon.png"));
                    } else if (item == MessageImportance.NORMAL) {
                        priorityIconView = new ImageView(new Image("normalprio-icon.png"));
                    } else if (item == MessageImportance.LOW) {
                        priorityIconView = new ImageView(new Image("lowprio-icon.png"));
                    }
                    setGraphic(priorityIconView);
                }
            }
        });

        readStatus.setCellValueFactory(cellData -> cellData.getValue().readStatusProperty().asObject());
        readStatus.setCellFactory(cellData -> new TableCell<Message, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    ImageView messageReadImageView;
                    if (item) messageReadImageView = new ImageView(new Image("not-read-icon.png"));
                    else messageReadImageView = new ImageView((new Image("read-icon.png")));
                    setGraphic(messageReadImageView);
                }
            }
        });
    }

    private void showMessageDetails(Message message) {
        if (message != null) {
            // Fill the labels with info from the person object.
            fromLabel.setText(String.valueOf(message.getSender().getMailAddress()));
            toLabel.setText(String.valueOf(message.getRecipients()));
            dateLabel.setText(String.valueOf(DateUtil.format(message.getReceivedAt())));
            betreffLabel.setText(message.getSubject());
            contentTextArea.setText(message.getText());

            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            fromLabel.setText("");
            toLabel.setText("");
            dateLabel.setText("");
            betreffLabel.setText("");
            contentTextArea.setText("");
        }
    }

}
