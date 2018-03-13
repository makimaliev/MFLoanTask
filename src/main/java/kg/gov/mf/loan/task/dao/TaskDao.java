package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.manage.dao.GenericDao;
import kg.gov.mf.loan.task.model.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {
    public List<Task> getTasksByUserId(long id);
}
