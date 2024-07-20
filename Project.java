import java.awt.*;

/* user inputs command line argument corresponding to the number of questions they want to answer and runs this class for
the game to start. the game is run using StdDraw.
*/

public class Project {
    //instance variables
    public Country[] countries; //store countries
    public int numQuestions; //number of questions
    public int score; //score

    //create constructor and initialize variables
    public Project(Country[] countries, int numQuestions) {
        this.countries = countries;
        this.numQuestions = numQuestions;
        this.score = 0;
    }

    public static void main(String[] args) {
        Country[] countries = Flags.getCountries(); //get array of country names and flag

        if (args.length != 1) { // check command line argument
            System.err.println("only one integer input please");
            System.exit(1);
        }

        int numQuestions = Integer.parseInt(args[0]); //command line

        if (numQuestions <= 0 || numQuestions > countries.length) { // check command line argument
            System.err.println("Please enter a number between 1 and " + countries.length);
            System.exit(1);
        }
        // start the game
        Project game = new Project(countries, numQuestions);
        game.startPage();
    }

    public void startPage() {
        StdDraw.setCanvasSize(400, 600); // set up the canvas size
        StdDraw.setScale(0, 6); // set up the canvas scale

        StdDraw.clear(Color.decode("#b5c9bc"));

        //print logo
        StdDraw.picture(3,4, "https://i.postimg.cc/5ttmbw8S/logo.png", 3.7, 2.5);
        //credits: logo created with Canva.com

        //create start game button
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,1.5,1.5,0.2);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,1.5,1.5,0.2);
        StdDraw.text(3,1.5,"Start Game");

        //create instructions button
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,2,1.5,0.2);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,2,1.5,0.2);
        StdDraw.text(3,2,"Instructions");

        StdDraw.show();

        //get mouse clicks
        while (!StdDraw.isMousePressed()) {}
        double yClick = StdDraw.mouseY();//get y coordinate of user click
        double xClick = StdDraw.mouseX();//get y coordinate of user click
        while (StdDraw.isMousePressed()) {}

        //if start game button is clicked
        if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.3) && yClick <= (1.7)) playGame();
        //instructions button is clicked
        if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.8) && yClick <= (2.2)) instructionsPage();
    }

    public void instructionsPage(){
        StdDraw.setCanvasSize(400, 600); // set up the canvas size
        StdDraw.setScale(0, 6); // set up the canvas scale

        StdDraw.clear(Color.decode("#b5c9bc"));

        //create start game button
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,1.5,1.5,0.2);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,1.5,1.5,0.2);
        StdDraw.text(3,1.5,"Start Game");

        //create back button to go back to start page
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,2,1.5,0.2);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,2,1.5,0.2);
        StdDraw.text(3,2,"Back");

        //instructions
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,4,1.5,1);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,4,1.5,1);
        StdDraw.text(3,4.3,"Click 'Start Game'");
        StdDraw.text(3,4.1,"Choose the flag :)");


        StdDraw.show();

        //get mouse clicks
        while (!StdDraw.isMousePressed()) {}
        double yClick = StdDraw.mouseY();//get y coordinate of user click
        double xClick = StdDraw.mouseX();//get y coordinate of user click
        while (StdDraw.isMousePressed()) {}

        //if start game button is clicked
        if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.3) && yClick <= (1.7)) playGame();
        //if back button is clicked
        if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.8) && yClick <= (2.2)) startPage();
    }

    // game play is implemented here
    public void playGame() {
        StdDraw.setCanvasSize(400, 600); // set up the canvas size
        StdDraw.setScale(0, 6); // set up the canvas

        score =0;// set score to 0 for each iteration of the game
        shuffleFlags();

        //create for loop here to call methods for i<numQuestions
        for (int i = 0; i < numQuestions; i++) {
            // choose the correct flag for the question
            Country questionCountry = getRandomCountry();
            int correctFlagIndex = (int) (Math.random() * 3);

            displayQuestion(questionCountry, correctFlagIndex); //print question
            int userAnswer = getUserGuess(correctFlagIndex); //get user answer
            checkAnswer(correctFlagIndex, userAnswer, numQuestions); //check answer and update screen
        }

        //this is the last screen displayed, it shows the final score out of the total number of questions
        feedbackPage(score, numQuestions);
    }

    //get a random country from the array for each of the questions
    private Country getRandomCountry() {
        int randomCountryIndex = (int) (Math.random() * countries.length);
        return countries[randomCountryIndex];
    }

    //print the question on the screen
    private void displayQuestion(Country country, int correctFlagIndex) {
        //the name of the country should be at the top of the screen
        StdDraw.clear(Color.decode("#b5c9bc"));

        //the name of the country should be at the top of the screen
        StdDraw.setPenColor(Color.decode("#F1D5DB"));
        StdDraw.filledRectangle(3,5.5,1.5,0.2);
        StdDraw.setPenColor(Color.decode("#C15B75"));
        StdDraw.rectangle(3,5.5,1.5,0.2);
        StdDraw.text(3,5.5, country.getName());

        int[] incorrectFlags = getIncorrectFlags(correctFlagIndex); //store incorrect flags here

        //show the 3 answer choices, one should be the correct flag... use a for loop where i<3
        for (int i = 0; i < 3; i++) {
            //x and y coordinates of the flags
            double xCoordinate = 3;
            double yCoordinate = 4 - i * 1.5;

            String flagUrl;
            if (i == correctFlagIndex) flagUrl = country.getFlag(); //get correct flag url
            else flagUrl = countries[incorrectFlags[i]].getFlag(); //incorrect flag urls

            //print flag pictures
            StdDraw.picture(xCoordinate, yCoordinate, flagUrl, 1.6, 1);
        }
        // print flags
        StdDraw.show();
        StdDraw.pause(1000);
    }

    // this method will get the two other random incorrect answer choices out of the entire array of 210 countries
    private int[] getIncorrectFlags(int correctFlagIndex) {
        int[] numChoices = new int[3]; //there are 3 total answer choices but only 2 will be the incorrect flags
        numChoices[0] = correctFlagIndex; //the first flag will be the right one

        shuffleFlags(); //shuffle flags

        // get two random incorrect flags
        for (int i = 1; i < 3; i++) {
            int randomIndex = 0;
            while ( contains(numChoices, randomIndex, i)) {
                randomIndex = (int) (Math.random() * countries.length); }
            numChoices[i] = randomIndex;
        }
        return numChoices;
    }

    // this is a helper method that will be used in getIncorrectFlags to make sure that the flags aren't repeating
    private boolean contains(int[] array, int usedFlag, int endIndex) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i] == usedFlag) return true;
        }
        return false;
    }

    //to shuffle flags before each time the game is played to ensure more random questions -- adapted from card game assignment
    public void shuffleFlags() {
        for (int i = 0; i < countries.length - 1; i++) {
            int j = i + (int) (Math.random() * (countries.length - i));
            Country tmp = this.countries[i];
            this.countries[i] = this.countries[j];
            this.countries[j] = tmp;
        }
    }

    //this method should get the users answer based on where their mouse clicks
    private int getUserGuess(int correctFlagIndex) {
        while (!StdDraw.isMousePressed()) {}
        double yClick = StdDraw.mouseY(); //get y coordinate of user click
        while (StdDraw.isMousePressed()) {}
        for (int i = 0; i < 3; i++) {
            if ((yClick >= ((4 - i * 1.5)-0.5) && yClick <= (4 - i * 1.5)+0.5) && i == correctFlagIndex) {
                return i; //if correct flag is clicked
            }
        }
        return -1; //if incorrect flag is clicked
    }

    //method should check the users guess and then update the score accordingly
    //score only +1 if correct, no change if incorrect
    private void checkAnswer(int correctFlagIndex, int userAnswer, int numQuestions) {
        if (userAnswer == correctFlagIndex) { //if answer is correct
            score++;
            StdDraw.setPenColor(Color.decode("#426651"));
            StdDraw.text(3, 5, "Correct! Score: " + score + "/" + numQuestions);
            StdDraw.setPenColor(StdDraw.BLACK);
        } else { //answer is incorrect
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.text(3, 5, "Incorrect! Score: " + score + "/" + numQuestions);
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }

    //final page-- display final score
    public void feedbackPage(int score, int numQuestions){

        if(( (double)score/ (double)numQuestions  < 0.5)){
            StdDraw.setCanvasSize(400, 600); // set up the canvas size
            StdDraw.setScale(0, 6); // set up the canvas scale
            StdDraw.clear(Color.decode("#b5c9bc")); //background color

            //try again message
            StdDraw.picture(3,4, "https://png.pngtree.com/png-vector/20220527/ourmid/pngtree-keep-trying-grunge-rubber-stamp-on-white-background-png-image_4748435.png", 3.7, 2);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(3, 2.5, "Game Over");
            StdDraw.text(3, 2.3, "Final Score: " + score + "/" + numQuestions);
            StdDraw.text(3, 2, (Math.round(((double)score/(double)numQuestions)*100)) + "%");
            StdDraw.show();

            //restart button
            StdDraw.setPenColor(Color.decode("#F1D5DB"));
            StdDraw.filledRectangle(3,1.5,1.5,0.2);
            StdDraw.setPenColor(Color.decode("#C15B75"));
            StdDraw.rectangle(3,1.5,1.5,0.2);
            StdDraw.text(3,1.5,"Play Again");

            //get mouse clicks
            while (!StdDraw.isMousePressed()) {}
            double yClick = StdDraw.mouseY();//get y coordinate of user click
            double xClick = StdDraw.mouseX();//get y coordinate of user click
            while (StdDraw.isMousePressed()) {}

            //if restart game button is clicked
            if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.4) && yClick <= (1.9)){
                playGame();
            }

        } else {
            StdDraw.setCanvasSize(400, 600); // set up the canvas size
            StdDraw.setScale(0, 6); // set up the canvas scale
            StdDraw.clear(Color.decode("#b5c9bc")); //background color

            //congratulations message
            StdDraw.picture(3,4, "https://images.vexels.com/media/users/3/245747/isolated/preview/fc5e5179e126bb8b8878c65ed0639179-great-job-badge.png", 3.7, 2.5);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(3, 2.5, "Game Over");
            StdDraw.text(3, 2.3, "Final Score: " + score + "/" + numQuestions);
            StdDraw.text(3, 2, (Math.round(((double)score/(double)numQuestions)*100)) + "%");
            StdDraw.show();

            //restart button
            StdDraw.setPenColor(Color.decode("#F1D5DB"));
            StdDraw.filledRectangle(3,1.5,1.5,0.2);
            StdDraw.setPenColor(Color.decode("#C15B75"));
            StdDraw.rectangle(3,1.5,1.5,0.2);
            StdDraw.text(3,1.5,"Play Again");

            //get mouse clicks
            while (!StdDraw.isMousePressed()) {}
            double yClick = StdDraw.mouseY();//get y coordinate of user click
            double xClick = StdDraw.mouseX();//get y coordinate of user click
            while (StdDraw.isMousePressed()) {}

            //if restart game button is clicked
            if (xClick >= (1.3) && xClick <= (4.5) && yClick >= (1.3) && yClick <= (1.7)){
                playGame();
            }
        }
    }
}

