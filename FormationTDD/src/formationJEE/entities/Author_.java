package formationJEE.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-17T21:50:52.026+0100")
@StaticMetamodel(Author.class)
public class Author_ {
	public static volatile SingularAttribute<Author, Integer> id;
	public static volatile SingularAttribute<Author, String> FName;
	public static volatile SingularAttribute<Author, String> LName;
	public static volatile ListAttribute<Author, Book> books;
}
