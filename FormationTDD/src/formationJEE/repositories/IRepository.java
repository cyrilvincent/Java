package formationJEE.repositories;

import java.util.List;

import formationJEE.entities.IEntity;

public interface IRepository<T extends IEntity> extends IUoW {

	public abstract T save(T entity);

	public abstract T update(T entity);

	public abstract void remove(T entity);

	public abstract T getById(int id);

	public abstract List<T> getAll();

}
