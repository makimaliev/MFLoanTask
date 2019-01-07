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
    private ChatUserDao dao;

    @Autowired
    public ChatUserServiceImpl(ChatUserDao dao) {
        this.dao = dao;
    }

    @Override
    public ChatUser getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public ChatUser getByUserName(String username) {
        return dao.getByUserName(username);
    }

    @Override
    public List<ChatUser> getAllByName(String name) {
        return dao.getAllByName(name);
    }

    @Override
    public List<ChatUser> getAllByUserName(String username) {
        return dao.getAllByUserName(username);
    }
}
