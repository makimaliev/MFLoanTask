package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.task.model.ChatUser;

import java.util.List;

public interface ChatUserDao extends GenericDao<ChatUser>
{
    ChatUser getByName(String name);
    ChatUser getByUserName(String username);

    List<ChatUser> getAllByName(String name);
    List<ChatUser> getAllByUserName(String username);
}
