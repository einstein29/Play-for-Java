package controllers;

import java.util.ArrayList;
import java.util.List;





import javax.inject.Inject;

import models.Product;
import models.Tag;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class Products extends Controller{
	
	@Inject FormFactory formFactory;
	
	public Result list() {
		List<Product> prods = Product.findAll();
		return ok(views.html.products.list.render(prods));
	}

	public Result newProduct() {
		Form<Product> productForm = formFactory.form(Product.class);
		return ok(views.html.products.details.render(productForm));
	}

	/*
	 *  Filled an empty form with the detail of a Product when the product's
	 *  EAN number is specified. 
	 */
	public Result details(String ean) {
		final Product product = Product.findByEan(ean);
		if (product == null) {
			return notFound(String.format("Product %s does not exist.", ean));
		}
		Form<Product> filledForm = formFactory.form(Product.class).fill(product);
		return ok(views.html.products.details.render(filledForm));
	}

	/*
	 * When user finished filling the form and send it to the server, we check if
	 * everything required is fulfilled. First we bind from request then check for
	 * errors.
	 */
	public Result save() {
		Form<Product> boundForm = formFactory.form(Product.class).bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(views.html.products.details.render(boundForm));
		}
		/* Creating a Product object whose attributes are specified by the user.
		 * Since the Tag has more than one attribute but only the ID is specified
		 * before it was passed here, so the for-loop is for getting other other attributes.
		 * Instead of manually doing so, we just create a new Tag object and assign it to
		 * the Product object.
		 */
		Product product = boundForm.get();
		List<Tag> tags = new ArrayList<Tag>();
		for (Tag tag : product.tags) {
			if (tag.id != null) {
				tags.add(Tag.findById(tag.id));
			}
		}
		product.tags = tags;
		product.save();
		flash("success", String.format("Successfully added product %s", product));
		return redirect(routes.Products.list());
	}
	
	public Result delete(String ean) {
		final Product product = Product.findByEan(ean);
		if(product == null) {
			return notFound(String.format("Product %s does not exists.", ean));
		}
		Product.remove(product);
		return redirect(routes.Products.list());
	}
}
