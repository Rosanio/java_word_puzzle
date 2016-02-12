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
      String puzzle = removeVowels(phrase);
      model.put("puzzle", puzzle);
      model.put("template", "templates/puzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
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
