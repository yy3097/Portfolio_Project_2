// Yao Yi
// 11/12/2015
// CSE 143 AC 
// TA: LATHE,CHLOE M.  
// 
// This class will manage an evil hangman game. The computer 
// will delays picking a word until it is forced to. 

import java.util.*;

public class HangmanManager {
   private Set<String> words; 
   private SortedSet<Character> guesses; 
   private int times; // available number of wrong guesses left
   private String pattern; 
   
   // pre: passed in a list of strings of a dictionary of words, an integer 
   //      of a target word length, and an integer of maximum number of wrong 
   //      guesses the player is allowed to make. 
   //      if the length is less than 1 or the maximum number is less than 0, 
   //      throw IllegalArgumentException. 
   // post: initialize a new HangmanManager over the given dictionary, length 
   //       and max. Make a storage of words with given length and character been 
   //       guessed in sorted order. Set up the guess pattern to default format: - - -(...). 
   public HangmanManager(List<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException(); 
      }
      words = new TreeSet<String>(); 
      guesses = new TreeSet<Character>();
      times = max; 
      for (int i = 0; i < dictionary.size(); i++) {
         String word = dictionary.get(i); 
         if (word.length() == length) {
            words.add(word); 
         }
      }
      pattern = "-";
      for (int i = 1; i < length; i++) {
         pattern += " -"; 
      }  
   }
   
   // post: return the current string set of words being considered by the HangmanManager. 
   public Set<String> words() {
      return words; 
   }
   
   // post: return the available number of guesses the player has left. 
   public int guessesLeft() {
      return times; 
   }
   
   // post: return the current character sorted set of letters has been guessed by the user. 
   public SortedSet<Character> guesses() {
      return guesses; 
   }
   
   // pre: if the set of words being considered by the HangmanManager is empty, throw 
   //      IllegalStateException. 
   // post: return a string represents the current pattern to be displayed by the 
   //       hangman game. Letters have not yet been guessed will be displayed as a dash and 
   //       else be themselves. There will be space separating letters. 
   public String pattern() {
      if (words.isEmpty()) {
         throw new IllegalStateException(); 
      }
      return pattern;     
   }
   
   // pre: pass in a character of the letter be guessed by the player this time. 
   //      if the number of guesses left is not at least 1 or the words being considered 
   //      is empty, throw IllegalStateException. 
   //      else if the character has already been guessed, throw IllegalArgumentException. 
   // post: record the guess, decide what set of words to use going foward, update pattern and 
   //       number of guesses left, and return an integer represents the number of occurrences 
   //       of the guessed letter in the new pattern. 
   public int record(char guess) {
      if (times < 1 || words.isEmpty()) {
         throw new IllegalStateException(); 
      } else if (guesses.contains(guess)) {
         throw new IllegalArgumentException();
      }
      guesses.add(guess); 
      Map<String, Set<String>> chooseWords = recordFamily(guess); 
      String chosenKey = findFamily(chooseWords); 
      words = chooseWords.get(chosenKey); 
      int record = checkCorrect(chosenKey); 
      if (record == 0) { 
         times--; // available guesses left decrease if no character has been guessed right. 
      }
      pattern = chosenKey;
      return record; 
   }
   
   // pre: passed in the character guessed by the user. 
   // post: create a map of string to set of strings that stores all the possible patterns 
   //       over the character guessed with all words fit these patterns separately, and 
   //       return it. 
   private Map<String, Set<String>> recordFamily(char guess) {
      Map<String, Set<String>> chooseWord = new TreeMap<String, Set<String>>();
      for (String oneWord : words) {
            String changedPattern = changePattern(guess, oneWord);
            if (!chooseWord.containsKey(changedPattern)) {
                chooseWord.put(changedPattern, new TreeSet<String>());
            }
            chooseWord.get(changedPattern).add(oneWord);
      }
      return chooseWord; 
   }
   
   // pre: pass in a certain character and a string of a certain word. 
   // post: create a string represents the pattern of the word over the character. 
   //       if the word contains the character, it will show up the character at 
   //       its position, with other positions unchanged. 
   //       return this string. 
   private String changePattern(char guess, String oneWord) {
      String result = pattern;
      for (int i = 0; i < oneWord.length(); i++) {
         if (oneWord.charAt(i) == guess) {
            result = result.substring(0, i * 2) + guess + result.substring(i * 2 + 1); 
         }
      }
      return result; 
   }
   
   // pre: passed in a map of string to set of strings with all possible word families. 
   // post: find the family with most elements. If there are 2 families contains same 
   //       numbers of words, choose the one occurs earlier in the map. 
   //       return a string of the pattern which matches the family be found. 
   private String findFamily(Map<String, Set<String>> chooseWords) { 
      int max = 0;
      String chosenKey = ""; 
      for (String oneKey : chooseWords.keySet()) {
         int familySize = chooseWords.get(oneKey).size(); 
         if (max < familySize) {
            max = familySize; 
            chosenKey = oneKey; 
         }
      }
      return chosenKey; 
   }
   
   // pre: pass in a string of the pattern which matches the family going foward.  
   // post: count how many letters are guessed correctly and return this count as an integer. 
   private int checkCorrect(String chosenKey) {
      int correctCount = 0; 
      for (int i = 0; i < pattern.length(); i ++){
         if (pattern.charAt(i) != chosenKey.charAt(i)) {
            correctCount++; 
         } 
      }
      return correctCount; 
   }
}