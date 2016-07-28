package models;

import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.*;

@Entity
public class StockItem extends Model {
	@Id
	public Long id;
	
	/* 
	 * This is the owning side.
	 * 
	 * Use it as a foreign key. Variable's name ("product" in this case) must
	 * match the corresponding name in the mapped variables' annotation.
	 * Refer to @OneToMany(mappedBy="product") in Products.java
	 */
	@ManyToOne
	public Product product;
	
	@ManyToOne
	public Warehouse warehouse;
	
	public Long quantity;
	
	public static final Find<Long, StockItem> find = new Find<Long, StockItem>(){};
	  
	public static StockItem findById(Long id){
		return find.byId(id);
	}

	public String toString() {
		return String.format("StockItem %d - %dx product %s",
				id, quantity, product == null ? null : product.Id);	
	}
}