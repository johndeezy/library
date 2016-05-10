import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Copy{

  private int book_id;
  private boolean checked;
  private String due_date;
  private int id;

  public Copy(int book_id, boolean checked, String due_date) {
    this.book_id = book_id;
    this.checked = checked;
    this.due_date = due_date;
  }

  public int getBookId() {
    return book_id;
  }

  public boolean getChecked() {
    return checked;
  }

  public String getDueDate() {
    return due_date;
  }

  public int getId() {
    return id;
  }

  public static List<Copy> all() {
    String sql = "SELECT * FROM copies;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Copy.class);
    }
  }

  @Override
  public boolean equals(Object otherCopy) {
    if (!(otherCopy instanceof Copy)) {
      return false;
    } else {
      Copy newCopy = (Copy) otherCopy;
      return this.getBookId() == newCopy.getBookId() &&
            this.getId() == newCopy.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO copies (book_id, checked, due_date) VALUES (:book_id, :checked, :due_date);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("book_id", this.book_id)
        .addParameter("checked", this.checked)
        .addParameter("due_date", this.due_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static Copy find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM copies WHERE id=:id;";
      Copy copy = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Copy.class);
      return copy;
    }
  }

  public void update(int book_id, boolean checked, String due_date) {
    this.book_id = book_id;
    this.checked = checked;
    this.due_date = due_date;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE copies SET book_id = :book_id, checked = :checked, due_date = :due_date WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("book_id", book_id)
        .addParameter("checked", checked)
        .addParameter("due_date", due_date)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM copies WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM checkouts WHERE copy_id = :copy_id;";
        con.createQuery(joinDeleteQuery)
          .addParameter("copy_id", this.getId())
          .executeUpdate();
    }
  }

  public void addPatron(Patron patron) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO checkouts (copy_id, patron_id) VALUES (:copy_id, :patron_id);";
      con.createQuery(sql)
        .addParameter("copy_id", this.id)
        .addParameter("patron_id", patron.getId())
        .executeUpdate();
    }
  }

  public List<Patron> getPatrons() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT patron_id FROM checkouts WHERE copy_id = :copy_id;";
      List<Integer> patronIds = con.createQuery(joinQuery)
        .addParameter("copy_id", this.id)
        .executeAndFetch(Integer.class);

      List<Patron> patrons = new ArrayList<Patron>();

      for (Integer patronId : patronIds) {
        String patronQuery = "SELECT * FROM patrons WHERE id = :patronId;";
        Patron patron = con.createQuery(patronQuery)
          .addParameter("patronId", patronId)
          .executeAndFetchFirst(Patron.class);
        patrons.add(patron);
      }
      return patrons;
    }
  }
}
