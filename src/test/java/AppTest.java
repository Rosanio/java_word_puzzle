import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest{
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

//Integration Testing

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Enter a Puzzle");
  }

  @Test
  public void noVowelPhrase() {
    goTo("http://localhost:4567/");
    fill("#input-puzzle").with("apples and bananas");
    submit(".btn");
    assertThat(pageSource()).contains("-ppl-s -nd b-n-n-s");
  }

//Behavior Testing

  @Test
  public void removeVowels_removeLetterA_returnStringWithNoA() {
    App testApp = new App();
    assertEquals("-nd", testApp.removeVowels("-nd"));
  }

  @Test
  public void removeVowels_replaceVowels_returnStringWithNoVowels() {
    App testApp = new App();
    assertEquals("th-s c-d- r-v--w -s --sy", testApp.removeVowels("this code review is easy"));
  }
}
