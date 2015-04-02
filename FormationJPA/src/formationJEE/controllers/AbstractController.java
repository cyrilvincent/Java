package formationJEE.controllers;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import formationJEE.adapters.IAdapter;
import formationJEE.entities.IEntity;
import formationJEE.repositories.IRepository;
import formationJEE.services.IService;
import formationJEE.transportObjects.ITransportObject;

public abstract class AbstractController<E extends IEntity, V extends ITransportObject, A extends IAdapter<E, V>, S extends IService<E,IRepository<E>>> implements IController<E,V,A,S> {

	@SuppressWarnings("unchecked")
	protected <T> Class<T> getClass(int number) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) genericSuperclass.getActualTypeArguments()[number];
	}
	
	protected A adapter;
	@Override
	public A getAdapter() {
		return adapter;
	}
	@Override
	public void setAdapter(A adapter) {
		this.adapter = adapter;	
	}

	protected V viewModel;
	@Override
	public V getViewModel() {
		return viewModel;
	}
	@Override
	public void setViewModel(V viewModel) {
		this.viewModel = viewModel;
	}

	protected S service;
	@Override
	public S getService() {
		return service;
	}
	@Override
	public void setService(S service) {
		this.service = service;
	}
	
	@Override
	public V save(V viewModel) {
		E entity = service.save(adapter.toEntity(viewModel));
		return adapter.toOuput(entity);
	}
	@Override
	public V update(V viewModel) {
		E entity = service.getById(viewModel.getId());
		entity = service.update(adapter.toEntity(viewModel, entity));
		return adapter.toOuput(entity);
	}
	@Override
	public void remove(V viewModel) {
		service.remove(adapter.toEntity(viewModel));
		
	}
	@Override
	public V getById(int id) {
		E entity = service.getById(id);
		return adapter.toOuput(entity);
	}
	@Override
	public List<V> getAll() {
		List<E> l = service.getAll();
		return adapter.toOutputs(l);
	}
	
	
	
}
