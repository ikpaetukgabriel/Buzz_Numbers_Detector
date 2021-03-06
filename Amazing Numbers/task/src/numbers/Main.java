package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Checks to see if the String can be parsed to Integer
    public static void welcomeMessage() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    // Checks to see if the String can be parsed to Integer
    public static boolean checkIfNumber(String num) {
        try
        {
            Long.parseLong(num);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    // Checks to see if numbers are good to be tested for their properties
    public static boolean isGoodToTest(String[] inputArray) {
        int count = 1;
        String[] invalidProperties = new String[2];

        /* When a single empty string is entered
          ERROR CODE -> 0
        */

        /*The first parameter should be a natural number or zero.
          ERROR CODE -> 1
        */

        /*The second parameter should be a natural number.
          ERROR CODE -> 2
        */

        /*The property [SUN] is wrong.
          Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
          ERROR CODE -> 3
        */

        /*The properties [HOT, SUN] are wrong.
          Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
          ERROR CODE -> 3
        */

        /*The request contains mutually exclusive properties: [ODD, EVEN]
         There are no numbers with these properties.
          ERROR CODE -> 4
        */

        int errorCode = -1;

        for (String memberInput : inputArray) {

            boolean isNumber = checkIfNumber(memberInput);
            if (count == 1 && "".equals(inputArray[0])) {
                errorCode = 0;
                break;
            }

            if (count == 1 && (!isNumber || Long.parseLong(memberInput) < 0)) {
                errorCode = 1;
                break;
            }


            if (count == 2 && (!isNumber || Long.parseLong(memberInput) <= 0)) {
                errorCode = 2;
                break;
            }

            if (count == 3 && !(acceptablePropertiesChecker(memberInput))) {
                invalidProperties[0] = memberInput.toUpperCase();
                errorCode = 3;
            }

            if (count == 4 && !(acceptablePropertiesChecker(memberInput))) {
                invalidProperties[1] = memberInput.toUpperCase();
                errorCode = 3;
                break;
            }
            count++;
        }

        // Checks if the two properties are mutually exclusive
        if (inputArray.length > 3 && mutualExclusiveChecker(inputArray[2], inputArray[3])){
            errorCode = 4;
        }

        if (errorCode >= 0) {
            if (errorCode == 0) {
                welcomeMessage();
            }

            else if (errorCode == 1) {
                System.out.println("The first parameter should be a natural number or zero.");
            }

            else if (errorCode == 2) {
                System.out.println("The second parameter should be a natural number.");
            }

            else if (errorCode == 3) {
                //Removes Null Value and empty string the String array
                invalidProperties = Arrays.stream(invalidProperties)
                        .filter(s -> (s != null && s.length() > 0))
                        .toArray(String[]::new);

                String propertiesPluralOrNot;
                String isOrAre;

                if (invalidProperties.length > 1) {
                    propertiesPluralOrNot = "properties";
                    isOrAre = "are";
                }
                else {
                    propertiesPluralOrNot = "property";
                    isOrAre = "is";
                }

                System.out.println("The " + propertiesPluralOrNot + " [" + String.join(", ", invalidProperties) + "] " + isOrAre + " wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
            }

            else {
                System.out.println("The request contains mutually exclusive properties: " +
                        "[" + inputArray[2].toUpperCase() + ", " + inputArray[3].toUpperCase() + "]");
                System.out.println("There are no numbers with these properties.");
            }

            return false;
        }

        else {
            return true;
        }
    }
    // Checks if the number
    // is an even number
    public static boolean evenChecker(long num) {
        boolean isEven;
        isEven = num % 2 == 0;
        return  isEven;
    }


    public static boolean buzzChecker(long number) {
        boolean isBuzz;
        // Checks if the number divisible by 7
        boolean divisibleBySeven = number % 7 == 0;

        // Checks if the number ends with 7
        boolean endsWith7 = (number < 10) ? (number == 7) : ((number - 7) % 10 == 0);

        // Checks if the number divisible by 7 and ends with 7
        if (divisibleBySeven && endsWith7) {
            isBuzz = true;

        } else // Checks if the number ends with 7
            if (!divisibleBySeven && endsWith7) {
                isBuzz = true;


            } //Checks if the number divisible by 7
            else isBuzz = divisibleBySeven;

        return  isBuzz;
    }

    /* Checks if the number
    is a duck number */
    public static boolean duckChecker(long number) {
        String numberString = Long.toString(number);
        int numberStringLength = numberString.length();
        boolean isDuck = false;

        for (int i = 0; i < numberStringLength; i ++) {
            char currentDigit = numberString.charAt(i);

            if (currentDigit == '0') {
                isDuck = true;
                break;
            }

        }

        return isDuck;
    }

    /* Checks if the number
    is a Palindromic number */
    public static boolean palindromicChecker(long number) {
        StringBuilder reversedNumber = new StringBuilder();

        String numberString = Long.toString(number);
        int numberStringLength = numberString.length();

        for (int i = numberStringLength; i > 0 ; i --) {
            reversedNumber.append(numberString.charAt(i - 1));
        }

        return reversedNumber.toString().equals(numberString);
    }

    // Checks if the number
    // is gapful number
    public static boolean gapfulChecker(long num) {
        boolean isGapful = false;
        String numberString = Long.toString(num);
        if (numberString.length() >= 3) {
            char first = numberString.charAt(0);
            char last = numberString.charAt(numberString.length() - 1);
            String firstAndLast = Character.toString(first) + last;

            if(num % Long.parseLong(firstAndLast) == 0) {
                isGapful = true;
            }
        }
        return isGapful;
    }


    // Checks if the number
    // is an spy number
    public static boolean spyChecker(long num) {
        String numberString = Long.toString(num);
        int sumOfDigit = 0;
        int productOfDigit = 1;

        // Loops through each digit in the number
        for (int i = 0; i < numberString.length(); i ++) {
            // Converts the character to digit and increments the sum
            sumOfDigit += Character.getNumericValue(numberString.charAt(i));
            // Converts the character to digit and multiplies the product by the digit
            productOfDigit *= Character.getNumericValue(numberString.charAt(i));
        }

        // Checks if the product of the digit equals the sum of the digit
        return (sumOfDigit == productOfDigit);
    }

    // Checks if the number is
    // a perfect square
    public static boolean perfectSquareChecker(long num) {
        // finding the square root of given number
        double squareRoot = Math.sqrt(num);

        /* Math.floor() returns closest integer value, for
         * example Math.floor of 984.1 is 984, so if the value
         * of sq is non integer than the below expression would
         * be non-zero.
         */
        return ((squareRoot - Math.floor(squareRoot)) == 0);
    }


    // Checks if the number is
    // a sunny number
    public static boolean sunnyChecker(long num) {
        // Num is a sunny number if N+1 is a perfect square number.
        return perfectSquareChecker(num + 1);
    }

    // Adds commas to the number as appropriate.
    public static String commaAdder(long number) {

        StringBuilder numberWithComma = new StringBuilder();

        String numberString = Long.toString(number);
        int numberStringLength = numberString.length();


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
        return numberWithComma.toString();
    }


    // Checks for valid number properties
    public static boolean acceptablePropertiesChecker(String property) {
        boolean isAcceptable;
        property = property.toUpperCase();
        switch (property) {
            case "BUZZ"         :
            case "DUCK"         :
            case "PALINDROMIC"  :
            case "GAPFUL"       :
            case "SPY"          :
            case "EVEN"         :
            case "ODD"          :
            case "SUNNY"        :
            case "SQUARE"       :
                isAcceptable = true;
                break;
            default: isAcceptable = false;
        }
        return isAcceptable;
    }


    // Displays the true or false output for a specific number properties
    public static void trueOrFalsePropertyDisplay(String[] inputArray) {
        long number = Long.parseLong(inputArray[0]);

        boolean isEven = evenChecker(number);
        boolean isOdd = !isEven;
        boolean isBuzz = buzzChecker(number);
        boolean isDuck = duckChecker(number);
        boolean isPalindromic = palindromicChecker(number);
        boolean isGapful = gapfulChecker(number);
        boolean isSpy = spyChecker(number);
        boolean isSunny = sunnyChecker(number);
        boolean isSquare = perfectSquareChecker(number);

        String numberWithComma = commaAdder(number);

        System.out.println("Properties of " + numberWithComma);
        System.out.println("        buzz: " + isBuzz);
        System.out.println("        duck: " + isDuck);
        System.out.println(" palindromic: " + isPalindromic);
        System.out.println("      gapful: " + isGapful);
        System.out.println("         spy: " + isSpy);
        System.out.println("      square: " + isSquare);
        System.out.println("       sunny: " + isSunny);
        System.out.println("        even: " + isEven);
        System.out.println("        odd : " + isOdd);
    }


    public static void propertyListDisplay(ArrayList<String> tempArrayProperties, long number) {
        // Loops through the array of the number property characteristics
        // and prints out the number property characteristics
        System.out.print("\t\t\t" + number + " is ");
        for (int j = 0; j < tempArrayProperties.size(); j++) {
            System.out.print(tempArrayProperties.get(j));

            // Only adds comma if it is not the last property
            if ((tempArrayProperties.size() - j) != 1) {
                System.out.print(", ");
            }

            else{
                System.out.print("");
            }
        }
        System.out.println();

    }

    // Checks if two properties are mutually exclusive
    public static boolean mutualExclusiveChecker(String firstProp, String secondProp) {
        boolean areMutuallyExclusive = false;
        ArrayList<ArrayList<String>> listOfMutuallyExclusive = new ArrayList<>();

        ArrayList<String> evenAndOdd = new ArrayList<>();
        evenAndOdd.add("EVEN");
        evenAndOdd.add("ODD");

        ArrayList<String>  duckAndSpy = new ArrayList<>();
        duckAndSpy.add("DUCK");
        duckAndSpy.add("SPY");

        ArrayList<String>  sunnyAndSquare = new ArrayList<>();
        sunnyAndSquare.add("SUNNY");
        sunnyAndSquare.add("SQUARE");

        listOfMutuallyExclusive.add(evenAndOdd);
        listOfMutuallyExclusive.add(duckAndSpy);
        listOfMutuallyExclusive.add(sunnyAndSquare);

        for (ArrayList<String> pair : listOfMutuallyExclusive) {
            if (pair.contains(firstProp.toUpperCase()) && pair.contains(secondProp.toUpperCase())) {
                areMutuallyExclusive = true;
                break;
            }
        }
        return areMutuallyExclusive;
    }

    // Checks the property and appends the name to the
    // array containing the number property characteristics
    public static ArrayList<String> propertyChecker(long number) {
        ArrayList<String> tempArray = new ArrayList<>();

        boolean isBuzz = buzzChecker(number);
        if (isBuzz) tempArray.add("buzz");

        boolean isDuck = duckChecker(number);
        if (isDuck) tempArray.add("duck");

        boolean isPalindromic = palindromicChecker(number);
        if (isPalindromic) tempArray.add("palindromic");

        boolean isGapful = gapfulChecker(number);
        if (isGapful) tempArray.add("gapful");

        boolean isSpy = spyChecker(number);
        if (isSpy) tempArray.add("spy");

        boolean isSquare = perfectSquareChecker(number);
        if (isSquare) tempArray.add("square");

        boolean isSunny = sunnyChecker(number);
        if (isSunny) tempArray.add("sunny");

        boolean isEven = evenChecker(number);
        if (isEven) tempArray.add("even");

        boolean isOdd = !isEven;
        if (isOdd) tempArray.add("odd");

        return tempArray;
    }


    public static void main(String[] args) {
        // write your code here
        long number;
        boolean isReadyToCheck;

        String inputString;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        // Prints the welcome message
        welcomeMessage();

        // Loops until 0 is entered as the first input.
        while(true) {
            System.out.println();
            // Gets the input requests
            System.out.print("Enter a request : ");
            inputString = scanner.nextLine();
            System.out.println();

            // Splits the input into an array, uses space
            String[] inputArray = inputString.split(" ");

            isReadyToCheck = isGoodToTest(inputArray);

            // If the user inputs passes the necessary requirements in the
            // isGoodToTest method.
            if (isReadyToCheck) {

                // Quits if the first input is zero
                if (Long.parseLong(inputArray[0]) == 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                // For one number, calculate and print the properties
                // of the number in true or false format
                else if (inputArray.length == 1) {
                    // Displays properties in a true or false format
                    trueOrFalsePropertyDisplay(inputArray);
                }

                // For two numbers, print the list of
                // numbers with their properties;
                else if (inputArray.length == 2) {
                    // Displays number properties in a list format
                    for (int i = 0; i < Long.parseLong(inputArray[1]); i++ ) {
                        ArrayList<String> tempArray;
                        number = Long.parseLong(inputArray[0]) + i;

                        // Checks the property and appends the name to the
                        // array containing the number property characteristics
                        tempArray = propertyChecker(number);
                        propertyListDisplay(tempArray, number);

                    }

                }

                // For two numbers and one property,
                // print the numbers with this property only;
                else if (inputArray.length == 3) {
                    long requiredNumber = Long.parseLong(inputArray[1]);
                    long count = 0;
                    long reqNumberCounter = 0;
                    String neededProp1 = inputArray[2].toLowerCase();

                    while (reqNumberCounter < requiredNumber) {
                        ArrayList<String> numPropertiesArray;

                        long tempNum = Long.parseLong(inputArray[0]) + count;

                        numPropertiesArray = propertyChecker(tempNum);

                        if (numPropertiesArray.contains(neededProp1)) {
                            // Checks the property and appends the name to the
                            // array containing the number property characteristics
                            propertyListDisplay(numPropertiesArray, tempNum);
                            reqNumberCounter++;
                        }

                        count++;
                    }
                }

                // For two numbers and two properties, print
                // the numbers that have both properties.
                else {

                    long requiredNumber = Long.parseLong(inputArray[1]);
                    long count = 0;
                    long reqNumberCounter = 0;
                    String neededProp1 = inputArray[2].toLowerCase();
                    String neededProp2 = inputArray[3].toLowerCase();


                    while (reqNumberCounter < requiredNumber) {
                        ArrayList<String> numPropertiesArray;

                        long tempNum = Long.parseLong(inputArray[0]) + count;

                        numPropertiesArray = propertyChecker(tempNum);

                        if (numPropertiesArray.contains(neededProp1) && numPropertiesArray.contains(neededProp2)) {
                            // Checks the property and appends the name to the
                            // array containing the number property characteristics
                            propertyListDisplay(numPropertiesArray, tempNum);
                            reqNumberCounter++;
                        }

                        count++;
                    }

                }

            }

        }

    }
}
