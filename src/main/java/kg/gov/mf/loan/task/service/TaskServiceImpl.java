package kg.gov.mf.loan.task.service;


import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.manage.service.GenericServiceImpl;
import kg.gov.mf.loan.task.dao.TaskDao;
import kg.gov.mf.loan.task.model.Task;
import kg.gov.mf.loan.task.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends GenericServiceImpl<Task> implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> getTasksByUserId(long id) {
        return this.taskDao.getTasksByUserId(id);
    }

    @Override
    public Task getTasksById(long id)
    {
        return taskDao.getTasksById(id);
    }

    @Override
    public Task getTaskByObjectTypeAndObjectId(String type, long id) {
        return this.taskDao.getTaskByObjectTypeAndObjectId(type,id);
    }

    @Override
    public Task getTaskByObjectType(String objectType) {
        return this.taskDao.getTaskByObjectType(objectType);
    }

    @Override
    public List<Task> getTasksByObjectType(String objectType) {
        return taskDao.getTasksByObjectType(objectType);
    }

    @Override
    public Task getTaskByObjectId(Long objectId) {
        return this.taskDao.getTaskByObjectId(objectId);
    }

    @Override
    public List<Task> getTasksByObjectId(Long objectId) {
        return taskDao.getTasksByObjectId(objectId);
    }

    @Override
    public void completeTask(Long objectId, User user) {

        for(Task task : taskDao.getTasksByObjectId(objectId))
        {
            if(task.getStatus() == TaskStatus.OPEN) {
                task.setActualResolutionDate(new Date());
                task.setStatus(TaskStatus.CLOSED);
                task.setModifiedOn(new Date());
                if(task.getAssignedToUserId() == user.getId())
                    task.setModifiedByUserId(user.getId());
                taskDao.update(task);
            }
        }
    }

    @Override
    public List<Task> getOpenTasks(Long userId) {
        return taskDao.getOpenTasks(userId);
    }

    @Override
    public List<Task> getClosedTasks(Long userId) {
        return taskDao.getClosedTasks(userId);
    }
}
