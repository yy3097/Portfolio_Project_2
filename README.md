# Portfolio Project: Evil Hangman
This repository is part of the portfolio uses for SFU MDM application only. 

This project is a homework from the computer programming course I took during my undergraduate study. All codes are completed in Java.

## Project Description
This is a hangman game as normal hangman except the computer does not have a definite answer in the beginning. The computer is always considering a set of words that could be the answer and will not let the users win until there’s only one word left given the letters chosen. In order to fool the user into thinking it is playing fairly, the computer only considers words with the same letter pattern. This program focuses on programming with Java Collections classes.


## Work Done
I completed HangmanManager.java file. The other files were provided by course instructor.


## File Description
**HangmanManager:** a class that manages the evil hangman game. 

**HangmanMain:** a client program that processing the file and uses for user interaction.

**dictionary & dictionary2:** the dictionary files read by HangmanMain as all possible answers of a game (can only use one dictionary per time), where switch between dictionaries can be done by adjusting the code on line 11.


## Outcome
While running HangmanMain.java, the program will start an evil hangman game uses all words in the given dictionary as the possible answers. The computer will narrow down the possible answers set to prevent the user from winning the game until there is only one word or there is no chance of guess left. The user can set the length of word to use and the number of guesses allowed at the beginning of the game. There is also a "DEBUG" mode on line 12 that can be switched on and off by changing the code, which will show all the current possible answers through user guessing. (The "DEBUG" should be "false" if using dictionary.txt since there are too many words in this file.)

For example, dictionary2.txt has the following words: 

>[ally, beta, cool, deal, else, flew, good, hope, ibex]

If the user guesses "e", the computer will narrow down the possible answers into: 

>[ally, cool, good]

The computer will do the same thing until: 

(i) there is only one word left in the set, and the user wins; 

(ii) the user has used up all guessing chances, and the computer wins. 
