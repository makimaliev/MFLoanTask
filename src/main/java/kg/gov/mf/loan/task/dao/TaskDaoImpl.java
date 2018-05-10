package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.manage.dao.GenericDaoImpl;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(long id)
    {
        return getCurrentSession().createQuery("from Task where assignedToUserId = '" + id + "'").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTasksById(long id)
    {
        return (Task)getCurrentSession().createQuery("from Task where Id = '" + id + "'").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectTypeAndObjectId(String type, Long id) {
        return (Task)getCurrentSession().createQuery("from Task where objectType = '" + type + "' and objectId = '" + id + "'").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectType(String type)
    {
        return (Task)getCurrentSession().createQuery("from Task where objectType = '" + type + "'").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectId(Long id) {
        return  (Task)getCurrentSession().createQuery("from Task where objectId = " + id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByObjectType(String type) {
        return getCurrentSession().createQuery("from Task where objectType = '" + type + "'").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasskByObjectId(Long id) {
        return getCurrentSession().createQuery("from Task where objectId = " + id).list();
    }
}
