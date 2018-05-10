package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.manage.service.GenericServiceImpl;
import kg.gov.mf.loan.task.dao.TaskDao;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends GenericServiceImpl<Task> implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> getTasksByUserId(long id)
    {
        return this.taskDao.getTasksByUserId(id);
    }

    @Override
    public Task getTasksById(long id)
    {
        return taskDao.getTasksById(id);
    }

    @Override
    public Task getTaskByObjectTypeAndObjectId(String type, long id)
    {
        return this.taskDao.getTaskByObjectTypeAndObjectId(type,id);
    }

    @Override
    public Task getTaskByObjectType(String type) {
        return this.taskDao.getTaskByObjectType(type);
    }

    @Override
    public Task getTaskByObjectId(Long id) {
        return this.taskDao.getTaskByObjectId(id);
    }

    @Override
    public List<Task> getTasksByObjectType(String type) {
        return taskDao.getTasksByObjectType(type);
    }

    @Override
    public List<Task> getTasksByObjectId(Long id) {
        return taskDao.getTasskByObjectId(id);
    }
}
