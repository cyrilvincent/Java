package formationJEE.viewModels;

import java.math.BigDecimal;

import formationJEE.transportObjects.ITransportObject;

public class BookViewModel implements IViewModel, ITransportObject {

	private int id;

	private BigDecimal price;

	private String title;

	@Override
	public int getId() {
		return id;
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
	
	public BigDecimal getPriceVATIncluded() {
		return price.multiply(BigDecimal.valueOf(1.05));
	}

}
