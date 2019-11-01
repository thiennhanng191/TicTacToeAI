/** 
 * Representing a TicTacToe game.
 * @author Nhan Nguyen
 * @version CMPU-102-Homework2 2.0
 */
public class TicGame 
{
    protected char BLANK = ' ';
    protected static char[][] board;
    protected final int boardSize;
    protected char gameResult;

    /**
     * Constructor for objects of class TicGame.
     * @param boardSize the size of the board; the board will have the size of 
    boardSize x boardSize
     */
    public TicGame(int boardSize) 
    {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];
        resetGame();
    }

    /**
     * executes a move, given a move and a symbol that represents a move.
    return false if the provided move is invalid.
     * @param move a TicMove.
     * @param symbol a character symbol representing a move.
     * @return true if the move is valid, false otherwise.
     */
    protected boolean executeMove(TicMove move, char symbol) 
    {
        if (isValidMove(move)) {
            board[move.row][move.col] = symbol; 
            return true;
        }
        else
            return false;
    }

    /** 
     * returns the size of the board. 
     * @return size of the board.
     */
    protected int getBoardSize() 
    {
        return boardSize; 
    }

    /**
     * return the result of the game after it is over in the form of a character: '?' means the game is not over yet; 
    "D" means that there is a draw; 'X' means that the user wins; 'O' means that the cpu wins. 
     * @return a character representing the game result.
     */
    public char getGameResult() 
    {
        boolean checkBoardRow = false;
        for (int i=0; i<(board.length); i++) 
        {
            checkBoardRow = false; 
            if (board[i][0] == BLANK) 
                continue; 
            for (int j=1; (j<board.length); j++) 
            {
                if (board[i][0] != board[i][j]) { 
                    checkBoardRow = true;
                    break;
                }
            }
            if (!checkBoardRow) 
                return board[i][0];
        }

        boolean checkBoardColumn = false; 
        for (int j=0; j<(board.length); j++)
        {
            checkBoardColumn = false; 
            if (board[0][j] == BLANK)
                continue; 
            for (int i=1; i<board.length; i++) 
            {
                if (board[0][j] != board [i][j]) {
                    checkBoardColumn = true; 
                    break;
                }
            }
            if (!checkBoardColumn)
                return board[0][j];
        }   

        //check diagonal items from upper left to lower right in the board
        boolean checkBoardDiagonalLeft = false;
        if (board[0][0] != BLANK) {
            for (int i=1; i< board.length; i++)
            {            
                if (board[0][0] != board[i][i]) {
                    checkBoardDiagonalLeft = true;
                    break;
                }
            }
            if (!checkBoardDiagonalLeft) 
                return board[0][0]; 
        }

        //check diagonal items from upper right to lower left in the board
        boolean checkBoardDiagonalRight = false;
        if (board[board.length-1][0] != BLANK) {
            for (int i= board.length -1 ; i>=0; i--)
            {
                if (board[board.length-1][0] != board[i][board.length - 1 - i]) {
                    checkBoardDiagonalRight = true;
                    break; 
                }
            }
            if (!checkBoardDiagonalRight) 
                return board[board.length-1][0];
        }

        boolean checkEntireBoard = false; 
        for (int i=0; i<board.length; i++) 
        {
            for (int j=0; j<board.length; j++)
            {
                if (board[i][j] == BLANK) {
                    checkEntireBoard = true;
                }
            }
        }
        if (!checkEntireBoard) 
            return 'D'; 

        return '?'; 
    }

    /**
     * check the conditions that would make the game over.
     * @return true if there's a row, a column or diagonal full of the same item
    or when the entire board is filled; false otherwise.
     */
    public boolean isGameOver()
    {
        if (getGameResult() == 'X' || getGameResult() == 'O' || getGameResult() == 'D')
            return true; 
        else 
            return false; 
    }

    /**
     * check if a move is valid move, that is if the move is inside the range of the board
    and it hasn't been played before by either the cpu or human.
     * @param move a TicMove, either of the cpu or human. 
     * @return true if the input move is valid, false otherwise.
     */
    public boolean isValidMove(TicMove move) 
    {
        // a move is valid when it's in the board's range and the item in the board is blank 
        // (meaning that neither cpu nor player has played that move)
        boolean x = ((move.col < boardSize) && (move.col >= 0) && (move.row < boardSize) && (move.row >= 0)
                && (board[move.row][move.col] == BLANK));

        return x;
    }

    /**
     * count how many turns (how many moves) have been played
     * @return the number of turns 
     */
    public int countTurn()
    {
        int countTurn = 0;
        for (int i =0; i < board.length; i++)
            for (int j=0; j < board.length; j++) {
                if (board[i][j] != BLANK)
                    countTurn++;
            }
        return countTurn;
    }

    /**
     * Compute a single-player game of TicTacToe and keep running consecutive games until the 
    user quits. If the user quits, the winning statistics will be printed. 
     * @param args the first argument stands for the wanted size of the game board, which should be an integer
    in [1,9]. If the provided board size does not satisfy the requirements or if there's no argument provided,
    a default game board size of 3x3 will be constructed
     */
    public static void main(String[] args) 
    {
        System.out.println("Welcome to TicTacToe game by Nhan Nguyen\n");
        System.out.println("------------------------NEW GAME------------------------"); 
        int inputInt;
        if (args.length > 0) {    
            try {
                inputInt = Integer.parseInt(args[0]); 
            } catch (NumberFormatException e){ 
                System.out.println("Invalid input. Print board [3x3]");
                inputInt =  3; 
            }
        }
        else 
        {
            inputInt =  3; 
        }

        if (inputInt < 1 || inputInt > 9)
            inputInt = 3; 

        TicGame ticGame = new TicGame(inputInt);  
        System.out.print(ticGame);
        ATicPlayer human = new HumanTicPlayer(ticGame);
        ATicPlayer cpu = new CpuTicPlayer(ticGame);
        boolean checkFirstPlayer = Math.random() < 0.5;
        int countHuman = 0; 
        int countCpu = 0; 
        int countDraw = 0;
        int firstRow = 0;
        int firstCol = 0;
        TicMove cpuCurrentMove = new TicMove (-1, -1); 
        while (true)
        {
            if (checkFirstPlayer)
            { 
                TicMove humanMove = human.pickMove();
                if (humanMove == null) 
                    break;
                ticGame.executeMove(humanMove, human.getSymbol()); 
                System.out.println(ticGame);
                checkFirstPlayer = false; // switch turn
                if (ticGame.isGameOver()) 
                {
                    ticGame.getGameResult();
                    if (ticGame.getGameResult() == 'X') { 
                        countHuman++; 
                        System.out.println("You Win!"); 
                    }
                    else if (ticGame.getGameResult() == 'O') {
                        countCpu++;
                        System.out.println("Cpu Wins!"); 
                    }
                    else if (ticGame.getGameResult() == 'D') {
                        countDraw++;
                        System.out.println("It's a Draw!"); 
                    }
                    ticGame.resetGame();
                    checkFirstPlayer = false;
                    System.out.println("------------------------NEW GAME------------------------");
                    System.out.println(ticGame);
                    //checkFirstPlayer = false; // switch the player
                    System.out.println("Cpu's Turn to Go First"); 
                }
            }
            else 
            {
                // execute cpu first move 
                if (ticGame.countTurn() == 0 || ticGame.countTurn() == 1) {
                    cpuCurrentMove = cpu.pickFirstMove(); 
                    ticGame.executeMove(cpuCurrentMove, cpu.getSymbol());
                }
                else 
                {
                    if (cpu.checkSurrounded(cpuCurrentMove)) // if the cpu move is surrounded, then execute cpu move at random
                    {
                        cpuCurrentMove = (cpu.pickMove()); 
                        ticGame.executeMove(cpuCurrentMove, cpu.getSymbol()); 
                    }
                    else //execute the next move based on cpu current move
                    {
                        cpuCurrentMove = cpu.pickNextMove(cpuCurrentMove); 
                        ticGame.executeMove(cpuCurrentMove, cpu.getSymbol()); 
                    }
                }
                System.out.println(ticGame);
                checkFirstPlayer = true; // switch turn
                if (ticGame.isGameOver()) 
                {
                    ticGame.getGameResult();
                    if (ticGame.getGameResult() == 'X') { 
                        countHuman++; 
                        System.out.println("User Wins! You lost! Try again?"); 
                    }
                    else if (ticGame.getGameResult() == 'O') {
                        countCpu++;
                        System.out.println("Cpu Wins!"); 
                    }
                    else if (ticGame.getGameResult() == 'D') {
                        countDraw++;
                        System.out.println("It's a Draw!"); 
                    }
                    ticGame.resetGame();
                    checkFirstPlayer = true; //switch the player
                    System.out.println("------------------------NEW GAME------------------------");
                    System.out.println(ticGame);
                    //checkFirstPlayer = true; //switch the player
                    System.out.println("User's Turn To Go First"); 
                }
            } 

        }
        System.out.println("Winning Statistics: "); 
        double totalGame = countHuman + countCpu + countDraw; 
        double humanPercentage = (countHuman / totalGame) *100; 
        double cpuPercentage = (countCpu / totalGame) *100; 
        System.out.println("You Won " + countHuman + " ames."); 
        System.out.println("Cpu Won " + countCpu + " games."); 
        System.out.println("Draw " + countDraw + " games."); 
        System.out.println("You Won " + countHuman + " out of " + (int) totalGame + " games (" + (int) humanPercentage + "%)");
        System.out.println("Cpu Won " + countCpu + " out of " + (int) totalGame + " games (" + (int) cpuPercentage + "%)");
        System.out.println("Goodbye!");
    } 

    /** 
     * reset the game by creating an empty board.
     */
    protected void resetGame() 
    {
        for (int i=0; i<board.length;i++)
            for(int j=0; j<board.length; j++)
                board[i][j] = BLANK;
    }

    /** 
     * create the visual of the game board of a tictactoe game. 
     * @return a string representing the game board in the format of a tictactoe's game board.
     */
    public String toString()
    {
        String s = "\n";
        s += " "; // add a space at the start of the number line
        for (int k=0; k < board.length; k++)
            s += " " + (k+1) + "  ";

        s += "\n";    
        for (int i=0; i < board.length; i++) {
            s += "" + (char) ('A' + i) ;
            // for loop to print out the row with empty item
            for (int j=0; j < board.length; j++) {
                if (j == (board.length - 1)) 
                    s += " " + board[i][j] + "" + "\n";
                else 
                    s += " " + board[i][j] + " " + "|";   
            }
            s += " "; // add a space at the start of the dashed line
            if (i != (board.length - 1)) // there's no dash line in the last row of the board
            {
                // another for loop to print out the dashed line after each row of empty item   
                for (int j=0; j < board.length; j++) {
                    if (j == (board.length - 1)) // add a new line after the end of the dashed line
                        s +=  "---" + "\n";
                    else 
                        s +=  "---" + "|"; 
                }
            }   

        }
        return s;  
    }

}