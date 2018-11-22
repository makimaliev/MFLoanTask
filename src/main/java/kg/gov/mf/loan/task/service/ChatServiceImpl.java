package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.dao.ChatDao;
import kg.gov.mf.loan.task.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatServiceImpl extends GenericServiceImpl<Chat> implements ChatService
{
    ChatDao dao;

    @Autowired
    public ChatServiceImpl(ChatDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Chat> getMessages(String sender, String receiver) {
        return dao.getMessages(sender, receiver);
    }
}
