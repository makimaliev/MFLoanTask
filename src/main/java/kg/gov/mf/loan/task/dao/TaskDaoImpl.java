package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.manage.dao.GenericDaoImpl;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {

    @Override
    public List<Task> getTasksByUserId(long id)
    {
        return getCurrentSession().createQuery("from Task where assignedToUserId = '" + id + "'").list();
    }

    @Override
    public Task getTaskByObjectTypeAndObjectId(String type, long id)
    {
        return (Task)getCurrentSession().createQuery("from Task where objectType = '" + type + "' and objectId = '" + id + "'").uniqueResult();
    }
}
