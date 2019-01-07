package kg.gov.mf.loan.task.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(name="chat_users")
public class ChatUser implements Serializable, Comparable<ChatUser> {

    private static final long serialVersionUID = -3307436748176180347L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ChatUser chatUser)
    {
        if (getName() == null || chatUser.getName() == null) {
            return 0;
        }
        return getName().compareTo(chatUser.getName());
    }
}
