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

    public static String symbol;

    public static boolean winnerFound = false;






    public static void main(String[] args) {

        // creating and initializing two player objects
        Players player1 = new Players(userInputString("Player 1, please enter your name here: "));
        Players player2 = new Players(userInputString("Player 2, please enter your name here: "));

        // game-loop
        //for (int i = 0; i < 9; i++) {
        while (!winnerFound){
            for (int i = 0; i < 9 ; i++) {

                if (i % 2 == 0) {
                    System.out.printf("Player 1: \n%s it's your turn\n\n", player1.getName());
                    symbol = "x";
                } else {
                    System.out.printf("Player 2: \n%s it's your turn\n\n", player2.getName());
                    symbol = "o";
                }

                theGame(symbol);

                if (winnerFound){
                    break;
                }
            }
        }
    }


    public static String userInputString (String prompt){

        // print prompt message
        System.out.printf(prompt);

        // create scanner obj
        Scanner scanner = new Scanner(System.in);

        // stores input
        return scanner.nextLine();
    }



    public static int userInputInt (String prompt,int min, int max) {

        // create scanner obj
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
                    // error message
                    System.out.printf("Error: please enter a number between %d and %d\n", min, max);
                }
            } else {
                // error message
                System.out.printf("Invalid input. please enter a number between %d and %d\n", min, max);
                //clean buffer
                scanner.reset().next();
            }
        }
    }

    public static boolean theGame (String playerSymbol){

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

        check3InARow(symbol);

        return winnerFound;
    }


    public static void printGameBoard (){
        for (int i = 0; i < gameBoardArray.length; i ++){

            for (int j = 0; j <gameBoardArray[i].length; j++){
                System.out.printf("%4s",gameBoardArray[i][j]);
            }
            System.out.println();
        }
    }

    public static void printGame (){
        for (int i = 0; i < gameArray.length; i ++){

            for (int j = 0; j <gameArray[i].length; j++){
                System.out.printf("%4s",gameArray[i][j]);
            }
            System.out.println();
        }
    }




    public static boolean check3InARow (String playerSymbol){
        System.out.println();

        for (int i = 0 ; i <= 4 ; i += 2) {

            // checks the 3 rows
            if (gameArray[i][0] == symbol && gameArray[i][2] == symbol && gameArray[i][4] == symbol){
                System.out.println("We have a winner");
                return winnerFound;
            }

            // checks the 3 column
            if (gameArray[0][i] == symbol && gameArray[0][i] == symbol && gameArray[0][i] == symbol){
                System.out.println("We have a winner");
                return winnerFound;
            }
        }

        // checks the one diagonal
        if (gameArray[0][0] == symbol && gameArray[2][2] == symbol && gameArray[4][4] == symbol){
            System.out.println("We have a winner");
            return winnerFound;
        }

        // checks the other diagonal
        else if (gameArray[0][4] == symbol && gameArray[2][2] == symbol && gameArray[4][0] == symbol){
            System.out.println("We have a winner");
            return winnerFound;
        }

        else
            return !winnerFound;
    }


}
