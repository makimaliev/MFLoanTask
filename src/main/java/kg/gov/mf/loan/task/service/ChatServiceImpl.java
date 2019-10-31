package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.Chat;
import kg.gov.mf.loan.task.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chatService")
@Transactional
public class ChatServiceImpl /*extends GenericServiceImpl<Chat>*/ implements ChatService
{
    @Autowired
    ChatRepository chatRepository;


    @Override
    public List<Chat> getMessages(String sender, String receiver) {
        return chatRepository.findAllByReceiverAndSenderOrSenderAndReceiver(receiver,sender,receiver,sender);
    }

    @Override
    public List<Chat> getNotRead(int readed,String receiver) {
        return chatRepository.findAllByReadedAndReceiver(readed,receiver);
    }
}
