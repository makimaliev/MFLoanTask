package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.manage.dao.GenericDao;
import kg.gov.mf.loan.task.model.Task;
import java.util.List;

public interface TaskDao extends GenericDao<Task>
{
    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);
    Task getTaskByObjectTypeAndObjectId (String type, Long id);

    Task getTaskByObjectType(String objectType, Long userId);
    Task getTaskByObjectId(Long objectId, Long userId);
    List<Task> getTasksByObjectType(String objectType, Long userId);
    List<Task> getTasksByObjectId(Long objectId, Long userId);

    List<Task> getOpenTasks(Long userId);
    List<Task> getClosedTasks(Long userId);
}

