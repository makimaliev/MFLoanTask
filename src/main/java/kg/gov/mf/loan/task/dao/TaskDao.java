package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.manage.dao.GenericDao;
import kg.gov.mf.loan.task.model.Task;
import java.util.List;

public interface TaskDao extends GenericDao<Task>
{
    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);
    Task getTaskByObjectTypeAndObjectId (String type, Long id);
    Task getTaskByObjectType(String type);
    Task getTaskByObjectId(Long id);
    List<Task> getTasksByObjectType(String type);
    List<Task> getTasskByObjectId(Long id);
}

