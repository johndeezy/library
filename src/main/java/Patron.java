import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Patron{

  private String first_name;
  private String last_name;
  private int id;

  public Patron(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public String getFullName() {
    return first_name + " " + last_name;
  }

  public int getId() {
    return id;
  }

  public static List<Patron> all() {
    String sql = "SELECT * FROM patrons;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  @Override
  public boolean equals(Object otherPatron) {
    if (!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getFirstName().equals(newPatron.getFirstName()) &&
            this.getLastName().equals(newPatron.getLastName()) &&
            this.getId() == newPatron.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons (first_name, last_name) VALUES (:first_name, :last_name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", this.first_name)
        .addParameter("last_name", this.last_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patron find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons WHERE id=:id;";
      Patron patron = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patron.class);
      return patron;
    }
  }

  public void update(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patrons SET first_name = :first_name, last_name = :last_name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM patrons WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM checkouts WHERE patron_id = :patron_id;";
        con.createQuery(joinDeleteQuery)
          .addParameter("patron_id", this.getId())
          .executeUpdate();
    }
  }

  public void addCopy(Copy copy) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO checkouts (patron_id, copy_id) VALUES (:patron_id, :copy_id);";
      con.createQuery(sql)
        .addParameter("patron_id", this.id)
        .addParameter("copy_id", copy.getId())
        .executeUpdate();
    }
  }

  public List<Copy> getCopies() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT copy_id FROM checkouts WHERE patron_id = :patron_id;";
      List<Integer> copyIds = con.createQuery(joinQuery)
        .addParameter("patron_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Copy> copies = new ArrayList<Copy>();

      for (Integer copyId : copyIds) {
        String copyQuery = "SELECT * FROM copies WHERE id = :copyId;";
        Copy copy = con.createQuery(copyQuery)
          .addParameter("copyId", copyId)
          .executeAndFetchFirst(Copy.class);
        copies.add(copy);
      }
      return copies;
    }
  }
}
