package kg.gov.mf.loan.task.listener;

import kg.gov.mf.loan.task.component.AuthenticationFacade;
import kg.gov.mf.loan.task.component.AutowireHelper;
import kg.gov.mf.loan.task.model.Task;
import kg.gov.mf.loan.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class TaskListener {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public TaskListener() {}

    @PostPersist
    public void entityPrePersist(Task t) {
        perform(t);
    }

    @PostUpdate
    public void entityPostUpdate(Task t) {
        perform(t);
    }

    @PostRemove
    public void entityPostRemove(Task t) {
        perform(t);
    }

    @Transactional
    void perform(Task t)
    {
        AutowireHelper.autowire(this, this.taskService);
        AutowireHelper.autowire(this, this.authenticationFacade);

        String user = authenticationFacade.getUser();

        //taskService.add(log);
    }
}
