package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.task.model.ChatUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatUserDaoImpl extends GenericDaoImpl<ChatUser> implements ChatUserDao
{

    @Override
    public ChatUser getByName(String name) {
        return (ChatUser)entityManager.createQuery("select cu from ChatUser cu where cu.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public ChatUser getByUserName(String username) {
        return (ChatUser)entityManager.createQuery("select cu from ChatUser cu where cu.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List getAllByName(String name) {
        return entityManager.createQuery("select cu from ChatUser cu where cu.name LIKE :name")
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List getAllByUserName(String username) {
        return entityManager.createQuery("select cu from ChatUser cu where cu.username LIKE :username")
                .setParameter("username", "%" + username + "%")
                .getResultList();
    }
}
