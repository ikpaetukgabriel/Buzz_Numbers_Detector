package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    // write your code here
        long number;
        boolean isOdd;
        boolean isEven;
        boolean endsWith7;
        boolean divisibleBySeven;
        boolean isBuzz;
        boolean isDuck;
        boolean isPalindromic;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");

        while(true) {

            System.out.println();
            System.out.print("Enter a request : ");
            number = scanner.nextLong();

            /* Checks if the number
            is a natural number */
            if (number > 0) {

                /* Checks if the number
                is an even number */
                isEven = number % 2 == 0;
                isOdd = !isEven;

                // Checks if the number divisible by 7
                divisibleBySeven = number % 7 == 0;

                // Checks if the number ends with 7
                endsWith7 = (number < 10) ? (number == 7) : ((number - 7) % 10 == 0);

                // Checks if the number divisible by 7 and ends with 7
                if (divisibleBySeven && endsWith7) {
                    isBuzz = true;

                } else // Checks if the number ends with 7
                    if (!divisibleBySeven && endsWith7) {
                        isBuzz = true;


                    } //Checks if the number divisible by 7
                    else isBuzz = divisibleBySeven;


                /* Checks if the number
                is a duck number */
                String numberString = Long.toString(number);
                int numberStringLength = numberString.length();
                isDuck = false;

                for (int i = 0; i < numberStringLength; i ++) {
                    char currentDigit = numberString.charAt(i);

                    if (currentDigit == '0') {
                        isDuck = true;
                        break;
                    }

                }

                /* Checks if the number
                is a Palindromic number */
                StringBuilder reversedNumber = new StringBuilder();

                for (int i = numberStringLength; i > 0 ; i --) {
                    reversedNumber.append(numberString.charAt(i - 1));
                }
                isPalindromic = reversedNumber.toString().equals(numberString);


                // Adds commas to the number as appropriate.
                StringBuilder numberWithComma = new StringBuilder();


                for (int i = numberStringLength; i > 0; i --) {

                    if ((numberStringLength == i)) {
                        numberWithComma.insert(0,numberString.charAt(i-1));
                    }

                    else if (((numberStringLength - i) % 3) == 0) {
                        numberWithComma.insert(0,',');
                        numberWithComma.insert(0,numberString.charAt(i-1));

                    }

                    else{
                        numberWithComma.insert(0,numberString.charAt(i-1));
                    }

                }

                System.out.println("Properties of " + numberWithComma);
                System.out.println("\t    even: " + isEven );
                System.out.println("\t    odd : " + isOdd );
                System.out.println("\t    buzz: " + isBuzz );
                System.out.println("\t    duck: " + isDuck );
                System.out.println(" palindromic: " + isPalindromic );

            }

            else if (number == 0) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("The first parameter should be a natural number or zero.");
            }
        }


    }
}
