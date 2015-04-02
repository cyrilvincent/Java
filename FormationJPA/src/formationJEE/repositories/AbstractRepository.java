package formationJEE.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import javax.annotation.Resource;
import javax.persistence.*;

import formationJEE.entities.IEntity;

public abstract class AbstractRepository<T extends IEntity> 
				implements IRepository<T> {
	
	@Resource(name="EntityManager")
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	private EntityTransaction transaction;

	@Override
	public EntityTransaction getTransaction() {
		if(transaction == null) {
			transaction = entityManager.getTransaction();
		}
		if(!transaction.isActive()) {
			transaction.begin();
		}
		return transaction;
	}
	
	@Override
	public void setTransaction(EntityTransaction transaction) {
		this.transaction = transaction;
	}

	private Class<T> entityClass;

    @SuppressWarnings("unchecked")
	public AbstractRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
 
    @Override
	public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
	public T update(T entity) {
        return entityManager.merge(entity);
    }
 
    @Override
	public void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }
 
    @Override
	public T getById(int id) {
    	return entityManager.find(entityClass, id);
    }
 
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
        //return entityManager.createQuery("select e from "+ entityClass.getSimpleName()  +" e").getResultList();
		return entityManager.createNamedQuery(entityClass.getSimpleName() + ".findAll").getResultList();
    }
	
	@Override
	public void commit() {
		getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> GetByQuery(Query q) {
		return q.getResultList();
	}
 
}
