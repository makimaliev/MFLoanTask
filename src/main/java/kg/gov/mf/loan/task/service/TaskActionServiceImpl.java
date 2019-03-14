package kg.gov.mf.loan.task.service;


import kg.gov.mf.loan.task.model.TaskAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskActionServiceImpl extends GenericServiceImpl<TaskAction> implements TaskActionService {
}
