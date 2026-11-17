import java.util.Scanner;

public class SAQ2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double deposit;
        do {
            System.out.print("Enter monthly deposit (RM): ");
            deposit = input.nextDouble();
            if (deposit < 100 || deposit > 5000) {
                System.out.println("Error: Deposit must be between RM 100 and RM 5000.");
            }
        } while (deposit < 100 || deposit > 5000);

        System.out.print("Enter starting annual interest rate (%): ");
        double startingRate = input.nextDouble();
        System.out.print("Enter savings goal (RM): ");
        double goal = input.nextDouble();
       
        char option;
        do {
            System.out.print("Is there a withdrawal? (Y/N): ");
            option = input.next().charAt(0);
            if (option != 'Y' && option != 'y'
                    && option != 'N' && option != 'n') {
                System.out.println("Error: Please enter Y or N.");
            }
        } while (option != 'Y' && option != 'y'
                && option != 'N' && option != 'n');

        int withdrawalMonth = 0;
        double withdrawalAmount = 0;

        if (option == 'Y' || option == 'y') {
            do {
                System.out.print("Enter withdrawal month (1-36): ");
                withdrawalMonth = input.nextInt();
                if (withdrawalMonth < 1 || withdrawalMonth > 36) {
                    System.out.println("Error: Month must be between 1 and 36.");
                }
            } while (withdrawalMonth < 1 || withdrawalMonth > 36);

            do {
                System.out.print("Enter withdrawal amount (RM): ");
                withdrawalAmount = input.nextDouble();
                if (withdrawalAmount <= 0) {
                    System.out.println("Error: Withdrawal amount must be greater than 0.");
                }
            } while (withdrawalAmount <= 0);
        }
       
        double balance = 0;
        double totalDeposits = 0;
        double totalInterest = 0;
        boolean goalReached = false;
        
        for (int month = 1; month <= 36; month++) {
            int year = (month - 1) / 12 + 1;
            double annualRate = startingRate + (year - 1) * 0.5;
            double monthlyRate = annualRate / (100 * 12);

            balance = balance + deposit;
            totalDeposits = totalDeposits + deposit;
           
            if (month == withdrawalMonth) {
                double penalty = 0;
                if (month <= 12) {
                    penalty = withdrawalAmount * 0.02;
                }

                double totalWithdrawal = withdrawalAmount + penalty;
                if (totalWithdrawal > balance) {
                    System.out.println("Warning: Withdrawal exceeds balance! Balance reset to RM 0.00.");
                    balance = 0;
                } else {
                    balance = balance - totalWithdrawal;
                    if (penalty > 0) {
                        System.out.printf("Early withdrawal penalty charged: RM %.2f%n", penalty);
                    }
                }
            }
           
            double interest = balance * monthlyRate;
            balance = balance + interest;
            totalInterest = totalInterest + interest;
            
            if (!goalReached && balance >= goal) {
                System.out.println("Savings goal reached in Month " + month + "!");
                goalReached = true;
            }
           
            if (month % 12 == 0) {
                System.out.printf("End of Year %d Balance: RM %.2f (rate %.1f%%)%n",
                        year, balance, annualRate);
            }
        }
       
        System.out.printf("Total Deposits: RM %.2f%n", totalDeposits);
        System.out.printf("Total Interest Earned: RM %.2f%n", totalInterest);
        if (!goalReached) {
            System.out.println("Savings goal not reached within 36 months.");
        }
        input.close();
    }
}
