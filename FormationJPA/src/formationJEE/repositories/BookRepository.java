package formationJEE.repositories;

import java.math.BigDecimal;
import java.util.List;

import formationJEE.entities.Book;

//@Repository("BookRepository")
public class BookRepository extends AbstractRepository<Book> {

	@SuppressWarnings("unchecked")
	public List<Book> getByPrice(BigDecimal price) {
        return getEntityManager().createQuery("select b from Book b where b.price<"+price).getResultList();
    }
	

}
