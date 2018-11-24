package kg.gov.mf.loan.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<E> implements GenericDao<E>
{
    private Class<E> entityClass;

    @Autowired
    public GenericDaoImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
    }

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void add(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public List list() {

        return entityManager.createQuery(criteria())
                .getResultList();
    }

    @Override
    public List list(int firstResult, int maxResults) {

        return entityManager.createQuery(criteria())
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public List listByParam(String param) {

        return entityManager.createQuery(criteria(param))
                .getResultList();
    }

    @Override
    public List listByParam(String param, int firstResult, int maxResults) {

        return entityManager.createQuery(criteria(param))
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public int count() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(entityClass)))
                .distinct(true);

        return entityManager.createQuery(criteria).getSingleResult().intValue();
    }

    @Override
    public E getById(long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public E update(E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void remove(E entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    private CriteriaQuery<E> criteria() {

        return criteria("id");
    }

    private CriteriaQuery<E> criteria(String param) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(entityClass);
        Root<E> root = criteria.from(entityClass);
        criteria.select(root)
                .distinct(true)
                .orderBy(builder.asc(root.get(param)));

        return criteria;
    }
}
