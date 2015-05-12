package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Message;
import model.MessageImportance;
import model.MessageStakeholder;

import javafx.scene.control.TableColumn;
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

    private final Node highPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../high-priority-icon.png")));
    private final Node mediumPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../medium-priority-icon.png")));
    private final Node lowPriorityIcon = new ImageView(new Image(getClass().getResourceAsStream("../low-priority-icon.png")));



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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receivedAt.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        sender.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().nameProperty());
        subject.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        createMessage();
        System.out.println(messageContent.toString());
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
        loadMessageIcons();
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);
        messageContent.add(test1);


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
                        priorityIconView = new ImageView(new Image("high-priority-icon.png"));
                    } else if (item == MessageImportance.NORMAL) {
                        priorityIconView = new ImageView(new Image("medium-priority-icon.png"));
                    } else if (item == MessageImportance.LOW) {
                        priorityIconView = new ImageView(new Image("low-priority-icon.png"));
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
                    if (item) messageReadImageView = new ImageView(new Image("read-me-icon.png"));
                    else messageReadImageView = new ImageView((new Image("read-icon.png")));
                    setGraphic(messageReadImageView);
                }
            }
        });
    }

}
