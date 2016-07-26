package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;







import javax.inject.Inject;

import com.google.common.io.Files;

import models.Product;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
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

	public Result details(String ean) {
		final Product product = Product.findByEan(ean);
		if (product == null) {
			return notFound(String.format("Product %s does not exist.", ean));
		}
		Form<Product> filledForm = formFactory.form(Product.class).fill(product);
		return ok(views.html.products.details.render(filledForm));
	}

	public Result save() {
		Form<Product> boundForm = formFactory.form(Product.class).bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(views.html.products.details.render(boundForm));
		}
		Product product = boundForm.get();
		
		MultipartFormData body = request().body().asMultipartFormData();
		MultipartFormData.FilePart part = body.getFile("picture");
		if(part != null){
			File pictureFile = (File) part.getFile();
			try{
				product.picture = Files.toByteArray(pictureFile);
			}catch(IOException e){
				return internalServerError("Error occurred when reading file upload");
			}
		}
		
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
	
	public Result picture(String ean){
		final Product p = Product.findByEan(ean);
		if(p == null){
			return notFound();
		}
		return ok(p.picture);
	}
}
