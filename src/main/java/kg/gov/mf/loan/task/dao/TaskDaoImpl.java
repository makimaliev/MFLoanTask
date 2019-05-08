package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.task.model.Task;
import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
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
    public List<Task> getTasks(Map<String, Object> vars) {

        String q = "select t from Task t where 1=1";

        if(!vars.isEmpty())
        {
            q += "";
            for (Map.Entry<String, Object> item : vars.entrySet())
            {
                q += " and t." + item.getKey() + " = :" + item.getKey() + "";
            }
        }

        Query query = entityManager.createQuery(q);

        if(!vars.isEmpty())
        {
            for (Map.Entry<String, Object> item : vars.entrySet())
            {
                query.setParameter(item.getKey(), item.getValue());
            }
        }

        return query.getResultList();
    }

    @Override
    public List getDocumentTasks(Long userId) {
        return entityManager.createQuery("select t from Task t where (t.assignedToUserId = :userId or t.assignedTo.id = :userId) and t.status = 'OPEN' and t.objectType = 'Document'", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public DataTablesOutput<Task> list(long formUserId, long toUserId, DataTablesInput input) {

        String selectQuery = "SELECT t FROM Task t WHERE 1=1";
        String countQuery = "SELECT count(t) FROM Task t WHERE 1=1";

        String q = "";

        if(formUserId !=  0)
        {
            q += " and t.createdBy.id = " + formUserId;
        }

        if(toUserId !=  0)
        {
            q += " and t.assignedTo.id = " + toUserId;
        }

        for(Column column : input.getColumns())
        {
            if(!column.getSearch().getValue().isEmpty())
            {
                if(column.getName().contains("Date") && !column.getName().contains("DueDate"))
                {
                    q += " and (date(t." + column.getName() + ") between :" + column.getName() + "fd and :" + column.getName() + "td)";
                }
                else if(column.getName().contains("DueDate"))
                {
                    q += " and t.documentDueDate IS " + column.getSearch().getValue() + " NULL";
                }
                else
                {
                    q += " and cast(t." + column.getName() + " as char) like :" + column.getName();
                }
            }
        }

        String columnToSort = input.getColumns().get(input.getOrder().get(0).getColumn()).getName();
        String sortDirection = input.getOrder().get(0).getDir();

        q += " order by t." + columnToSort + " " + sortDirection;

        /*
        String orderdata = "";
        for(Order order : input.getOrder())
        {
            orderdata += " t." + input.getColumns().get(order.getColumn()).getName() + " " + order.getDir();
        }
        */

        Query querySelect = entityManager.createQuery(selectQuery + q, Task.class);
        Query queryCount = entityManager.createQuery(countQuery + q, Long.class);

        for(Column column : input.getColumns())
        {
            if(!column.getSearch().getValue().isEmpty()) {
                if(column.getName().contains("Date") && !column.getName().contains("DueDate"))
                {
                    Date[] dates = getDates(column.getSearch().getValue());
                    querySelect.setParameter(column.getName() + "fd", dates[0]);
                    querySelect.setParameter(column.getName() + "td", dates[1]);
                    queryCount.setParameter(column.getName() + "fd", dates[0]);
                    queryCount.setParameter(column.getName() + "td", dates[1]);
                }
                else
                {
                    if(!column.getName().contains("DueDate")) {
                        querySelect.setParameter(column.getName(), "%" + column.getSearch().getValue() + "%");
                        queryCount.setParameter(column.getName(), "%" + column.getSearch().getValue() + "%");
                    }
                }
            }
        }

        Long count = (Long)queryCount
                .getSingleResult();
        List<Task> tasks = querySelect
                        .setFirstResult(input.getStart())
                        .setMaxResults(input.getLength())
                        .getResultList();

        DataTablesOutput<Task> dataTablesOutput = new DataTablesOutput<Task>();
        dataTablesOutput.setDraw(input.getDraw());
        dataTablesOutput.setRecordsTotal(count());
        dataTablesOutput.setRecordsFiltered(count);
        dataTablesOutput.setData(tasks);

        return dataTablesOutput;
    }

    private Date[] getDates(String dates) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");

        String[] allDate = dates.split("-");

        String fd = allDate[0].replaceAll("\\s+","");
        String td = allDate[1].replaceAll("\\s+","");

        Date[] d = new Date[2];

        try {
            d[0] = format.parse(fd);
            d[1] = format.parse(td);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
