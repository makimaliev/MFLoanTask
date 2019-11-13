package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.dao.ChatUserDao;
import kg.gov.mf.loan.task.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatUserServiceImpl extends GenericServiceImpl<ChatUser> implements ChatUserService
{
    private ChatUserDao chatUserDao;

    @Autowired
    public ChatUserServiceImpl(ChatUserDao chatUserDao) {
        this.chatUserDao = chatUserDao;
    }

    @Override
    public ChatUser getByName(String name) {
        return chatUserDao.getByName(name);
    }

    @Override
    public ChatUser getByUserName(String username) {
        return chatUserDao.getByUserName(username);
    }

    @Override
    public List<ChatUser> getAllByName(String name) {
        return chatUserDao.getAllByName(name);
    }

    @Override
    public List<ChatUser> getAllByUserName(String username) {
        return chatUserDao.getAllByUserName(username);
    }

    @Override
    public List<ChatUser> listAllByDesc() {
        return chatUserDao.listAllByDesc();
    }
}
