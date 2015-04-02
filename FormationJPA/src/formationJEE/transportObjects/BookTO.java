package formationJEE.transportObjects;

import java.math.BigDecimal;

public class BookTO implements ITransportObject {

	private int id;

	private BigDecimal price;

	private String title;

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
