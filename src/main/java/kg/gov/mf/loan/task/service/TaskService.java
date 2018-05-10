package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.manage.service.GenericService;
import kg.gov.mf.loan.task.model.Task;

import java.util.List;

public interface TaskService extends GenericService<Task> {
    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);

    Task getTaskByObjectTypeAndObjectId(String type, long id);

    Task getTaskByObjectType(String type);
    Task getTaskByObjectId(Long id);

    List<Task> getTasksByObjectType(String type);
    List<Task> getTasksByObjectId(Long id);
}
