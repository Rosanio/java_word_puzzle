import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AppTest {

  @Test
  public void removeVowels_removeLetterA_returnStringWithNoA() {
    App testApp = new App();
    assertEquals("-pple", testApp.removeVowels("apple"));
  }
}
