import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class AppTest extends FluentTest{

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Portland Library");
  }

  @Test
  public void librarianPageTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Librarian Options"));
    assertThat(pageSource()).contains("Book List:");
  }

  @Test
  public void addBookPageTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Librarian Options"));
    click("a", withText("Add a Book"));
    assertThat(pageSource()).contains("Author's First Name");
  }

  @Test
  public void addBookFormTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Librarian Options"));
    click("a", withText("Add a Book"));
    fill("#title").with("Book 1");
    fill("#authorFirstName").with("Tim");
    fill("#authorLastName").with("Tom");
    submit(".btn");
    assertThat(pageSource()).contains("Book 1");
  }
}
