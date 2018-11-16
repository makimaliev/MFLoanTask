package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.admin.sys.model.User;
import kg.gov.mf.loan.core.service.GenericServiceImpl;
import kg.gov.mf.loan.task.dao.TaskDao;
import kg.gov.mf.loan.task.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends GenericServiceImpl<Task> implements TaskService {

    private TaskDao taskDao;
    private SessionFactory sessionFactory;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, SessionFactory sessionFactory) {
        this.taskDao = taskDao;
        this.sessionFactory = sessionFactory;
    }

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
    public Task getTaskByObjectTypeAndObjectId(String type, long id) {
        return this.taskDao.getTaskByObjectTypeAndObjectId(type,id);
    }

    @Override
    public Task getTaskByObjectType(String objectType)
    {
        return this.taskDao.getTaskByObjectType(objectType);
    }

    @Override
    public List<Task> getTasksByObjectType(String objectType)
    {
        return taskDao.getTasksByObjectType(objectType);
    }

    @Override
    public Task getTaskByObjectId(Long objectId)
    {
        return this.taskDao.getTaskByObjectId(objectId);
    }

    @Override
    public List<Task> getTasksByObjectId(Long objectId)
    {
        return taskDao.getTasksByObjectId(objectId);
    }

    @Override
    public Task getTask(User user, Map<String, String> vars) {
        return  taskDao.getTask(user, vars);
    }


    @Override
    public List<Task> getTasks(Map<String, String> vars) {
        return taskDao.getTasks(vars);
    }

    @Override
    public void completeTask(Long objectId, User user, String result) {

        Map<String, String> vars = new HashMap<>();
        vars.put("status", "OPEN");
        vars.put("objectId", objectId.toString());

        Task task = taskDao.getTask(user, vars);
        if(task != null) {
            task.setActualResolutionDate(new Date());
            task.setStatus(TaskStatus.CLOSED);
            task.setResolutionSummary(result);
            taskDao.update(task);
        }
    }

    //******************************************************************************************************************
    @Override
    public List<ObjectType> getEntities() {

        Map<String, ClassMetadata>  entities = sessionFactory.getAllClassMetadata();

        List<ObjectType> objects = new ArrayList<>();

        for(Map.Entry<String, ClassMetadata> item : entities.entrySet())
        {
            ObjectType ot = new ObjectType();

            ot.setObject(item.getValue().getMappedClass().getSimpleName());
            ot.setClassName(item.getValue().getMappedClass().getName());

            /*
            Map<String, Object> f = new HashMap<>();
            for(Field field : getFields(item.getValue().getMappedClass()))
            {
                f.put(field.getName(), field.getType().getSimpleName());
            }
            ot.setFields(f);
            */
            objects.add(ot);
        }

        return objects;
    }

    @Override
    public List<Field> getFields(Class<?> type) {

        List<Field> fields = new ArrayList<Field>();

        for (Class<?> c = type; c != null; c = c.getSuperclass())
        {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }

        return fields;
    }

    @Override
    public Map<String, Object> getFields(String className) {

        Class<?> type = null;
        try {
            type = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Object> fields = new HashMap<>();

        for(Field field : type.getDeclaredFields())
        {
            fields.put(field.getName(), field.getType().getSimpleName());
        }

        return fields;
    }

    @Override
    public ObjectType getEntity(String entity) {

        Map<String, ClassMetadata>  entities = sessionFactory.getAllClassMetadata();

        for(Map.Entry<String, ClassMetadata> item : entities.entrySet())
        {
            if(item.getValue().getMappedClass().getSimpleName().equals(entity))
            {
                ObjectType ot = new ObjectType();
                ot.setObject(item.getValue().getMappedClass().getSimpleName());

                /*
                Map<String, Object> f = new HashMap<>();
                for (Field field : getFields(item.getValue().getMappedClass())) {
                    f.put(field.getName(), field.getType().getSimpleName());
                }
                ot.setFields(f);
                */

                return ot;
            }
        }

        return new ObjectType();
    }
    //******************************************************************************************************************

    @Override
    public List getData(String query) {

        Session session;

        try {
            session =  sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session =  sessionFactory.openSession();
        }

        return session.createQuery(query).list();
    }

    @Override
    public List queryBuilder(TaskObject taskObject) {
        Session session;

        try {
            session =  sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session =  sessionFactory.openSession();
        }

        String query = "from " + taskObject.getTable();
        Query q = session.createQuery(query);

        List<ObjectData> vars = taskObject.getProperties();

        if(!vars.isEmpty())
        {
            query += " where ";
            int current = 1;
            for (ObjectData item : vars)
            {
                query += item.getProperty() + " " + item.getOperator() + " :" + item.getProperty();
                if(current != vars.size())
                {
                    query += " and ";
                }
                current++;
            }

            q = session.createQuery(query);
            for(ObjectData item : vars)
            {
                q.setParameter(item.getProperty(), item.getValue());
            }
        }

        return q.list();
    }
}
