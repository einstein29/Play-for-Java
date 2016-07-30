package models;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.*;


import play.data.validation.Constraints;

@Entity
public class Tag {
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@ManyToMany(mappedBy="tags")
	public List<Product> products;
	
	
	private static List<Tag> tags = new LinkedList<Tag>();
	public static final Find<Long,Tag> find = new Find<Long,Tag>(){};

	public Tag() {
		// Left empty
	}

	public Tag(Long id, String name, Collection<Product> products) {
		this.id = id;
		this.name = name;
		this.products = new LinkedList<Product>(products);
		
		for (Product product : products) {
			product.tags.add(this);
		}
	}
	
	public static Tag findById(Long id) {
		return find.byId(id);
	}
}
