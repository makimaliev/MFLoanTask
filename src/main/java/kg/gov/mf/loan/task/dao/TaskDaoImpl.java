package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.manage.dao.GenericDaoImpl;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String query = "from Task where objectType = :objectType";
        return (Task)getCurrentSession().createQuery(query)
                .setParameter("objectType", objectType)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByObjectType(String objectType) {
        String query = "from Task where objectType = :objectType";
        return getCurrentSession().createQuery(query)
                .setParameter("objectType", objectType)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByObjectId(Long objectId) {
        String query = "from Task where objectId = :objectId";
        return (Task)getCurrentSession().createQuery(query)
                .setParameter("objectId", objectId)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByObjectId(Long objectId) {
        String query = "from Task where objectId = :objectId";
        return getCurrentSession().createQuery(query)
                .setParameter("objectId", objectId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTask(User user, Map<String, String> atr) {
        String query = "from Task where assignedTo = :user";

        for(Map.Entry<String, String> item : atr.entrySet())
        {
            query += " and " +  item.getKey() + " = '" + item.getValue() + "' ";
        }

        return (Task)getCurrentSession().createQuery(query)
                .setParameter("user", user)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasks(Map<String, String> vars) {

        String query = "from Task";

        if(!vars.isEmpty())
        {
            query += " where ";
            int current = 1;
            for (Map.Entry<String, String> item : vars.entrySet())
            {
                query += item.getKey() + " = '" + item.getValue() + "'";
                if(current != vars.size())
                {
                    query += " and ";
                }
                current++;
            }
        }

        return getCurrentSession().createQuery(query)
                .list();
    }
}
