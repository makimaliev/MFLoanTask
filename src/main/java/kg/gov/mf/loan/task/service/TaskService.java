package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.manage.service.GenericService;
import kg.gov.mf.loan.task.model.Task;

import java.util.List;

public interface TaskService extends GenericService<Task> {
    public List<Task> getTasksByUserId(long id);
}
