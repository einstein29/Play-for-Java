package controllers;

import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    /* Page 17, figure 1.6 */
//    public Result hello(String name){
//    	return ok("Hello " + name);
//    }
    
    /* Page 18, figure 1.7 */
    public Result hello(String name){
    	return ok(views.html.hello.render(name));
    }

}
