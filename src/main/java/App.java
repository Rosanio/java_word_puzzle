import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    
  }

  public static String removeVowels(String userInput) {
    userInput = userInput.replace('a', '-');
    userInput = userInput.replace('e', '-');
    userInput = userInput.replace('i', '-');
    userInput = userInput.replace('o', '-');
    userInput = userInput.replace('u', '-');
    return userInput;

  }
}
