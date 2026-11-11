
import java.util.Scanner;

public class SAQ1 {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter Original Phrase: ");
        String input = keyboard.nextLine();
        System.out.println();

        boolean control = true;
        int n = 0;
        int shift = 0;

        // 1st Loop: Calculate the shift key

        while (control) {

            if (n >= input.length()) { //or using (n > input.length -1)
                control = false;

            } else {
                char c = input.charAt(n);

                if (Character.isDigit(c)) {
                    int digit = Character.getNumericValue(c); //Change from char to int(digit)
                    shift += digit;
                }

                n++;
            }
        }

        shift = shift % 26; //make sure shift not overstep the 26 alphabet

        if (shift == 0) {
            shift = 13; //as question mentioned if 0 then the shift be 13
        }

        System.out.println("Shift Key: " + shift);
        System.out.println();

        // 2nd loop: Encode characters

        n = 0;
        control = true;

        String encoded = "";

        char[] alpha = {                    //can using ASCII table too (pronunciation:"ASS-kee")
            'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u',
            'v','w','x','y','z'
        };

        char[] symbols = {'!', '@', '#', '$', '%'}; //checking for sysmbols

        while (control) {

            if (n >= input.length()) {
                control = false;

            } else {
                char c = input.charAt(n);

                // 1. Lowercase letters
                if (c >= 'a' && c <= 'z') { //Character.isLowerCase(c)

                    for (int i = 0; i < alpha.length; i++) { //Try to matching char in alpha array

                        if (alpha[i] == c) {
                            int newPosition = (i + shift) % 26; //shift count in 1st loop
                            char newLetter = alpha[newPosition];

                            encoded += newLetter;
                            break;
                        }
                    }

                // 2. Uppercase letters
                } else if (c >= 'A' && c <= 'Z') { //Character.isUpperCase(c)

                    if (c == 'A' || c == 'E' || c == 'I' ||
                        c == 'O' || c == 'U') {

                        char lower = Character.toLowerCase(c);
                        encoded += "*";
                        encoded += lower;

                    } else {

                        char lower = Character.toLowerCase(c);

                        for (int i = 0; i < alpha.length; i++) {

                            if (alpha[i] == lower) {
                                int newPosition = (i + 1) % 26;
                                char newLetter = alpha[newPosition];

                                encoded += newLetter;
                                break;
                            }
                        }
                    }

                // 3. Digits
                } else if (c >= '0' && c <= '9') { //Character.isDigit(c)

                    int digit = Character.getNumericValue(c);
                    int newDigit = (digit * digit) % 10; //question mentioned

                    encoded += newDigit;

                // 4. Spaces
                } else if (c == ' ') {

                    encoded += ".";

                // 5. Symbols
                } else if (c == '!' || c == '@' ||
                           c == '#' || c == '$' || c == '%') {

                    for (int i = 0; i < symbols.length; i++) {

                        if (symbols[i] == c) {
                            int newPosition = (i + 2) % 5;
                            char newSymbol = symbols[newPosition];

                            encoded += newSymbol;
                            break;
                        }
                    }

                // 6. Other characters
                } else {

                    encoded += c; //not mentioned in question,just back up
                }

                n++;
            }
        }

        System.out.println("Before Reversal: " + encoded);
        System.out.println();

        // 3rd Stage: Reverse the string

        String reversed = "";

        int i = encoded.length() - 1;

        while (i >= 0) {

            reversed += encoded.charAt(i);

            i--;
        }

        System.out.println("Encoded Password: " + reversed);

        keyboard.close();
    }
}
