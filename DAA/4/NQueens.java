import java.util.Scanner;

public class NQueens {

    static int N; 
    static int[][] board;

    
    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

   
    static boolean isSafe(int row, int col) {
        int i, j;

        
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

       
        for (i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    
    static boolean solveNQ(int row) {
        
        if (row >= N)
            return true;

        
        boolean alreadyPlaced = false;
        for (int j = 0; j < N; j++) {
            if (board[row][j] == 1) {
                alreadyPlaced = true;
                break;
            }
        }
        if (alreadyPlaced)
            return solveNQ(row + 1);

        
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solveNQ(row + 1))
                    return true;
                board[row][col] = 0; 
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Queens (N): ");
        N = sc.nextInt();

        board = new int[N][N];

        System.out.print("Enter the position of the first Queen (row and column, 1-based index): ");
        int row = sc.nextInt() - 1;
        int col = sc.nextInt() - 1;

      
        board[row][col] = 1;

        if (!solveNQ(0))
            System.out.println("Solution does not exist");
        else {
            System.out.println("\nFinal N-Queens Matrix:");
            printBoard();
        }

        sc.close();
    }
}
