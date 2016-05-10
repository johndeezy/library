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

  @Test
  public void equals_returnsTrueifTitlesAreTheSame() {
    Book firstBook = new Book("Book 1");
    Book secondBook = new Book("Book 1");
    assertTrue(firstBook.equals(secondBook));
  }

  @Test
  public void save_returnsTrueifTitlesAreTheSame() {
    Book firstBook = new Book("Book 1");
    firstBook.save();
    assertEquals(Book.all().get(0), firstBook);
  }

  @Test
  public void save_assignsIdToObject_1() {
    Book firstBook = new Book("Book 1");
    firstBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(savedBook.getId(), firstBook.getId());
  }

  @Test
  public void find_findsBookInDatabase_true() {
    Book myBook = new Book("Book1");
    myBook.save();
    Book savedBook = Book.find(myBook.getId());
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void update_updatesTitle_String() {
    Book myBook = new Book("Book 1");
    myBook.save();
    myBook.update("Book 2");
    assertEquals("Book 2", Book.find(myBook.getId()).getTitle());
  }

  @Test
  public void delete_deletesBookFromDatabase_true() {
    Book myBook = new Book("Book 1");
    myBook.save();
    int myBookId = myBook.getId();
    myBook.delete();
    assertEquals(null, Book.find(myBookId));
  }
}
