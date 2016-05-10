import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;


public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Author_instantiatesCorrectly() {
    Author myAuthor = new Author("Tim", "Anker");
    assertTrue(myAuthor instanceof Author);
  }

  @Test
  public void getFullName_returnsFirstNameAndLastName_String() {
    Author myAuthor = new Author("Tim", "Anker");
    assertEquals("Tim Anker", myAuthor.getFullName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Author.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifTitlesAreTheSame() {
    Author firstAuthor = new Author("Author 1", "last");
    Author secondAuthor = new Author("Author 1", "last");
    assertTrue(firstAuthor.equals(secondAuthor));
  }

  @Test
  public void save_returnsTrueifTitlesAreTheSame() {
    Author firstAuthor = new Author("Author 1", "last");
    firstAuthor.save();
    assertEquals(Author.all().get(0), firstAuthor);
  }

  @Test
  public void save_assignsIdToObject_1() {
    Author firstAuthor = new Author("Author 1", "last");
    firstAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertEquals(savedAuthor.getId(), firstAuthor.getId());
  }

  @Test
  public void find_findsAuthorInDatabase_true() {
    Author myAuthor = new Author("Author1", "last");
    myAuthor.save();
    Author savedAuthor = Author.find(myAuthor.getId());
    assertTrue(myAuthor.equals(savedAuthor));
  }

  @Test
  public void update_updatesTitle_String() {
    Author myAuthor = new Author("Author 1", "last");
    myAuthor.save();
    myAuthor.update("Author 2", "last");
    assertEquals("Author 2 last", Author.find(myAuthor.getId()).getFullName());
  }

  @Test
  public void delete_deletesAuthorFromDatabase_true() {
    Author myAuthor = new Author("Author 1", "last");
    myAuthor.save();
    int myAuthorId = myAuthor.getId();
    myAuthor.delete();
    assertEquals(null, Author.find(myAuthorId));
  }

  @Test
  public void addBook_addsBookToAuthor_true() {
    Book myBook = new Book("Book 1");
    myBook.save();
    Author myAuthor = new Author("First", "Last");
    myAuthor.save();
    myAuthor.addBook(myBook);
    Book savedBook = myAuthor.getBooks().get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_returnsAllBooks_List() {
    Author myAuthor = new Author("tim", "thompson");
    myAuthor.save();
    Book myBook = new Book("Booky");
    myBook.save();
    myAuthor.addBook(myBook);
    List savedBooks = myAuthor.getBooks();
    assertEquals(1, savedBooks.size());
  }

  @Test
  public void delete_deletesAllBooksAndAuthorsAssociations() {
    Author myAuthor = new Author("tim", "tom");
    myAuthor.save();
    Book myBook= new Book("Book 1");
    myBook.save();
    myAuthor.addBook(myBook);
    myAuthor.delete();
    assertEquals(0, myBook.getAuthors().size());
  }
}
