import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Book{

  private String title;
  private int id;

  public Book(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public static List<Book> all() {
    String sql = "SELECT id, title FROM books;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  @Override
  public boolean equals(Object otherBook) {
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
            this.getId() == newBook.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books (title) VALUES (:title);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .executeUpdate()
        .getKey();
    }
  }

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books WHERE id=:id;";
      Book book = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
      return book;
    }
  }

  public void update(String title) {
    this.title = title;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :title WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM books WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM authors_books WHERE book_id = :book_id;";
        con.createQuery(joinDeleteQuery)
          .addParameter("book_id", this.getId())
          .executeUpdate();
    }
  }
}
