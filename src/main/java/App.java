import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/librarian", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/librarian.vtl");
      model.put("books", Book.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/add-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-book.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("title");
      String authorFirstName = request.queryParams("authorFirstName");
      String authorLastName = request.queryParams("authorLastName");

      Book newBook = new Book(title);
      Author newAuthor = new Author(authorFirstName, authorLastName);

      newBook.save();
      newAuthor.save();

      newBook.addAuthor(newAuthor);

      int bookId = newBook.getId();

      response.redirect("/book/" + bookId);
      return null;
    });

    get("/book/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Book book = Book.find(Integer.parseInt(request.params(":id")));

      model.put("book", book);
      model.put("template", "templates/book.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-author/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Book book = Book.find(Integer.parseInt(request.params(":id")));

      String authorFirstName = request.queryParams("authorFirstName");
      String authorLastName = request.queryParams("authorLastName");
      Author newAuthor = new Author(authorFirstName, authorLastName);

      newAuthor.save();
      book.addAuthor(newAuthor);

      int bookId = book.getId();

      response.redirect("/book/" + bookId);
      return null;
    });

    post("/edit-title/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Book book = Book.find(Integer.parseInt(request.params(":id")));
      String updatedTitle = request.queryParams("title");
      book.update(updatedTitle);

      response.redirect("/book/" + book.getId());
      return null;
    });

    get("/delete-book/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Book book = Book.find(Integer.parseInt(request.params(":id")));

      book.delete();

      model.put("books", Book.all());
      model.put("template", "templates/librarian.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
