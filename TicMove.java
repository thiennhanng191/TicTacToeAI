/** 
 * Representing a TicMove, or a move played in the TicTacToe game.
 * @author Nhan Nguyen
 * @version CMPU-102-Homework2 2.0
 */
public class TicMove
{
    int col;
    int row;
    
    /**
     * Constructor for objects of class TicMove.
     */
    public TicMove(int row, int col)
    {
        this.row = row; 
        this.col = col;
    }
}
