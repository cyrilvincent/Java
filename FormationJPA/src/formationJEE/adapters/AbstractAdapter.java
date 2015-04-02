package formationJEE.adapters;

import java.lang.reflect.*;
import java.util.*;

import formationJEE.entities.IEntity;
import formationJEE.ioc.Spring;
import formationJEE.transportObjects.ITransportObject;

public abstract class AbstractAdapter<E extends IEntity, V extends ITransportObject> implements IAdapter<E, V> {

	public static void adapter(Object source, Object destination) {
		Field[] fields = source.getClass().getDeclaredFields();
		for(Field f : fields) {
			Field field;
			try {
				field = destination.getClass().getDeclaredField(f.getName());
				f.setAccessible(true);
				Object value = f.get(source);
				field.setAccessible(true);
				field.set(destination, value);
			} 
			catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			}		
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Class<T> getClass(int number) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) genericSuperclass.getActualTypeArguments()[number];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V toOuput(E entity) {
		V vm = (V) Spring.getBean(getClass(1));
		adapter(entity,vm);
		return vm;
	}

	@Override
	public E toEntity(V viewModel, E entity) {
		adapter(viewModel,entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E toEntity(V viewModel) {
		E entity = (E)Spring.getBean(getClass(0));
		adapter(viewModel,entity);
		return entity;
	}

	@Override
	public List<V> toOutputs(List<E> entities) {
		List<V> l = new ArrayList<V>();
		for(E entity : entities) {
			l.add(toOuput(entity));
		}
		return l;
	}

}
