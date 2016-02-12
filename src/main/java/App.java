import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/enter-puzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/puzzle", (request, response) -> {
      HashMap model = new HashMap();
      String phrase = request.queryParams("input-puzzle");
      if(phrase.length() == 0) {
        phrase = "you should probably enter a phrase";
      }
      String puzzle = removeVowels(phrase);
      model.put("puzzle", puzzle);
      model.put("template", "templates/puzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String removeVowels(String userInput) {
    char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
    for(char letter: vowels) {
      userInput = userInput.replace(letter, '-');
    }
    return userInput;
  }
}
