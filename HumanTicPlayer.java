import java.util.Scanner;
/** 
 * Representing a human player in the TicTacToe game, whose moves are input by the user.
 * @author Nhan Nguyen
 * @version CMPU-102-Homework2 2.0
 */

public class HumanTicPlayer extends ATicPlayer
{
    /**
     * Constructor for objects of class HumanTicPlayer.
     */
    public HumanTicPlayer(TicGame game)
    {
        super(game); // call the game field from the superclass ATicPlayer
    }

    /**
     * Ask the user to make a tic-tac-toe move. The human move is the input that the user provides, consisting of 2 characters, while the 
    former character is a letter representing the number of the rows and the ladder character is a digit representing the numbers 
    of the columns. Print out error messages if the input does not consist of 2 characters, the input move is outside of the game
    board's range or if the move has been played by either cpu or human player. 
     * @specified by pickMove in class ATicPlayer
     * @return a valid TicMove from the user or null if the user wants to quit.
     */
    public TicMove pickMove()
    {
        Scanner scanner = new Scanner(System.in);
        TicMove ticMove = new TicMove (-1, -1);
        System.out.println("Your move (quit to exit game):"); 
        while (true) {
            System.out.println("Please insert a valid move with a letter for the row and a number for the column"); 
            String s = scanner.next();
            if (s.equals("quit") || s.equals("QUIT")){
                return null;
            }

            if (s.length() != 2)
            {
                System.out.println("A move must have 2 characters, one for row, one for column"); 
                continue; 
            }

            char charRow = s.charAt(0);
            charRow = Character.toUpperCase(charRow); //if the input is lowercase then make it uppercase, else remain the same
            int intCol = (Character.digit(s.charAt(1), 10));
            int col = intCol - 1; // column index starts from 0
            // convert the char input to its numeric value
            int row = (int) (charRow) - 65; // minus 65 because 'A' has the numeric value of 65 

            ticMove = new TicMove(row, col); 

            if ((ticMove.col < game.boardSize) && (ticMove.col >= 0) && (ticMove.row < game.boardSize) && (ticMove.row >=0)) 
            {
                if (game.board[ticMove.row][ticMove.col] != ' ')
                    System.out.println("This move has been played."); 
            }
            else 
            { 
                if (ticMove.row >= game.boardSize || ticMove.row < 0) 
                    System.out.println("Invalid Row. Row is outside of game board's range"); 
                else if (ticMove.col < game.boardSize || ticMove.col >=0)
                    System.out.println("Invalid Column. Column is outside of game board's range"); 
            }
            if (game.isValidMove(ticMove)) 
                break; 
        }

        return ticMove;
    }

    /**
     * serves no function, only there for the cpu to execute its pickFirstMove() method
     * @return null
     */
    public TicMove pickFirstMove() {
        return null;
    }

    /**
     * serves no function, only there for the cpu to execute its pickNextMove() method
     * @return null
     */
    public TicMove pickNextMove(TicMove ticMove){
        return null; 
    }

    /**
     * serves no function, only there for the cpu to execute its checkSurrounded(ticMove) method
     * @return false
     */
    public boolean checkSurrounded(TicMove ticMove){
        return false;
    }
    
    /**
     * return a symbol representing the move of human player (the user).
     * @return character 'X' for human player.
     */
    public char getSymbol()
    {
        return 'X'; 
    }

}
