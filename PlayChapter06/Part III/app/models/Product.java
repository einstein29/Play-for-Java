package models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints;
import play.data.validation.Constraints.ValidateWith;
import play.mvc.QueryStringBindable;

public class Product implements QueryStringBindable<Product>{
	
	private static final String dateFormat = "yyyy-MM-dd";
	
	@Constraints.Required 
	public String name;
	
	@Constraints.Required 
	public String ean;
	
	@DateTime(pattern = dateFormat)
	public Date date;

	public String description;
	private static List<Product> products;
	public List<Tag> tags = new LinkedList<Tag>();
	
	static {
		products = new ArrayList<Product>();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date d = new Date();
		try {
			d = sdf.parse("1970-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		products.add(new Product("1111111111111", "Paperclips 1", "Paperclips description 1", d));
		products.add(new Product("2222222222222", "Paperclips 2", "Paperclips description 2", d));
		products.add(new Product("3333333333333", "Paperclips 3", "Paperclips description 3", d));
		products.add(new Product("4444444444444", "Paperclips 4", "Paperclips description 4", d));
		products.add(new Product("5555555555555", "Paperclips 5", "Paperclips description 5", d));
	}

	public Product() {
	}

	public Product(String ean, String name, String description, Date date) {
		this.ean = ean;
		this.name = name;
		this.description = description;
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", ean, name);
	}
	
	public static List<Product> findAll() {
		return new ArrayList<Product>(products);
	}
	
	public static Product findByEan(String ean) {
		for (Product candidate : products) {
			if (candidate.ean.equals(ean)) {
				return candidate;
			}
		}
		return null;
	}
	
	public static List<Product> findByName(String term) {
		final List<Product> results = new ArrayList<Product>();
		for (Product candidate : products) {
			if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
				results.add(candidate);
			}
		}
		return results;
	}
	
	public static boolean remove(Product product) {
		return products.remove(product);
	}
	
	public void save() {
		products.remove(findByEan(this.ean));
		products.add(this);
	}
	
	/*
	 * Page-118, QueryStringBindable.
	 * key = "ean" in the URL, and the actual EAN number is stored in "data".
	 * @see play.mvc.QueryStringBindable#bind(java.lang.String, java.util.Map)
	 */
	@Override
	public Optional<Product> bind(String key, Map<String, String[]> data) {
		String[] ids = data.get(key); 
		String ean = ids[0];
		return Optional.of(findByEan(ean));
	}

	@Override
	public String unbind(String key) {
		return "ean="+this.ean;
	}

	@Override
	public String javascriptUnbind() {
		return this.ean;
	}

}
