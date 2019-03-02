package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository("taskDao")
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(long id) {
        return entityManager.createQuery("select t from Task t where t.assignedToUserId = :id or t.assignedTo.id = :id and t.status = 'OPEN'")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTasksById(long id) {
        return (Task)entityManager.createQuery("select t from Task t where t.assignedToUserId = :id and t.status = 'OPEN'")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectTypeAndObjectId(String type, Long id) {
        return (Task)entityManager.createQuery("select t from Task t where t.objectType = :objectType and t.objectId = :id")
                .setParameter("objectType", type)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectType(String objectType) {
        return (Task)entityManager.createQuery("select t from Task t where t.objectType = :objectType")
                .setParameter("objectType", objectType)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByObjectType(String objectType) {
        return entityManager.createQuery("select t from Task t where t.objectType = :objectType")
                .setParameter("objectType", objectType)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectId(Long objectId) {
        return (Task)entityManager.createQuery("select t from Task t where t.objectId = :objectId")
                .setParameter("objectId", objectId)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByObjectId(Long objectId) {
        return entityManager.createQuery("select t from Task t where t.objectId = :objectId")
                .setParameter("objectId", objectId)
                .getResultList();

    }

    @Override
    @Transactional(readOnly = true)
    public Task getTask(User user, Map<String, String> atr) {

        String query = "select t from Task t where t.assignedTo = :user";

        for(Map.Entry<String, String> item : atr.entrySet())
        {
            query += " and t." +  item.getKey() + " = '" + item.getValue() + "' ";
        }

        List result = entityManager.createQuery(query)
                .setParameter("user", user)
                .getResultList();
        return !result.isEmpty() ? (Task)result.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasks(Map<String, String> vars) {

        String query = "select t from Task t ";

        if(!vars.isEmpty())
        {
            query += " where 1=1";
            for (Map.Entry<String, String> item : vars.entrySet())
            {
                query += " and t." + item.getKey() + " = '" + item.getValue() + "'";
            }
        }

        return entityManager.createQuery(query)
                .getResultList();
    }
}
