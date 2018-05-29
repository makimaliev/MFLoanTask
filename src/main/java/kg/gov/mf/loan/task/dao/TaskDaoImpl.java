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
    public List<Task> getTasksByUserId(long id) {
        return getCurrentSession().createQuery("from Task where assignedToUserId = '" + id + "' and status = 'OPEN'").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTasksById(long id) {
        return (Task)getCurrentSession().createQuery("from Task where Id = '" + id + "'").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectTypeAndObjectId(String type, Long id) {
        return (Task)getCurrentSession().createQuery("from Task where objectType = '" + type + "' and objectId = '" + id + "'").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectType(String objectType) {
        String query = "from Task where objectType = :objectType and status = 'OPEN'";
        return (Task)getCurrentSession().createQuery(query)
                .setParameter("objectType", objectType)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List getTasksByObjectType(String objectType) {
        String query = "from Task where objectType = :objectType and status = 'OPEN'";
        return getCurrentSession().createQuery(query)
                .setParameter("objectType", objectType)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectId(Long objectId) {
        String query = "from Task where objectId = :objectId and status = 'OPEN'";
        return (Task)getCurrentSession().createQuery(query)
                .setParameter("objectId", objectId)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List getTasksByObjectId(Long objectId) {
        String query = "from Task where objectId = :objectId and status = 'OPEN'";
        return getCurrentSession().createQuery(query)
                .setParameter("objectId", objectId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List getOpenTasks(Long userId) {
        String query = "from Task where assignedToUserId = :assignedToUserId and status = 'OPEN'";
        return getCurrentSession().createQuery(query)
                .setParameter("assignedToUserId", userId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List getClosedTasks(Long userId) {
        String query = "from Task where assignedToUserId = :assignedToUserId and status = 'CLOSED'";
        return getCurrentSession().createQuery(query)
                .setParameter("assignedToUserId", userId)
                .list();
    }
}
