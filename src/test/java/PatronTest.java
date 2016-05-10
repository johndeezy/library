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
  public void equals_returnsTrueifNamesAreTheSame() {
    Patron firstPatron = new Patron("Patron 1", "last");
    Patron secondPatron = new Patron("Patron 1", "last");
    assertTrue(firstPatron.equals(secondPatron));
  }

  @Test
  public void save_returnsTrueifNamesAreTheSame() {
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
  public void update_updatesName_String() {
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

  // @Test
  // public void addCopy_addsCopyToPatron_true() {
  //   Copy myCopy = new Copy("Copy 1");
  //   myCopy.save();
  //   Patron myPatron = new Patron("First", "Last");
  //   myPatron.save();
  //   myPatron.addCopy(myCopy);
  //   Copy savedCopy = myPatron.getCopies().get(0);
  //   assertTrue(myCopy.equals(savedCopy));
  // }
  //
  // @Test
  // public void getCopys_returnsAllCopies_List() {
  //   Patron myPatron = new Patron("tim", "thompson");
  //   myPatron.save();
  //   Copy myCopy = new Copy("Copyy");
  //   myCopy.save();
  //   myPatron.addCopy(myCopy);
  //   List savedCopies = myPatron.getCopies();
  //   assertEquals(1, savedCopys.size());
  // }
  //
  // @Test
  // public void delete_deletesAllCopiesAndPatronsAssociations() {
  //   Patron myPatron = new Patron("tim", "tom");
  //   myPatron.save();
  //   Copy myCopy= new Copy("Copy 1");
  //   myCopy.save();
  //   myPatron.addCopy(myCopy);
  //   myPatron.delete();
  //   assertEquals(0, myCopy.getPatrons().size());
  // }
}
