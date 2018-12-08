package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.ChatUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatUserServiceImpl extends GenericServiceImpl<ChatUser> implements ChatUserService {
}
