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
      con.createQuery(deleteBooksQuery).executeUpdate();
      con.createQuery(deleteAuthorsQuery).executeUpdate();
    }
  }

}
