package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.ChatUser;

import java.util.List;

public interface ChatUserService extends GenericService<ChatUser>
{
    ChatUser getByName(String name);
    ChatUser getByUserName(String username);

    List<ChatUser> getAllByName(String name);
    List<ChatUser> getAllByUserName(String username);
    List<ChatUser> listAllByDesc();
}
