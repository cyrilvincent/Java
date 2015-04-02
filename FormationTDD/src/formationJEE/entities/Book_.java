package formationJEE.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-17T21:50:47.162+0100")
@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Integer> id;
	public static volatile SingularAttribute<Book, BigDecimal> price;
	public static volatile SingularAttribute<Book, String> title;
	public static volatile SingularAttribute<Book, Publisher> publisher;
	public static volatile ListAttribute<Book, Author> authors;
}
