package formationJEE.services;

import java.util.List;

import formationJEE.entities.IEntity;
import formationJEE.repositories.IRepository;


public interface IService<T extends IEntity, R extends IRepository<T>> {

	/**
	 * @return the repository
	 */
	public abstract R getRepository();

	/**
	 * @param repository the repository to set
	 */
	public abstract void setRepository(R repository);

	public abstract T save(T entity);

	public abstract T update(T entity);

	public abstract void remove(T entity);

	public abstract T getById(int id);

	public abstract List<T> getAll();

}
