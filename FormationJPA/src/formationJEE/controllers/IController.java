package formationJEE.controllers;

import java.util.List;

import formationJEE.adapters.IAdapter;
import formationJEE.entities.IEntity;
import formationJEE.repositories.IRepository;
import formationJEE.services.IService;
import formationJEE.transportObjects.ITransportObject;

public interface IController<E extends IEntity, V extends ITransportObject, A extends IAdapter<E, V>, S extends IService<E,IRepository<E>>> {

	public abstract A getAdapter();
	public abstract void setAdapter(A adapter);
	public abstract V getViewModel();
	public abstract void setViewModel(V viewModel);
	public abstract S getService();
	public abstract void setService(S service);
	public abstract V save(V viewModel);
	public abstract V update(V viewModel);
	public abstract void remove(V viewModel);
	public abstract V getById(int id);
	public abstract List<V> getAll();
}
