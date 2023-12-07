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

    public static String playerX = "x";

    public static String playerO = "o";

    public static boolean winnerFound = false;

    public static boolean winnerStatus;






    public static void main(String[] args) {

        // creating and initializing two player objects
        Players player1 = new Players(userInputString("Player 1, please enter your name here: "));
        Players player2 = new Players(userInputString("Player 2, please enter your name here: "));


        // game-loop
        for (int i = 0; i < 9; i++) {

                if (i % 2 == 0) {
                    System.out.printf("Player 1: \n%s it's your turn\n\n", player1.getName());
                    symbol = playerX;
                } else {
                    System.out.printf("Player 2: \n%s it's your turn\n\n", player2.getName());
                    symbol = playerO;
                }

                theGame(symbol);
                boolean winnerStatus = check3InARow(symbol);
                System.out.println("efter check metode: "+winnerStatus);
        }
        System.out.println("YEAH!");
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
                System.out.println("We have a winner1");
                return true;
            }

            // checks the 3 column
            if (gameArray[0][i] == symbol && gameArray[2][i] == symbol && gameArray[4][i] == symbol){
                System.out.println("We have a winner2");
                return true;
            }
        }

        // checks the one diagonal
        if (gameArray[0][0] == symbol && gameArray[2][2] == symbol && gameArray[4][4] == symbol){
            System.out.println("We have a winner3");
            return true;
        }

        // checks the other diagonal
        else if (gameArray[0][4] == symbol && gameArray[2][2] == symbol && gameArray[4][0] == symbol){
            System.out.println("We have a winner4");
            return true;
        }

        else
            System.out.println(winnerFound);
            return false;
    }


}
