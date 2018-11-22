package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.task.model.Chat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class ChatDaoImpl extends GenericDaoImpl<Chat> implements ChatDao
{
    @Override
    public List getMessages(String sender, String receiver) {

        List messages = entityManager.createQuery("select c from Chat c where ((c.sender = :sender OR c.sender = :receiver) AND (c.receiver = :receiver OR c.receiver = :sender)) order by c.id desc")
                .setParameter("sender", sender)
                .setParameter("receiver", receiver)
                .setMaxResults(10)
                .getResultList();

        Collections.sort(messages, new Comparator<Chat>(){
            @Override
            public int compare(Chat c1, Chat c2) {
                return c1.getDate().compareTo(c2.getDate());
            }
        });

        return messages;
    }
}
