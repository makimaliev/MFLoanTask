package kg.gov.mf.loan.task.dao;

import java.util.List;

public interface GenericDao<E> {

    void add(E entity);
    List<E> list();
    List<E> list(int firstResult, int maxResults);
    List<E> listByParam(String param);
    List<E> listByParam(String param, int firstResult, int maxResults);
    int count();
    E getById(long id);
    E update(E entity);
    void remove(E entity);
}