package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.manage.service.GenericService;
import kg.gov.mf.loan.task.model.Task;

import java.util.List;

public interface TaskService extends GenericService<Task> {
    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);

    Task getTaskByObjectTypeAndObjectId(String type, long id);

    Task getTaskByObjectType(String objectType, Long userId);
    List<Task> getTasksByObjectType(String objectType, Long userId);

    Task getTaskByObjectId(Long objectId, Long userId);
    List<Task> getTasksByObjectId(Long objectId, Long userId);

    void completeTask(Long objectId, User user);

    List<Task> getOpenTasks(Long userId);
    List<Task> getClosedTasks(Long userId);
}
