/** 
 * An abstract class representing a player in the TicTacToe game. 
 * @author Nhan Nguyen
 * @version CMPU-102-Homework2 2.0
 */
public abstract class ATicPlayer
{
    protected TicGame game;
    protected char symbol;
    protected char BLANK = ' ';
    /**
     * Empty constructor for objects of class ATicPlayer.
     */
    protected ATicPlayer()
    {
    }

    /**
     * Constructor for objects of class ATicPlayer.
     */
    protected ATicPlayer(TicGame game)
    {
        this.game = game; 
    }

    /** 
     * Returns the symbol representing this player.
     * @return a character representing the player's symbol. 
     */
    public char getSymbol()
    {
        return symbol; 
    }

    /** 
     * Enables the player (cpu and human) make the next move. The cpu and human player implements this method differently based on its own
       semantics. 
     * @return the move that the player picked. 
     */
    public abstract TicMove pickMove();
    
    /** 
     * Enables the cpu player to make the its first move strategy
     * @return the cpu's first move. 
     */
    public abstract TicMove pickFirstMove();
    
    /** 
     * Enables the cpu to execute the next move based on its previous move
     * @return the cpu's next move.
     */
    public abstract TicMove pickNextMove(TicMove ticMove);
    
    /**
     * check if a move is surrounded, that is all the moves around it have been played. (Only cpu player should make use of this method).
     * return true if a move has been surrounded.
     */
    public abstract boolean checkSurrounded(TicMove ticMove);
}
