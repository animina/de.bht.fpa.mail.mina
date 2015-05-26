package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by mina on 24.05.15.
 */

    @XmlRootElement(name = "messages")
    public class MessageListWrapper {
        private List<Message> messages;

        @XmlElement(name = "messages")
        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

    }
