import java.util.Scanner;

public class TicTacToe {
    private static final int rows = 3;
    private static final int columns = 3;
    private static final String[][] board = {{"   ", "   ", "   "}, {"   ", "   ", "   "}, {"   ", "   ", "   "}};
    private static boolean player = false;

    private static boolean checkRow(int row, String playerSymbol) {
        for (String cell : board[row]) {
            if (!cell.equals(playerSymbol)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(int column, String playerSymbol) {
        for (String[] row : board) {
            if (!row[column].equals(playerSymbol)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonal(String playerSymbol) {
        int diagonal1Count = 0;
        int diagonal2Count = 0;
        for (int i = 0; i < rows; i++) {
            if (board[i][i].equals(playerSymbol)) {
                diagonal1Count++;
            }
            if (board[i][rows - 1 - i].equals(playerSymbol)) {
                diagonal2Count++;
            }
        }
        return diagonal1Count == rows || diagonal2Count == rows;
    }

    private static boolean checkWin(String playerSymbol) {
        for (int i = 0; i < rows; i++) {
            if (checkRow(i, playerSymbol) || checkColumn(i, playerSymbol)) {
                return true;
            }
        }
        return checkDiagonal(playerSymbol);
    }

    private static void printBoard() {
        System.out.println();
        for (String[] row : board) {
            System.out.println("-------------");
            System.out.print("|");
            for (String cell : row) {
                System.out.print(cell);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("-------------\n");
    }

    private static boolean isValidMove(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns && board[row][column].equals("   ");
    }

    private static void makeMove(int row, int column, String playerSymbol) {
        board[row][column] = playerSymbol;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            int row, column;

            while (true) {
                try {
                    System.out.print("Row: ");
                    row = Integer.parseInt(scanner.nextLine()) - 1;
                    System.out.print("Column: ");
                    column = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                if (isValidMove(row, column)) {
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            if (player) {
                makeMove(row, column, " O ");
            } else {
                makeMove(row, column, " X ");
            }

            if (checkWin(player ? " O " : " X ")) {
                printBoard();
                System.out.println("Player " + (player ? "2" : "1") + " wins!");
                break;
            }

            player = !player;
        }

        System.out.println("Thanks for playing!");
    }
}
