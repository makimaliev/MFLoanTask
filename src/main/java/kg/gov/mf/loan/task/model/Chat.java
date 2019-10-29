package kg.gov.mf.loan.task.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="chat_message")
public class Chat// extends GenericModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long version = 1L;

    private String uuid = UUID.randomUUID().toString();

    @Column(name = "record_status", columnDefinition = "int default 1")
    private int record_status = 1;

    private Date date = new Date();
    private String content;
    private String sender;
    private String receiver;
    private Long docId;

    @Transient
    private MessageType type;
    @Transient
    private String senderfullname;
    @Transient
    private String receiverfullname;

    //region GET-SET


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRecord_status() {
        return record_status;
    }

    public void setRecord_status(int record_status) {
        this.record_status = record_status;
    }

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

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }
    //endregion
}
