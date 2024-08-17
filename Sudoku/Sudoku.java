import java.util.Scanner;

class Sudoku {
    public void solveSudoku(char[][] board) {
        if (helper(board, 0, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    public boolean isSafe(char[][] board, int row, int col, int num) {
        // Check row
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == (char)(num + '0')) {
                return false;
            }
        }
        // Check column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char)(num + '0')) {
                return false;
            }
        }
        // Check 3x3 grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char)(num + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }
        int nrow, ncol;
        if (col == board.length - 1) {
            nrow = row + 1;
            ncol = 0;
        } else {
            nrow = row;
            ncol = col + 1;
        }

        if (board[row][col] != '.') {
            return helper(board, nrow, ncol);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char)(i + '0');
                    if (helper(board, nrow, ncol)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
    }

    public void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0 && j < board[i].length - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i < board.length - 1) {
                System.out.println("---------------------");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[9][9];

        System.out.println("Enter the Sudoku puzzle row by row:");
        System.out.println("Use '.' for empty cells.");

        for (int i = 0; i < 9; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        Sudoku solver = new Sudoku();
        solver.solveSudoku(board);
    }
}
