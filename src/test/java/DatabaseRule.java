import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBooksQuery = "DELETE FROM books *;";
      String deleteAuthorsQuery = "DELETE FROM authors *;";
      String deleteAuthors_booksQuery = "DELETE FROM authors_books *;";
      String deletePatronsQuery = "DELETE FROM patrons *;";
      String deleteCopiesQuery = "DELETE FROM copies *;";
      String deleteCheckoutsQuery = "DELETE FROM checkouts *;";

      con.createQuery(deleteBooksQuery).executeUpdate();
      con.createQuery(deleteAuthorsQuery).executeUpdate();
      con.createQuery(deleteAuthors_booksQuery).executeUpdate();
      con.createQuery(deletePatronsQuery).executeUpdate();
      con.createQuery(deleteCopiesQuery).executeUpdate();
      con.createQuery(deleteCheckoutsQuery).executeUpdate();
    }
  }

}
