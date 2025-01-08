# GuessTheFlag
How to play:

Command line argument: There is one command line argument (numQuestions) which represents how many questions the game will have. This command line argument has to be positive and between 1 and 210 (the number of flags that will be available in the game).

After the command line argument is inputted, the user has to run the Project class to start the game. 

For each question, the name of a randomly selected country will be printed at the top of the display. Underneath, there will be 3 choices of flags. The user should click on their answer.

After each question, the screen will print whether the answer was correct or incorrect and the updated score. After the amount of questions that the player initially wanted to do is completed, the final score will be displayed with either a “Congratulations” or “Try Again” message based on their score percentage. On this page, the player will have the option to restart the game.

The gameplay is programmed in the Project class, and that should be run, but there will be two additional classes-- Flags and Country. The Flags class will create an array of countries that has the names of 210 countries and territories and a corresponding URL of their flags. The Country class will create objects that represent countries, and each object will have a name and a flag.

