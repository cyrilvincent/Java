package formationJEE.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-17T21:50:47.165+0100")
@StaticMetamodel(Publisher.class)
public class Publisher_ {
	public static volatile SingularAttribute<Publisher, Integer> id;
	public static volatile SingularAttribute<Publisher, String> name;
	public static volatile ListAttribute<Publisher, Book> books;
}
