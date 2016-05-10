import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Author{

  private String first_name;
  private String last_name;
  private int id;

  public Author(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public String getFullName() {
    return first_name + " " + last_name;
  }

  public int getId() {
    return id;
  }

  public static List<Author> all() {
    String sql = "SELECT * FROM authors;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  @Override
  public boolean equals(Object otherAuthor) {
    if (!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) otherAuthor;
      return this.getFirstName().equals(newAuthor.getFirstName()) &&
            this.getLastName().equals(newAuthor.getLastName()) &&
            this.getId() == newAuthor.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors (first_name, last_name) VALUES (:first_name, :last_name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", this.first_name)
        .addParameter("last_name", this.last_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Author find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE id=:id;";
      Author author = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Author.class);
      return author;
    }
  }

  public void update(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET first_name = :first_name, last_name = :last_name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM authors WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM authors_books WHERE author_id = :author_id;";
        con.createQuery(joinDeleteQuery)
          .addParameter("author_id", this.getId())
          .executeUpdate();
    }
  }

  public void addBook(Book book) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors_books (author_id, book_id) VALUES (:author_id, :book_id);";
      con.createQuery(sql)
        .addParameter("author_id", this.id)
        .addParameter("book_id", book.getId())
        .executeUpdate();
    }
  }

  public List<Book> getBooks() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT book_id FROM authors_books WHERE author_id = :author_id;";
      List<Integer> bookIds = con.createQuery(joinQuery)
        .addParameter("author_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Book> books = new ArrayList<Book>();

      for (Integer bookId : bookIds) {
        String bookQuery = "SELECT * FROM books WHERE id = :bookId;";
        Book book = con.createQuery(bookQuery)
          .addParameter("bookId", bookId)
          .executeAndFetchFirst(Book.class);
        books.add(book);
      }
      return books;
    }
  }
}
