package formationJEE.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
@Table(name="book", schema="formation")
public class Book implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal price;

	@Column(name="title")
	private String title;

	//bi-directional many-to-one association to Publisher
	@ManyToOne
	@JoinColumn(name="PublisherId")
	private Publisher publisher;

	//bi-directional many-to-many association to Author
	@ManyToMany
	@JoinTable(
		name="bookauthor"
		, joinColumns={
			@JoinColumn(name="BookId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AuthorId")
			}
		)
	private List<Author> authors;

	public Book() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Publisher getPublisher() {
		return this.publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


}