import java.util.Objects;
import java.util.Scanner;

public class Main {

    // 2D array used as a game board
    public static String [][] gameBoardArray = { {"1" , "|" , "2" , "|" , "3"},
                                                 {"-" , "+" , "-" , "+" , "-"},
                                                 {"4" , "|" , "5" , "|" , "6"},
                                                 {"-" , "+" , "-" , "+" , "-"},
                                                 {"7" , "|" , "8" , "|" , "9"} };

    // 2D array that handles players game moves
    public static String[][] gameArray = {       {"" , "|" , "" , "|" , ""},
                                                 {"-" , "+" , "-" , "+" , "-"},
                                                 {"" , "|" , "" , "|" , ""},
                                                 {"-" , "+" , "-" , "+" , "-"},
                                                 {"" , "|" , "" , "|" , ""} };


    // variable to handle the players symbols; x and o
    public static String symbol;

    // variable to handle player names
    public static String player;

    public static boolean winnerStatus;




    public static void main(String[] args) {

        // creating and initializing two player objects using a scanner and a user input
        Players player1 = new Players(userInputString("Player 1, please enter your name here: "));
        Players player2 = new Players(userInputString("Player 2, please enter your name here: "));


        // game loop that keeps track of the player order - if no winner, the game stops after ninth round
        for (int i = 0; i < 9; i++) {

            // player 1
            if (i % 2 == 0) {
                System.out.printf("\nPlayer 1: \n%s it's your turn\n\n", player1.getName());
                player = player1.getName();
                symbol = "x";
                }
            // player 2
            else {
                System.out.printf("\nPlayer 2: \n%s it's your turn\n\n", player2.getName());
                player = player2.getName();
                symbol = "o";
                }

                theGame(symbol);

                // local variable to store the result from the "check3InARow" method
                boolean winnerStatus = check3InARow(symbol);

                // breaks the game loop if one of the players have 3 in a row (winnerStatus = true)
                if (winnerStatus){
                    i = 10;
                    System.out.println("YEAH! We have a winner!");
                    System.out.printf("Congrats %s - big applause!",player);

                // writes an "end-message" if no winner found (winnerStatus = false) during nine rounds
                } else if (!winnerStatus && i == 8) {
                    System.out.println("No winner found. Maybe you want to try again? :D");
                }
        }
    }

    /**
     * This method prompts the user for a String input
     *
     * @param prompt message to user
     * @return String input from user
     */
    public static String userInputString (String prompt){

        // print prompt message
        System.out.printf(prompt);

        // create scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // returns input from user
        return scanner.nextLine();
    }


    /**
     * This method prompts the user for an int input.
     * Afterward , the user's input is validated in two steps;
     * First, it is checked whether an int has been entered. If yes, the user's input is further
     * validated against the min and max values; otherwise, the user is prompted to enter again.
     *
     * @param prompt message to user
     * @param min value
     * @param max value
     * @return int input from user
     */
    public static int userInputInt (String prompt,int min, int max) {

        // create scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // print prompt message
            System.out.printf("\n%s",prompt);

            // if user input is an int
            if (scanner.hasNextInt()) {

                // stores the user input as a local variable
                int input = scanner.nextInt();

                // if input is in between the min and max value
                if (input >= min && input <= max) {

                    // cleans buffer (doMagic!)
                    scanner.nextLine();

                    // returns input from user
                    return input;

                // if input isn't in between the min and max value
                } else {
                    // error message - if input is less than min value or more than max value
                    System.out.printf("Error: please enter a number between %d and %d\n", min, max);
                }
            }
            // if user input isn't an int
            else {
                // error message - if input isn't a number
                System.out.printf("Invalid input. please enter a number between %d and %d\n", min, max);

                //clean buffer (doMagic!)
                scanner.nextLine();
            }
        }
    }

    /**
     * This method is the actual game.
     * The user is shown the possible moves. Afterward, the user is prompted to enter their next move.
     * Using a switch case, the user's entered value/next move is transformed into a player symbol in the
     * corresponding field on the game board, and the user's move is removed from the game board.
     * Finally, the game get printed with the latest move.
     *
     * @param playerSymbol either "x" or "o"
     */
    public static void theGame (String playerSymbol){

        // shows possible moves at the game board
        System.out.println("Possible moves:");
        printGameBoard();

        // gets player move from user and stores it as a local variable
        int playerMove = userInputInt("Please enter your next move here: ", 1, 9);

        // switch case to switch player move into a symbol at the gameArray and an empty space at the game board
        switch (playerMove) {

            case 1:
                gameArray[0][0] = symbol;
                gameBoardArray[0][0] = "";
                break;

            case 2:
                gameArray[0][2] = symbol;
                gameBoardArray[0][2] = "";
                break;

            case 3:
                gameArray[0][4] = symbol;
                gameBoardArray[0][4] = "";
                break;

            case 4:
                gameArray[2][0] = symbol;
                gameBoardArray[2][0] = "";
                break;

            case 5:
                gameArray[2][2] = symbol;
                gameBoardArray[2][2] = "";
                break;

            case 6:
                gameArray[2][4] = symbol;
                gameBoardArray[2][4] = "";
                break;

            case 7:
                gameArray[4][0] = symbol;
                gameBoardArray[4][0] = "";
                break;

            case 8:
                gameArray[4][2] = symbol;
                gameBoardArray[4][2] = "";
                break;

            case 9:
                gameArray[4][4] = symbol;
                gameBoardArray[4][4] = "";
                break;
        }

        // prints the game with the latest move
        printGame();
    }


    /**
     * This method prints the game board as a 2D array
     */
    public static void printGameBoard (){
        for (int i = 0; i < gameBoardArray.length; i ++){

            for (int j = 0; j <gameBoardArray[i].length; j++){
                System.out.printf("%4s",gameBoardArray[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method prints the game as a 2D array
     */
    public static void printGame (){
        for (int i = 0; i < gameArray.length; i ++){

            for (int j = 0; j <gameArray[i].length; j++){
                System.out.printf("%4s",gameArray[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n___________________________________");
    }


    /**
     * This method checks if a player has 3 equal symbols in a row.
     * All rows are checked, all columns are checked, and finally, the two diagonals are checked.
     * If a player has 3 in a row, the method returns "true," and if a player doesn't have 3 in a row,
     * it returns "false."
     *
     * @param playerSymbol either "x" or "o"
     * @return a boolean
     */
    public static boolean check3InARow (String playerSymbol){
        System.out.println();

        // for loop to check if there is 3 equal symbols in a row (either in one of the rows or one of the columns)
        for (int i = 0 ; i <= 4 ; i += 2) {

            // checks the 3 rows
            if (gameArray[i][0] == symbol && gameArray[i][2] == symbol && gameArray[i][4] == symbol){
                return true;
            }

            // checks the 3 column
            if (gameArray[0][i] == symbol && gameArray[2][i] == symbol && gameArray[4][i] == symbol){
                return true;
            }
        }

        // checks the one diagonal
        if (gameArray[0][0] == symbol && gameArray[2][2] == symbol && gameArray[4][4] == symbol){
            return true;
        }

        // checks the other diagonal
        else if (gameArray[0][4] == symbol && gameArray[2][2] == symbol && gameArray[4][0] == symbol){
            return true;
        }

        // if there isn't 3 equal symbols in a row
        else
            return false;
    }
}
