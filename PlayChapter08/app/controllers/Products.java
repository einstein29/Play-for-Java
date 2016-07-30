package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import javax.inject.Inject;

import com.google.common.io.Files;

import models.Product;
import models.StockItem;
import models.Tag;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;

import com.avaje.ebean.PagedList;		

public class Products extends Controller{
	
	@Inject FormFactory formFactory;
	
	public Result list(Integer page){
		PagedList<Product> prods = Product.findByPage(page);
		return ok(views.html.catalog.render(prods));
	}

	public Result newProduct() {
		Form<Product> productForm = formFactory.form(Product.class);
		return ok(views.html.products.details.render(productForm));
	}

	public Result details(Product prdct) {
		if (prdct == null) {
			return notFound(String.format("Product %s does not exist.", prdct.ean));
		}
		Form<Product> filledForm = formFactory.form(Product.class).fill(prdct);
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
		MultipartFormData.FilePart part = body.getFile("picture");//System.out.println("======> "+part.getClass());
		if(part != null){
			File pictureFile =  (File) part.getFile();
			try{
				product.picture = Files.toByteArray(pictureFile);
			}catch(IOException e){
				return internalServerError("Error occurred when reading file upload");
			}
		}

		List<Tag> tags = new ArrayList<Tag>();
		for (Tag tag : product.tags) {
			if (tag.id != null) {
				tags.add(Tag.findById(tag.id));
			}
		}
		product.tags = tags;
		
		if(product.Id == null){
			product.save();
		}else{
			product.update();
		}
		
		flash("success", String.format("Successfully added product %s", product));
		return redirect(routes.Products.list(0)); 
	}
	
	public Result delete(String ean) {
		Product product = Product.findByEan(ean);
		if(product == null) {
			return notFound(String.format("Product %s does not exists.", ean));
		}
		/*
		 * This delete() method is actually from com.avaje.ebean.Model,
		 * of cause you can use Model's inner class Find(){...} to do the same job.
		 * E.g. product.find.deleteById(ID); It is noted that the input parameter is
		 * ID number not EAN! It is much easier if we find the object, and use Model's delete()
		 * to delete this object from DB. 
		 */
		product.delete();
		return redirect(routes.Products.list(0));
	}

	public Result picture(String ean){
		final Product p = Product.findByEan(ean);
		if(p == null){
			return notFound();
		}
		return ok(p.picture);
	}
}
