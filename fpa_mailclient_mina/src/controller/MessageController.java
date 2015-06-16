package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Message;
import model.MessageImportance;
import javafx.event.ActionEvent;

import model.MessageStakeholder;
import model.MessageListWrapper;

import util.DateUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by mina on 05.05.15.
 */
public class MessageController implements Initializable, Observer {

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

    @FXML
    private Label recipientsLabel;

    @FXML
    private Label fromLabel;

    @FXML
    private Label betreffLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextArea contentTextArea;

    @FXML
    private Label TestnachrichtLabel;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
                    if (item) messageReadImageView = new ImageView(new Image("read-icon.png"));
                    else messageReadImageView = new ImageView((new Image("not-read-icon.png")));
                    setGraphic(messageReadImageView);
                }
            }
        });

        receivedAt.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        receivedAt.setCellFactory(cellData -> new TableCell<Message, LocalDateTime>()
        {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else setText(formatter.format(item));
            }
        });
        /*
        dateLabel.setText(String.valueOf(DateUtil.format(message.getReceivedAt())));

        importanceOfMessage.setCellValueFactory(cellData -> cellData.getValue().importanceOfMessageProperty());
        importanceOfMessage.setCellFactory(cellData -> new TableCell<Message, MessageImportance>() {
    */
            sender.setCellValueFactory(cellData->cellData.getValue().senderProperty().get().nameProperty());
            subject.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());

            //createMessage();

            System.out.println(messageContent.toString());
            System.out.println("Test");
            messageTable.setItems(messageContent);
            showMessageDetails(null);

            // Listen for selection changes and show the person details when changed.
            messageTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                    -> showMessageDetails(newValue));


        //generateMessages();
        System.out.println("initialize messagetable");
        fillTable("src/xml_messages");
        System.out.println("initialize messagetable");
        // Clear person details.
        showMessageDetails(null);
        // Listen for selection changes and show the person details when changed.
        messageTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMessageDetails(newValue));

        }




    private void showMessageDetails(Message message) {
        if (message != null) {

            // Fill the labels with info from the person object.
            System.out.println("In showMessageDetails: message != null");
            fromLabel.setText(String.valueOf(message.getSender().getMailAddress()));
            System.out.println("FromLabel: " + fromLabel);
            recipientsLabel.setText(String.valueOf(message.getRecipients().get(0).getMailAddress()));
            System.out.println("ReceipientsLabel: " + recipientsLabel);
            dateLabel.setText(String.valueOf(DateUtil.format(message.getReceivedAt())));
            betreffLabel.setText(message.getSubject());
            contentTextArea.setText(message.getText());
            TestnachrichtLabel.setText("Eine Testnachricht von " + message.getSender().getMailAddress());
            //Monas Version
            handleLineSelected(message);
           //hier gibts nen Fehler
           // markAsUnread(message);


        } else {
            // Person is null, remove all the text.
            fromLabel.setText("");
            recipientsLabel.setText("");
            dateLabel.setText("");
            betreffLabel.setText("");
            contentTextArea.setText("");
            TestnachrichtLabel.setText("");
        }
    }
    /**
     * Opens the xml file, reads all the information and returns a new message
     * object.
     *
     * @param file The passed xml file
     * @return The resulting Message object
     */
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



    /**
     * Fills the table by the xml files within the passed path.
     *
     * @param path the path containing the xml files.
     */
    private void fillTable(String path) {
        System.out.println("Anfang von fillTable()");

        File file = new File(path);
        System.out.println("Vor der for-Schleife fillTable()");
        //   try {
        //     System.out.println("In fillTable() Anfang von try");
        System.out.println(file.listFiles());
        for (File each : file.listFiles()) {
            System.out.println("In der For-Schleife: fillTable()");
            messageContent.add(readMessage(each));
        }
        messageTable.setItems(messageContent);
    }

   private void handleLineSelected (Message message) {
       if (message.getReadStatus() == false) {
           message.setReadStatus(true);
           try {
               saveMessage(messageTable.getSelectionModel().getSelectedItem());
           } catch (JAXBException e) {
               e.printStackTrace();
           }
       }
   }

    @FXML
    public void markAsUnread() {
        System.out.println("markAsUnread()-Methode");
       // Message message = new Message(messageTable.getSelectionModel().getSelectedItem());
        if (messageTable.getSelectionModel().getSelectedItem() != null) {
            messageTable.getSelectionModel().getSelectedItem().setReadStatus(false);
        }
        try {
           saveMessage(messageTable.getSelectionModel().getSelectedItem());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveMessage(Message msg) throws JAXBException{
        final File currentDir = new File("src/xml_messages");
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(msg, new File(currentDir.getAbsolutePath() + "/" + msg.getId() + ".xml"));
        System.out.println("savemessage()");
        System.out.println(new File(currentDir.getAbsolutePath() + "/" + msg.getId() + ".xml"));
    }

    @Override
        public void update(Observable o, Object arg) {
        System.out.println("Jetzt wird geupdated!");
        System.out.println("Das Object args vor der fillTable-Methode: " + arg );

        //forall messages in Table: +  showMessageDetails();


        fillTable("src/xml_messages");
    }

    //  }
      //  catch (NullPointerException e) {
      //      System.out.println("Keine Daten in da Liste");
      //  }

        /* public void createMessage() {
        Message test1 = new Message();
        test1.setId("a12");
        test1.setSender(new MessageStakeholder("Muddi", "muddi@home.de"));
        test1.setSubject("Erinnerung");
        test1.setImportanceOfMessage(MessageImportance.NORMAL);
        test1.setReceivedAt(LocalDateTime.now());
        test1.setReadStatus(true);
        test1.setRecipients(new MessageStakeholder("Icke", "Icke@Berlin.de"));
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
        test2.setRecipients(new MessageStakeholder("Jemand", "Jemand@Berlin.de"));
        test2.setReadStatus(true);
        test2.setText("Wie geht es dir?");
        loadMessageIcons();
        messageContent.add(test2);



    }
*/


}
