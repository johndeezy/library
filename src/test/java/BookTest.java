import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

 @Test
 public void Book_instatiatesCorrectly() {
   Book myBook = new Book("Book 1");
   assertTrue(myBook instanceof Book);
 }
}
