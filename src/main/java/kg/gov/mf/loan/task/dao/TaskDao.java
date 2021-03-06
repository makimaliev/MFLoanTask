package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Map;

public interface TaskDao extends GenericDao<Task>
{
    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);
    Task getTaskByObjectTypeAndObjectId (String type, Long id);

    Task getTaskByObjectType(String objectType);
    List<Task> getTasksByObjectType(String objectType);

    Task getTaskByObjectId(Long objectId);
    List<Task> getTasksByObjectId(Long objectId);

    Task getTask(User user, Map<String, String> vars);
    List getTasks(Map<String, Object> vars);
    List getDocumentTasks(Long userId);

    DataTablesOutput<Task> list(long formUserId, long toUserId, DataTablesInput input);
}

