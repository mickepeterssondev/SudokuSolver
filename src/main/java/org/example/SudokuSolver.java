package org.example;

public class SudokuSolver {

    public static void main(String[] args) {

        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)){
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }



    }

    public static boolean solveSudoku(int[][] board){
       //set up loop for 2D array, iterate through all rows and cols
        for (int row = 0; row < 9; row++){
           for (int col = 0; col < 9; col++){
               //set up action for if cell is 0
               if (board[row][col] == 0){
                   //testing 1-9 for cell
                   for(int num = 1; num <= 9; num++){
                       if (isSafe(board, row, col, num)){
                           //if check passed, set number
                           board[row][col] = num;
                           // funtion recursively calls itself to proceed with next cell
                           //if function returns false, remove number placed and try next one
                           if (solveSudoku(board)){
                               return true;
                           } else {
                               board[row][col] = 0; //if false, backtrack and reset to 0
                           }
                       }
                   }
                   return false;
               }
           }
       }
        //if the function fills cells without conflict, return true -> puzzle solved
        return true;
    }

    //check based on sudoku rules algorithm
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        //return false if any sudoku rule is broken
        //row check
        for (int d = 0; d < board.length; d++){
            if (board[row][d] == num) {
                return false;
            }
        }

        //col check
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        //sqrt of 9 represents the size of each dimension of the sub-grid
        int sqrt = 3;
        //calculate top left corner of the subgrid/box
        //nearest multiple of 3 is start of subgrid, row - remainder of modulo
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;


        //iterates over each cell in identified 3x3 sub-grid to check for defeater
        //Row and Col start will always indicate top left of a box, which allows us to
        //use sqrt of 9 to control vector
        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false; //if defeater found, return false
                }
            }
        }
        //if pass check, return true
        return true;
    }

    //print complete board
    public static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
