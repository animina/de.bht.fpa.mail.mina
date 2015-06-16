package model;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Haupt on 26.03.15.
 */
@XmlRootElement
public class Message {

    private String id;
    private ObjectProperty<MessageImportance> importanceOfMessage;
    private ObjectProperty<LocalDateTime> receivedAt;
    private BooleanProperty readStatus;
    private ObjectProperty<MessageStakeholder> sender;
    private StringProperty subject;
    private StringProperty text;

    //alte Version mit Liste
    private List<MessageStakeholder> recipients;

    //neue bzw ziwschenversion mit ObjectProperty
    //private ObjectProperty <MessageStakeholder> recipients;

    public Message() {
        this.importanceOfMessage = new SimpleObjectProperty<>();
        this.subject = new SimpleStringProperty();
        this.readStatus = new SimpleBooleanProperty();
        this.receivedAt = new SimpleObjectProperty<>();
        this.sender = new SimpleObjectProperty<>();
        this.text = new SimpleStringProperty();

      //alte Version mit Liste
        this.recipients = new ArrayList<>();

        //neue Version oder Zwischenversion mit ObjectProperty
        //this.recipients = new SimpleObjectProperty<>();
    }



    @XmlElementWrapper (name="recipients")
    @XmlElement(name = "recipient")
    public List<MessageStakeholder> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<MessageStakeholder> recipients) {
        this.recipients = recipients;
    }

/*
    @XmlElementWrapper
    @XmlElement(name = "recipient")
    public List<MessageStakeholder> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<MessageStakeholder> recipients) {
        this.recipients = recipients;
    }
*/

    //alte Version von set ung getRecipients()
   // public void setRecipients(MessageStakeholder messageStakeholder) {
     //   recipients.set(messageStakeholder);
    //}





   // public MessageStakeholder getRecipients() {
    //    return recipients.get();
   // }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ObjectProperty<MessageImportance> importanceOfMessageProperty() {
        return importanceOfMessage;
    }

    public void setImportanceOfMessage(MessageImportance importanceOfMessage) {
        this.importanceOfMessage.set(importanceOfMessage);
    }

    public MessageImportance getImportanceOfMessage() {
        return this.importanceOfMessage.get();
    }

    public ObjectProperty<LocalDateTime> receivedAtProperty() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt.set(receivedAt);
    }

    //altes getRecievedAt()
    //public LocalDateTime getReceivedAt() {
    //    return this.receivedAt.get();    }

    //Neues getRecievedAt()

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDateTime getReceivedAt() {
        return this.receivedAt.get();
    }

    public BooleanProperty readStatusProperty() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus.set(readStatus);
    }

    public boolean getReadStatus() {
        return this.readStatus.get();
    }

    public ObjectProperty<MessageStakeholder> senderProperty() {
        return sender;
    }

    public void setSender(MessageStakeholder messageStakeholder) {
        sender.set(messageStakeholder);
    }

    public MessageStakeholder getSender() {
        return sender.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getSubject() {
        return this.subject.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getText() {
        return this.text.get();
    }
}