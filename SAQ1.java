
import java.util.Scanner;

public class Bq1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Original Message: ");
        String original = input.nextLine();

        String encoded = "";
        String symbols = "+-*/=";

        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);

            if (c >= 'a' && c <= 'z') {
                int shift = (i % 5) + 1;
                char newLetter = (char) ('a' + (c - 'a' + shift) % 26);
                encoded += newLetter;

            } else if (c >= 'A' && c <= 'Z') {
                char mirror = (char) ('z' - (c - 'A'));
                encoded += "<" + mirror + ">";

            } else if (c >= '0' && c <= '9') {
                int count = 1;

                while (i + 1 < original.length()
                        && original.charAt(i + 1) == c) {
                    count++;
                    i++;
                }

                int replacement = 9 - (c - '0');
                encoded += replacement;

                if (count > 1) {
                    encoded += "x" + count;
                }

            } else if (c == ' ') {
                encoded += "_";

                while (i + 1 < original.length() && original.charAt(i + 1) == ' ') {
                    i++;
                }

            } else {
                boolean found = false;

                for (int j = 0; j < symbols.length(); j++) {
                    if (symbols.charAt(j) == c) {
                        int previous = (j + 4) % 5;
                        encoded += symbols.charAt(previous);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    encoded += c;
                }
            }
        }

        System.out.println("Encoded Message: " + encoded);
        System.out.println("Original Length: " + original.length()
                + " | Encoded Length: " + encoded.length());

        input.close();
    }
}
