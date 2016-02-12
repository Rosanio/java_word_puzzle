import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {}

  public static String removeVowels(String userInput) {
    // char[] letters = userInput.toCharArray();
    // for(char letter: letters) {
    //   if(letter == 'a') {
    //
    //   }
    // }
    userInput = userInput.replace('a', '-');
    return userInput;

  }
}
