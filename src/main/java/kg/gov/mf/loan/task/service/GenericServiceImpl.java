package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericServiceImpl<E> implements GenericService<E>
{
    @Autowired
    protected GenericDao<E> dao;

    @Override
    public void add(E entity) {
        dao.add(entity);
    }

    @Override
    public E update(E entity) {
        return dao.update(entity);
    }

    @Override
    public void remove(E entity) {
        dao.remove(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public E getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> list() {
        return dao.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> list(int firstResult, int maxResults) {
        return dao.list(firstResult, maxResults);
    }

    @Override
    public List<E> listByParam(String param) {
        return dao.listByParam(param);
    }

    @Override
    public List<E> listByParam(String param, int firstResult, int maxResults) {
        return dao.listByParam(param, firstResult, maxResults);
    }

    @Override
    public int count() {
        return dao.count();
    }
}