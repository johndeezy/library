import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Book_instantiatesCorrectly() {
    Book myBook = new Book("Book 1");
    assertTrue(myBook instanceof Book);
  }

  @Test
  public void getTitle_returnsTitle_String() {
    Book myBook = new Book("Book 1");
    assertEquals("Book 1", myBook.getTitle());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }
}
