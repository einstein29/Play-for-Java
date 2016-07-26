package models;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import play.data.validation.Constraints;

public class Tag {
	
	public Long id;
	
	@Constraints.Required
	public String name;
	
	public List<Product> products;
	
	/* 
	 * Creating some fake Tags. All products have 2 tags, and one product has 3 tags.
	 */
	private static List<Tag> tags = new LinkedList<Tag>();
	
	static {
		tags.add(new Tag(1L, "lightweight", Product.findByName("paperclips 1")));
		tags.add(new Tag(2L, "metal", Product.findByName("paperclips")));
		tags.add(new Tag(3L, "plastic", Product.findByName("paperclips")));
	}

	public Tag() {
		// Left empty
	}

	public Tag(Long id, String name, Collection<Product> products) {
		this.id = id;
		this.name = name;
		this.products = new LinkedList<Product>(products);
		
		/* For each product in the product list, we add this tag to it */
		for (Product product : products) {
			product.tags.add(this);
		}
	}
	
	/*
	 *  In chapter 06, we have created some fake tags, so when this method is called,
	 *  it means that we already have a bunch of tags, and we need to find the one with
	 *  the specified ID.
	 */
	public static Tag findById(Long id) {
		for (Tag tag : tags) {
			if (tag.id == id)
				return tag;
		}
		return null;
	}
}
