package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.core.service.GenericService;
import kg.gov.mf.loan.task.model.ObjectType;
import kg.gov.mf.loan.task.model.Task;
import kg.gov.mf.loan.task.model.TaskObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface TaskService extends GenericService<Task> {

    List<Task> getTasksByUserId(long id);
    Task getTasksById(long id);

    Task getTaskByObjectTypeAndObjectId(String type, long id);

    Task getTaskByObjectType(String objectType);
    List<Task> getTasksByObjectType(String objectType);

    Task getTaskByObjectId(Long objectId);
    List<Task> getTasksByObjectId(Long objectId);

    Task getTask(User user, Map<String, String> vars);
    List<Task> getTasks(Map<String, String> vars);

    void completeTask(Long objectId, User user, String result);

    ObjectType getEntity(String entity);
    List<ObjectType> getEntities();

    List<Field> getFields(Class<?> type);
    Map<String, Object> getFields(String className);

    List getData(String query);
    List queryBuilder(TaskObject taskObject);
}
