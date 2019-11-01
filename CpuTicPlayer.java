
/** 
 * Representing a cpu player in the TicTacToe game, whose moves are generated randomly
 * @author Nhan Nguyen
 * @version CMPU-102-Homework2 2.0
 */
public class CpuTicPlayer extends ATicPlayer
{
    /**
     * Constructor for objects of class CpuTicPlayer
     */
    public CpuTicPlayer(TicGame game)
    {
        super(game); //call the game field from the superclass ATicPlayer
    }

    /** 
     * make a move at random by generating random moves inside the range of
    the board, looping until the move is valid.
     * @specified by pickMove in class ATicPlayer.
     * @return the satisfied random move.
     */
    public TicMove pickMove()
    {
        int size = game.getBoardSize();
        TicMove cputicMove;
        do 
        {
            int row = (int) (size * Math.random()); // return random value in [0,size)
            int col = (int) (size * Math.random());
            cputicMove = new TicMove(row, col); 
        }
        while (!game.isValidMove(cputicMove)); 

        char charRow = (char) (cputicMove.row + 65);
        int charCol = (cputicMove.col + 1); 

        System.out.println("CPU's move: " + charRow + ""+ charCol); 
        return cputicMove;
    }

    int lastMoveRow = 0;
    int lastMoveCol = 0;

    /** 
     * check if a row is almost full, that is all the moves in a row but one have been played by the same user,
    either the cpu or human. update the lastMoveRow and lastMoveRow which is the position of cpu's next move
    (cpu's next move will occupy the only blank position left in the row).
     * @return true if the row is almost full, that is one of the users almost wins; false otherwise.
     */
    public boolean checkRowAlmostFull() {
        int countRow = 0; 
        int countBlank = 0; 
        for (int i=0; i<(game.boardSize); i++) 
        {
            countRow = 0;
            countBlank = 0;
            if (game.board[i][0] == BLANK)  // check when first index is empty, and the remaining of indice of same type
            {
                for (int j=1; (j<game.boardSize); j++) 
                {
                    if (game.board[i][0] != game.board[i][j]) { 
                        countRow++;
                    }
                }
                if (countRow == game.boardSize - 1) {
                    lastMoveRow = i;
                    lastMoveCol = 0;
                    return true;
                }
            }
            else { // check when row only has 1 empty index, which is not the first one, and the remaining of indice of same type
                {
                    for (int j=1; (j<game.boardSize); j++) 
                    {
                        if (game.board[i][0] == game.board[i][j]) { 
                            countRow++;
                        }
                        if (game.board[i][j] == BLANK)
                        {
                            lastMoveRow = i; 
                            lastMoveCol = j;
                            countBlank++;
                        } 
                    }
                    if ((countRow == game.boardSize - 2) && (countBlank == 1))
                        return true;
                }
            }
        }
        return false;
    }

    /** 
     * check if a column is almost full, that is all the moves in a column but one have been played by the same user,
    either the cpu or human. update the lastMoveRow and lastMoveCol which is the position of cpu's next move
    (cpu's next move will occupy the only blank position left in the columm).
     * @return true if the column is almost full, that is one of the users almost wins; false otherwise.
     */
    public boolean checkColAlmostFull() {
        int countCol = 0;
        int countBlank = 0; 

        for (int j=0; j <game.boardSize; j++)
        {
            countCol = 0; 
            countBlank = 0; 
            if (game.board[0][j] == BLANK)  // check when first index is empty, and the remaining indice are of same type
            {
                for (int i=1; (i<game.boardSize); i++) 
                {
                    if (game.board[0][j] != game.board[i][j]) { 
                        countCol++;
                    }
                }
                if (countCol == game.boardSize - 1)
                { 
                    lastMoveRow = 0; 
                    lastMoveCol = j; 
                    return true;
                }
            }
            else { // check when col only has 1 empty index, which is not the first one, and the remaining of indice of same type
                {
                    for (int i=1; (i<game.boardSize); i++) 
                    {
                        if (game.board[0][j] == game.board[i][j]) { 
                            countCol++;
                        }
                        if (game.board[i][j] == BLANK)
                        {
                            lastMoveRow = i; 
                            lastMoveCol = j; 
                            countBlank++;
                        }  
                    }
                    if ((countCol == game.boardSize - 2) && (countBlank == 1))
                        return true;
                }
            }
        }
        return false; 
    }

    /** 
     * check if the left diagonal of the board is almost full, that is all the moves in a the diagonal but one have been played by the same user,
    either the cpu or human. update the lastMoveRow and lastMoveCol which is the position of cpu's next move
    (cpu's next move will occupy the only blank position left in the diagonal).
     * @return true if the left diagonal is almost full, that is one of the user almost wins; false otherwise.
     */
    public boolean checkLeftDiagonalAlmostFull() {
        int countLeftDiag = 0; 
        int countBlank = 0; 
        if (game.board[0][0] == BLANK) { //  check when first index is empty, and the remaining indice are of same type
            for (int i=1; i< game.boardSize; i++)
                if (game.board[0][0] != game.board[i][i]) 
                    countLeftDiag++; 

            if (countLeftDiag == game.boardSize - 1) 
            {
                lastMoveRow = 0;
                lastMoveCol = 0; 
                return true; 
            }
        }
        else // check when left diag only has 1 empty index, which is not the first one, and the remaining of indice of same type
        {
            for (int i=1; i< game.boardSize; i++)
            {            
                if (game.board[0][0] == game.board[i][i]) {
                    countLeftDiag++; 
                }
                if (game.board[i][i] == BLANK)
                { 
                    lastMoveRow = i; 
                    lastMoveCol = i; 
                    countBlank++;
                }
            }
            if ((countLeftDiag == game.boardSize - 2) && countBlank == 1)
                return true; 
        } 
        
        return false;
    }

    /** 
     * check if the right diagonal of the board is almost full, that is all the moves in a the diagonal but one have been played by the same user,
    either the cpu or human. update the lastMoveRow and lastMoveCol which is the position of cpu's next move
    (cpu's next move will occupy the only blank position left in the diagonal).
     * @return true if the right diagonal is almost full, that is one of the user almost wins; false otherwise.
     */
    public boolean checkRightDiagonalAlmostFull() {
        int countRightDiag = 0; 
        int countBlank = 0; 
        if (game.board[game.boardSize-1][0] == BLANK) {
            for (int i= game.boardSize -2 ; i>=0; i--)
            {
                if (game.board[game.boardSize-1][0] != game.board[i][game.boardSize - 1 - i])
                {
                    lastMoveRow = game.boardSize - 1; 
                    lastMoveCol = 0; 
                    countRightDiag++;
                }
            }
            if (countRightDiag == game.boardSize - 1) 
                return true; 
        }
        else {
            for (int i= game.boardSize -2 ; i>=0; i--)
            {
                if (game.board[game.boardSize-1][0] == game.board[i][game.boardSize - 1 - i]) {
                    countRightDiag++;  
                }
                if (game.board[i][game.boardSize - 1 - i] == BLANK)
                {
                    lastMoveRow = i;
                    lastMoveCol = game.boardSize - 1 - i;
                    countBlank++; 
                }
            }
            if ((countRightDiag == game.boardSize - 2) && (countBlank == 1))
                return true; 
        } 

        return false; 
    }

    /**
     * make the cpu's next move strategy. in general, the strategy is to generate the cpu's next move in 
    one of the empty positions around the cpu's current move. if either the row or column or diagonal 
    has been occupied with all the same move but one then the cpu's next move would be in that only 
    blank position to prevent human player from winning or to make cpu win.
     * @param ticMove the cpu's current move
     * @return the cpu's next move based on the cpu's current move. 
     */
    public TicMove pickNextMove(TicMove ticMove)
    {
        int row; 
        int col;
        int currentRow = ticMove.row;
        int currentCol = ticMove.col;
        TicMove cpuNextMove; 
        do {
            // if either the row or column or diagonal has been occupied with all the same move but one
            // then the cpu's next move would be in that only blank position to prevent human player from winning
            // or to make cpu win.
            if (checkRowAlmostFull() || checkColAlmostFull() || checkLeftDiagonalAlmostFull() || checkRightDiagonalAlmostFull())
            {
                row = lastMoveRow;
                col = lastMoveCol;
            }
            // in general, the cpu's next move would be one in one of the positions around cpu's current move.
            else {
                switch((int) (8 * Math.random())) {
                    case 0: 
                    row = currentRow + 1;
                    col = currentCol; 
                    break;
                    case 1: 
                    row = currentRow + 1; 
                    col = currentCol + 1;
                    break;
                    case 2: 
                    row = currentRow + 1;
                    col = currentCol - 1; 
                    break;
                    case 3: 
                    row = currentRow - 1; 
                    col = currentCol + 1; 
                    break; 
                    case 4: 
                    row = currentRow - 1; 
                    col = currentCol - 1; 
                    break; 
                    case 5: 
                    row = currentRow - 1; 
                    col = currentCol; 
                    break; 
                    case 6: 
                    row = currentRow; 
                    col = currentCol + 1;
                    break;
                    default: 
                    row = currentRow; 
                    col = currentCol - 1;
                    break; 
                }
            }
            cpuNextMove = new TicMove(row, col); 
        }
        while (!game.isValidMove(cpuNextMove));

        char charRow = (char) (cpuNextMove.row + 65);
        int charCol = (cpuNextMove.col + 1); 

        System.out.println("CPU's move: " + charRow + ""+ charCol); 

        return cpuNextMove;
    }

    /**
     * make the cpu's first move, cpu's first move is either in a corner of the game board or in the center 
       of the board.
     * @return the cpu's first move with the above strategy.
     */
    public TicMove pickFirstMove()
    {
        int size = game.getBoardSize();
        int row; 
        int col;
        TicMove cpuFirstMove;
        do {
            // if board's size is odd, play the cpu's first move in the center of the board. 
            // if the center position has been played by the human, generate cpu's first move in one of the corners
            if (size % 2 == 1) {
                if (game.board[size/2][size/2] == ' ') {
                    row = size/2; 
                    col = size/2;
                }
                else // in case the user first move is in the center
                {
                    switch ((int) (4 * Math.random())) {
                        case 0: 
                        row = 0; 
                        col = 0; 
                        break; 
                        case 1: 
                        row = size-1; 
                        col = 0; 
                        break; 
                        case 2: 
                        row = 0; 
                        col = size-1; 
                        break;
                        default: 
                        row = size-1; 
                        col = size-1;
                        break;
                    }
                }
            }
            // if the game board's size is even, then generate cpu's first move in one of the corners. 
            else 
            {
                switch ((int) (4 * Math.random())) {
                    case 0: 
                    row = 0; 
                    col = 0; 
                    break; 
                    case 1: 
                    row = size-1; 
                    col = 0; 
                    break; 
                    case 2: 
                    row = 0; 
                    col = size-1; 
                    break;
                    default: 
                    row = size-1; 
                    col = size-1;
                    break;
                }
            }
            cpuFirstMove = new TicMove(row, col); 
        } 

        while (!game.isValidMove(cpuFirstMove));
        char charRow = (char) (cpuFirstMove.row + 65);
        int charCol = (cpuFirstMove.col + 1); 

        System.out.println("CPU's move: " + charRow + ""+ charCol); 

        return cpuFirstMove;
    }

    /**
     * check if the cpu's move is surrounded, that is all the moves around it have been played
     * @param ticMove the cpu's current move.
     * @return true if the cpu's move is surrounded, false otherwise.
     */
    public boolean checkSurrounded(TicMove ticMove) 
    {
        int row = ticMove.row; 
        int col = ticMove.col;
        boolean x; 
        if ((row == 0) && (col == 0)) 
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col+1] != BLANK) 
            && (game.board[row][col+1] != BLANK); 
        else if ((row == game.boardSize -1) && (col == game.boardSize -1))
            x = (game.board[row-1][col-1] != BLANK) && (game.board[row-1][col] != BLANK)
            && (game.board[row][col-1] != BLANK);
        else if ((row == game.boardSize -1) && (col ==0))
            x = (game.board[row-1][col+1] != BLANK) && (game.board[row-1][col] != BLANK)
            && (game.board[row][col+1] != BLANK);
        else if ((row == 0) && (col == game.boardSize -1))
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col-1] != BLANK)
            && (game.board[row][col-1] != BLANK); 
        else if (row == game.boardSize -1 && col > 0) 

            x = (game.board[row-1][col+1] != BLANK) && (game.board[row-1][col-1] != BLANK) 
            && (game.board[row-1][col] != BLANK) && (game.board[row][col+1] != BLANK)
            && (game.board[row][col-1] != BLANK);  
        else if (col == game.boardSize -1 && row > 0) 
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col-1] != BLANK) 
            && (game.board[row-1][col-1] != BLANK) && (game.board[row-1][col] != BLANK)
            && (game.board[row][col-1] != BLANK); 

        else if (row == 0 && col < (game.boardSize -1)) 
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col+1] != BLANK) 
            && (game.board[row+1][col-1] != BLANK) && (game.board[row][col+1] != BLANK)
            && (game.board[row][col-1] != BLANK); 
        else if (col == 0 && row < (game.boardSize -1)) 
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col+1] != BLANK) 
            && (game.board[row-1][col+1] != BLANK) && (game.board[row-1][col] != BLANK) 
            && (game.board[row][col+1] != BLANK); 
        else
            x = (game.board[row+1][col] != BLANK) && (game.board[row+1][col+1] != BLANK) 
            && (game.board[row+1][col-1] != BLANK) && (game.board[row-1][col+1] != BLANK) && 
            (game.board[row-1][col-1] != BLANK) && (game.board[row-1][col] != BLANK) &&
            (game.board[row][col+1] != BLANK) && (game.board[row][col-1] != BLANK); 
        return x; 
    }

    /**
     * return a symbol representing the move of cpu player.
     * @return character 'O' for cpu player.
     */
    public char getSymbol()
    {
        return 'O'; 
    }

}
