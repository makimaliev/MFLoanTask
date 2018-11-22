package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.Chat;

import java.util.List;

public interface ChatService extends GenericService<Chat>
{
    List<Chat> getMessages(String sender, String receiver);
}
