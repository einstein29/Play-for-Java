package models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.persistence.*;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints;
import play.data.validation.Constraints.ValidateWith;
import play.libs.F.Tuple;
import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;
import utils.EAN;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.*;
import com.avaje.ebean.PagedList;

@Entity
public class Product extends Model implements PathBindable<Product> {

	@Id
	public Long Id;

	private static final String dateFormat = "yyyy-MM-dd";
	private static final Integer pageSize = 10;

	@Constraints.Required
	public String name;

	@Constraints.Required
	@EAN(message = "Customized message: must be 13 digits.")
	public String ean;

	@DateTime(pattern = dateFormat)
	public Date date;

	public String description;
	private static List<Product> products;

	/*
	 * Without specifying cascade, it will prevent you from deleting a row,
	 * since it is also the foreign-key in other tables.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Tag> tags = new LinkedList<Tag>();

	public byte[] picture;

	@OneToMany(mappedBy = "product")
	public List<StockItem> stockItem;

	public static final Find<Long, Product> find = new Find<Long, Product>() {
	};

	public Product() {
	}

	public Product(String ean, String name, String description, Date date) {
		this.ean = ean;
		this.name = name;
		this.description = description;
		this.date = date;
	}

	// @Override
	public String toString() {
		return String.format("%s - %s", ean, name);
	}

	public static List<Product> findAll() {
		return find.findList();
	}

	public static Product findByEan(String ean) {
		return find.where().eq("ean", ean).findUnique();
	}

	public static PagedList<Product> findByPage(int pageIndex) {
		return find.where().orderBy("id desc")
				.findPagedList(pageIndex, pageSize);
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

	@Override
	public Product bind(String key, String txt) {
		return findByEan(txt);
	}

	@Override
	public String unbind(String key) {
		return this.ean;
	}

	@Override
	public String javascriptUnbind() {
		return this.ean;
	}

}
