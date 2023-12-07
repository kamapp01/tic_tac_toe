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

    // variable to handle if player 1 or player 2
    public static String player;

    public static boolean winnerStatus;




    public static void main(String[] args) {

        // creating and initializing two player objects using a scanner and a user input
        Players player1 = new Players(userInputString("Player 1, please enter your name here: "));
        Players player2 = new Players(userInputString("Player 2, please enter your name here: "));


        // game loop that keeps track of the player order - if no winner, the game stops after ninth round
        for (int i = 0; i < 9; i++) {

            if (i % 2 == 0) {
                System.out.printf("Player 1: \n%s it's your turn\n\n", player1.getName());
                player = "Player 1";
                symbol = "x";
                }
            else {
                System.out.printf("Player 2: \n%s it's your turn\n\n", player2.getName());
                player = "Player 2";
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


    public static String userInputString (String prompt){

        // print prompt message
        System.out.printf(prompt);

        // create scanner object
        Scanner scanner = new Scanner(System.in);

        // returns input
        return scanner.nextLine();
    }


    /**
     * This method
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public static int userInputInt (String prompt,int min, int max) {

        // create scanner object
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // print prompt message
            System.out.printf("\n%s",prompt);

            if (scanner.hasNextInt()) {

                int input = scanner.nextInt();

                if (input >= min && input <= max) {

                    // doMagic!
                    scanner.nextLine();

                    return input;

                } else {
                    // error message - if input is less than min value or more than max value
                    System.out.printf("Error: please enter a number between %d and %d\n", min, max);
                }
            }
            else {
                // error message - if input isn't a number
                System.out.printf("Invalid input. please enter a number between %d and %d\n", min, max);

                //clean buffer
                scanner.reset().next();
            }
        }
    }

    /**
     * This method
     *
     * @param playerSymbol
     * @return
     */
    public static void theGame (String playerSymbol){

        System.out.println("Possible moves:");
        printGameBoard();

        int playerMove = userInputInt("Please enter your next move here: ", 1, 9);

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

        printGame();

        //check3InARow(symbol);
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
    }


    /**
     * This method checks if a player has 3 in a row.
     *
     * @param playerSymbol
     * @return
     */
    public static boolean check3InARow (String playerSymbol){
        System.out.println();

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

        else
            return false;
    }


}
