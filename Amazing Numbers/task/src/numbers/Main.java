package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    // write your code here
        int number;
        String evenOrOdd;
        boolean endsWith7;
        boolean divisibleBySeven;
        String explanation;
        String result;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");

        number = scanner.nextInt();

        if (number < 1) {
            System.out.println("This number is not natural!");
        }

        else {
            // Check if even or not
            evenOrOdd = (number % 2 == 0) ? "Even" : "Odd";

            System.out.println("This number is ".concat(evenOrOdd).concat("."));

            divisibleBySeven = number % 7 == 0;

            endsWith7 = (number < 10) ? (number == 7) : ((number - 7) % 10 == 0);

            if (divisibleBySeven && endsWith7) {
                result = "It is a Buzz number.";
                explanation = number + " is divisible by 7 and ends with 7.";

            } else if (!divisibleBySeven && endsWith7) {
                result = "It is a Buzz number.";
                explanation = number + " ends with 7.";

            } else if (divisibleBySeven) {
                result = "It is a Buzz number.";
                explanation = number + " is divisible by 7.";

            } else {
                result = "It is not a Buzz number.";
                explanation = number + " is neither divisible by 7 nor does it end with 7.";
            }

            System.out.println(result.concat("\nExplanation:\n").concat(explanation));
        }

    }
}
