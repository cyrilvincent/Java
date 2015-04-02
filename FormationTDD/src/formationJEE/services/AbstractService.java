package formationJEE.services;

import java.util.List;

import formationJEE.entities.IEntity;
import formationJEE.repositories.IRepository;

public abstract class AbstractService<T extends IEntity, R extends IRepository<T>> implements IService<T, R> {

	//@Autowired
	protected R repository;
	
	@Override
	public R getRepository() {
		return repository;
	}

	@Override
	public void setRepository(R repository) {
		this.repository = repository;
	}
	
	@Override
	public T save(T entity) {
        repository.save(entity);
        repository.commit();
        return entity;
    }

    @Override
	public T update(T entity) {
        repository.update(entity);
        return save(entity);
    }
 
    @Override
	public void remove(T entity) {
        repository.remove(entity);
        repository.commit();
    }
 
    @Override
	public T getById(int id) {
    	return repository.getById(id);
    }
 
	@Override
	public List<T> getAll() {
        return repository.getAll();
    }

}
