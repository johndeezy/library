import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;


public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Patron_instantiatesCorrectly() {
    Patron myPatron = new Patron("Tim", "Anker");
    assertTrue(myPatron instanceof Patron);
  }

  @Test
  public void getFullName_returnsFirstNameAndLastName_String() {
    Patron myPatron = new Patron("Tim", "Anker");
    assertEquals("Tim Anker", myPatron.getFullName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patron.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifTitlesAreTheSame() {
    Patron firstPatron = new Patron("Patron 1", "last");
    Patron secondPatron = new Patron("Patron 1", "last");
    assertTrue(firstPatron.equals(secondPatron));
  }

  @Test
  public void save_returnsTrueifTitlesAreTheSame() {
    Patron firstPatron = new Patron("Patron 1", "last");
    firstPatron.save();
    assertEquals(Patron.all().get(0), firstPatron);
  }

  @Test
  public void save_assignsIdToObject_1() {
    Patron firstPatron = new Patron("Patron 1", "last");
    firstPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(savedPatron.getId(), firstPatron.getId());
  }

  @Test
  public void find_findsPatronInDatabase_true() {
    Patron myPatron = new Patron("Patron1", "last");
    myPatron.save();
    Patron savedPatron = Patron.find(myPatron.getId());
    assertTrue(myPatron.equals(savedPatron));
  }

  @Test
  public void update_updatesTitle_String() {
    Patron myPatron = new Patron("Patron 1", "last");
    myPatron.save();
    myPatron.update("Patron 2", "last");
    assertEquals("Patron 2 last", Patron.find(myPatron.getId()).getFullName());
  }

  @Test
  public void delete_deletesPatronFromDatabase_true() {
    Patron myPatron = new Patron("Patron 1", "last");
    myPatron.save();
    int myPatronId = myPatron.getId();
    myPatron.delete();
    assertEquals(null, Patron.find(myPatronId));
  }

  @Test
  public void addBook_addsBookToPatron_true() {
    Book myBook = new Book("Book 1");
    myBook.save();
    Patron myPatron = new Patron("First", "Last");
    myPatron.save();
    myPatron.addBook(myBook);
    Book savedBook = myPatron.getBooks().get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_returnsAllBooks_List() {
    Patron myPatron = new Patron("tim", "thompson");
    myPatron.save();
    Book myBook = new Book("Booky");
    myBook.save();
    myPatron.addBook(myBook);
    List savedBooks = myPatron.getBooks();
    assertEquals(1, savedBooks.size());
  }

  @Test
  public void delete_deletesAllBooksAndPatronsAssociations() {
    Patron myPatron = new Patron("tim", "tom");
    myPatron.save();
    Book myBook= new Book("Book 1");
    myBook.save();
    myPatron.addBook(myBook);
    myPatron.delete();
    assertEquals(0, myBook.getPatrons().size());
  }
}
