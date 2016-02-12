import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AppTest {

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
