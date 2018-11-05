package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.model.GenericModel;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="chat_message")
public class Chat extends GenericModel
{
    private Date date = new Date();
    private String content;
    private String sender;
    private String receiver;

    @Transient
    private MessageType type;
    @Transient
    private String senderfullname;
    @Transient
    private String receiverfullname;

    //region GET-SET
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSenderfullname() {
        return senderfullname;
    }

    public void setSenderfullname(String senderfullname) {
        this.senderfullname = senderfullname;
    }

    public String getReceiverfullname() {
        return receiverfullname;
    }

    public void setReceiverfullname(String receiverfullname) {
        this.receiverfullname = receiverfullname;
    }
    //endregion
}
