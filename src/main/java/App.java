import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static String phrase = ""; //Initialize global phrase variable
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
      App.phrase = request.queryParams("input-puzzle"); //Update global phrase variable. Now the phrase can be used in any other lambda
      if(App.phrase.length() == 0) {
        App.phrase = "you should probably enter a phrase";
      }
      String puzzle = removeVowels(App.phrase);
      model.put("puzzle", puzzle);
      model.put("template", "templates/puzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/guess", (request, response) -> {
      HashMap model = new HashMap();
      String guess = request.queryParams("guess");
      String correctGuess = "";
      if(guess.length() == 0) {
        correctGuess += "Not a very good guesser, are you?";
      } else if(guess.equals(App.phrase)) {
        correctGuess += "You got it right! Good for you!";
      } else {
        correctGuess += "No, that's wrong! You did bad and you should feel bad!";
      }
      model.put("guess", guess);
      model.put("userInput", App.phrase);
      model.put("correctGuess", correctGuess);
      model.put("template", "templates/guess.vtl");
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

  //giveHint function was meant to be used to display puzzle with one vowel revealed, didn't have time to figure out user logic

  public static String giveHint(String puzzle, String originalPhrase) {
    char[] puzzleCharArray = puzzle.toCharArray();
    char[] phraseCharArray = originalPhrase.toCharArray();
    ArrayList<Character> puzzleArrayList = new ArrayList<Character>();
    for(char letter: puzzleCharArray) {
      puzzleArrayList.add(letter);
    }
    ArrayList<Character> phraseArrayList = new ArrayList<Character>();
    for(char letter: phraseCharArray) {
      phraseArrayList.add(letter);
    }
    Integer index = 0;
    for(Character letter: puzzleArrayList) {
      if(letter == '-') {
        index = puzzleArrayList.indexOf(letter);
        break;
      }
    }
    Character replaceVowel = phraseArrayList.get(index);
    puzzleCharArray[index] = replaceVowel;
    String puzzleHint = new String(puzzleCharArray);
    return puzzleHint;
  }
}
