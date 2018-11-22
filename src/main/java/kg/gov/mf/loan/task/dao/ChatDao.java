package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.task.model.Chat;

import java.util.List;

public interface ChatDao extends GenericDao<Chat>
{
    List getMessages(String sender, String receiver);
}
