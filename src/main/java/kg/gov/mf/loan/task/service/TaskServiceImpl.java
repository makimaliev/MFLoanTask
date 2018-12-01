package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.admin.sys.model.User;
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
    public Map<String, Object> getFields(String className) {

        Map<String, Object> fields = new HashMap<>();

        for(Field field : getAllFields(className))
        {
            fields.put(field.getName(), field.getType().getSimpleName());
        }

        return fields;
    }

    private List<Field> getAllFields(String className) {

        Class<?> type = null;
        try {
            type = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Field> fields = new ArrayList<>();

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null)
        {
            fields.addAll(getAllFields(type.getSuperclass().getName()));
        }

        return fields;
    }

    private String getClassName(String obj)
    {
        return obj.split("\\.")[obj.split("\\.").length - 1];
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

        return session
                .createQuery(query)
                .list();
    }

    @Override
    public List queryBuilder(TaskObject taskObject) {

        String query = "from " + getClassName(taskObject.getTable());
        Query q = getSession().createQuery(query);

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

                Field f = null;

                for(Field field : getAllFields(taskObject.getTable()))
                {
                    if (field.getName().equals(item.getProperty())) {
                        f = field;
                        break;
                    }
                }

                q.setParameter(item.getProperty(), parse(f, item.getValue()));
            }
        }

        return q.list();
    }

    private Object parse(Field field, String value) {

        if( Boolean.class == field.getType()) {
            return Boolean.valueOf(value);
        }
        else if( Byte.class == field.getType()) {
            return Byte.valueOf(value);
        }
        else if( Short.class == field.getType()) {
            return Short.valueOf(value);
        }
        else if( Integer.class == field.getType()) {
            return Integer.valueOf(value);
        }
        else if( Long.class == field.getType()) {
            return Long.valueOf(value);
        }
        else if( Float.class == field.getType()) {
            return Float.valueOf(value);
        }
        else if( Double.class == field.getType()) {
            return Double.valueOf(value);
        }
        else if( Boolean.TYPE == field.getType()) {
            return Boolean.parseBoolean(value);
        }
        else if( Byte.TYPE == field.getType()) {
            return Byte.parseByte(value);
        }
        else if( Short.TYPE == field.getType()) {
            return Short.parseShort(value);
        }
        else if( Integer.TYPE == field.getType()) {
            return Integer.parseInt(value);
        }
        else if( Long.TYPE == field.getType()) {
            return Long.parseLong(value);
        }
        else if( Float.TYPE == field.getType()) {
            return Float.parseFloat(value);
        }
        else if( Double.TYPE == field.getType()) {
            return Double.parseDouble(value);
        } else {
            return getSession().load(field.getType(), new Long(value));
        }
    }

    private Session getSession() {

        Session session;
        try {
            session =  sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session =  sessionFactory.openSession();
        }
        return session;
    }
}
