import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    //Declare X as starting player
    private static String player = "X";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            //When a new game beings the board is to be cleared
            clearBoard();
            //X goes first
            player = "X";

            while (true) {
                //Displays the board
                display();
                //Get the players move
                int[] move = getPlayerMove(in);
                int row = move[0];
                int col = move[1];

                row--;
                col--;

                //Checks to make sure the move is valid
                if (isValidMove(row, col)) {
                    board[row][col] = player;

                    //Check for win or tie
                    if (isWin(player)) {
                        display();
                        System.out.println("Player " + player + " Wins");
                        break;
                    } else if (isTie()) {
                        display();
                        System.out.println("Its a tie");
                        break;
                    }
                    player = (player.equals("X")) ? "0" : "X";
                } else {
                    System.out.println("Invalid move");
                }
            }


            //Prompt player to play again
            System.out.println("Do you want to play again? (yes/no)");
            String choice = in.next().toLowerCase();
            if (!choice.equals("yes")) {
                break;
            }
        }
        in.close();
    }

    private static void clearBoard() {
        //Make sure there is enough space for 'x' and 'O' with a space
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        //The TicTacToe game board that is displayed
        System.out.println("_____________");
        for (int i = 0; i < ROW; i++) {
            System.out.println(" ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("_____________");
        }
    }

    private static int[] getPlayerMove(Scanner in) {
        //Checks for the players move coordinate
        System.out.println("Player " + player + ", enter your move (row and column): ");
        int row = SafeInput.getRangedInt(in, "Enter row (1-3):", 1, 3);
        int col = SafeInput.getRangedInt(in, "Enter column (1-3):", 1, 3);
        return new int[]{row, col};
    }

    private static boolean isValidMove(int row, int col) {
        //Checks for valid move position
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        //Checks for a win of any sort
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        //Checks for a vertical win
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player) {
        //This checks for a Horizontal win
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player) {
        //This checks for a diagonal win
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }
    private static boolean isTie() {
        //This checks for a tie
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
