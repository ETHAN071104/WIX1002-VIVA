import java.util.Scanner;

public class Viva_Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N, K;
        boolean validN, validK;

        // Input validation
        do {
            System.out.print("Enter size N: ");
            N = input.nextInt();

            System.out.print("Enter band thickness K: ");
            K = input.nextInt();

            validN = N >= 5 && N <= 25 && N % 2 != 0;
            validK = K >= 1 && K <= 3 && K <= N / 2;

            if (!validN) {
                System.out.println(
                    "Error: N must be an odd number between 5 and 25."
                );
            }

            if (!validK) {
                System.out.println(
                    "Error: K must be between 1 and 3 and not more than N / 2."
                );
            }

        } while (!validN || !validK);

        // New line
        System.out.println();

        // Find the centre
        int mid = N / 2;

        // Print the diamond pattern
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                int d = Math.abs(r - mid) + Math.abs(c - mid);
                char symbol;

                if (d == 0) {
                    symbol = '@';

                } else if (d <= mid) {
                    int band = (d / K) % 3;

                    if (band == 0) {
                        symbol = '#';
                    } else if (band == 1) {
                        symbol = '+';
                    } else {
                        symbol = 'o';
                    }

                } else if (r < mid && c < mid) {
                    symbol = '/';

                } else if (r < mid && c > mid) {
                    symbol = '\\';

                } else if (r > mid && c < mid) {
                    symbol = '\\';

                } else {
                    symbol = '/';
                }

                System.out.print(symbol);
            }

            System.out.println();
        }
    }
}
