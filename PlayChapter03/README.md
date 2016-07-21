## Play for Java - Chapter 03 ##

1. In the Controller class, all methods are static by default, therefore you should not declare your method as static explicitly.
2. Play 2.5.x got rid off helper.twitterBootstrap._ since it is changing all the time. You should include Bootstrap in your main.scala.html file. In addition, Bootstrap uses JQuery and hence you should load JQuery as well, otherwise AJAX will not be triggered when you are hitting the trash icon.
3. Play 2.5.x recommends to inject a play.data.FormFactory into your Controller class. Refer to [Play](https://www.playframework.com/documentation/2.5.x/JavaForms) or [StackOverflow](http://stackoverflow.com/questions/36099834/the-method-formclasst-from-form-class-is-deprecated-in-play-framework) for help.
4. In the list.scala.html file, you need to use 
	`<span class="glyphicon glyphicon-pencil"></span>` 
	instead of `<i class="icon icon-pencil"></i>` to see the icon. More details can be found on [W3schools](http://www.w3schools.com/bootstrap/bootstrap_ref_comp_glyphs.asp).



