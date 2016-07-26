package models;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;

import play.data.validation.Constraints;
import play.data.validation.Constraints.ValidateWith;
import play.libs.F.Tuple;

import utils.EAN;

public class Product {
	
	@Constraints.Required
	@EAN
	public String ean;
	
	@Constraints.Required
	public String name;
	
	public String description;
	private static List<Product> products;
	public List<Tag> tags = new LinkedList<Tag>();
	
	static {
		products = new ArrayList<Product>();
		products.add(new Product("1111111111111", "Paperclips 1", "Paperclips description 1"));
		products.add(new Product("2222222222222", "Paperclips 2", "Paperclips description 2"));
		products.add(new Product("3333333333333", "Paperclips 3", "Paperclips description 3"));
		products.add(new Product("4444444444444", "Paperclips 4", "Paperclips description 4"));
		products.add(new Product("5555555555555", "Paperclips 5", "Paperclips description 5"));
	}

	public Product() {
	}

	public Product(String ean, String name, String description) {
		this.ean = ean;
		this.name = name;
		this.description = description;
	}

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
}
