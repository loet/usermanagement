package ch.mobi.ueliloetscher.learning.usermanagement.validation;

import java.io.Serializable;
import java.util.Collection;

public class ViolationMessageDTO implements Serializable {

    private Collection<String> messages;

    public ViolationMessageDTO(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }

    public void setMessages(Collection<String> messages) {
        this.messages = messages;
    }
}
