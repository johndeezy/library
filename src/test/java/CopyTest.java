import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;


public class CopyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Copy_instantiatesCorrectly() {
    Copy myCopy = new Copy(1, false, "1/15/2017");
    assertTrue(myCopy instanceof Copy);
  }

  @Test
  public void getBookId_returnsBookId_int() {
    Copy myCopy = new Copy(1, false, "1/15/2017");
    assertEquals(1, myCopy.getBookId());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Copy.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifBookIdsAreTheSame() {
    Copy firstCopy = new Copy(1, false, "1/15/2017");
    Copy secondCopy = new Copy(1, false, "1/15/2017");
    assertTrue(firstCopy.equals(secondCopy));
  }

  @Test
  public void save_returnsTrueifBookIdsAreTheSame() {
    Copy firstCopy = new Copy(1, false, "1/15/2017");
    firstCopy.save();
    assertEquals(Copy.all().get(0), firstCopy);
  }

  @Test
  public void save_assignsIdToObject_1() {
    Copy firstCopy = new Copy(1, false, "1/15/2017");
    firstCopy.save();
    Copy savedCopy = Copy.all().get(0);
    assertEquals(savedCopy.getId(), firstCopy.getId());
  }

  @Test
  public void find_findsCopyInDatabase_true() {
    Copy myCopy = new Copy(1, false, "1/15/2017");
    myCopy.save();
    Copy savedCopy = Copy.find(myCopy.getId());
    assertTrue(myCopy.equals(savedCopy));
  }

  @Test
  public void update_updatesBookId_String() {
    Copy myCopy = new Copy(2, false, "1/15/2017");
    myCopy.save();
    myCopy.update(1, false, "1/15/2017");
    assertEquals(1, Copy.find(myCopy.getId()).getBookId());
  }

  @Test
  public void delete_deletesCopyFromDatabase_true() {
    Copy myCopy = new Copy(1, false, "1/15/2017");
    myCopy.save();
    int myCopyId = myCopy.getId();
    myCopy.delete();
    assertEquals(null, Copy.find(myCopyId));
  }

  @Test
  public void addPatron_addsPatronToCopy_true() {
    Copy myCopy = new Copy(1, false, "1/15/2017");
    myCopy.save();
    Patron patron = new Patron("First", "Last");
    patron.save();
    myCopy.addPatron(patron);
    Patron savedPatron = myCopy.getPatrons().get(0);
    assertTrue(patron.equals(savedPatron));
  }

  @Test
  public void getPatrons_returnsAllPatrons_List() {
    Patron myPatron = new Patron("tim", "thompson");
    myPatron.save();
    Copy myCopy = new Copy(1, false, "1/15/2017");
    myCopy.save();
    myCopy.addPatron(myPatron);
    List savedPatrons = myCopy.getPatrons();
    assertEquals(1, savedPatrons.size());
  }

  @Test
  public void delete_deletesAllCopiesAndPatronsAssociations() {
    Patron myPatron = new Patron("tim", "tom");
    myPatron.save();
    Copy myCopy= new Copy(1, false, "1/15/2017");
    myCopy.save();
    myCopy.addPatron(myPatron);
    myCopy.delete();
    assertEquals(0, myPatron.getCopies().size());
  }
}
