import java.util.ArrayList;
import org.sql2o.*;

public class Book{

  private String title;
  private int id;

  public Book(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  
}
