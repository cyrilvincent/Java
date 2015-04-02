package formationJEE.adapters;

import java.util.List;

import formationJEE.entities.IEntity;
import formationJEE.transportObjects.ITransportObject;

public interface IAdapter<E extends IEntity, T extends ITransportObject> {

	public abstract T toOuput(E entity);
	public abstract E toEntity(T output, E entity);
	public abstract E toEntity(T output);
	public abstract List<T> toOutputs(List<E> entities);
}
