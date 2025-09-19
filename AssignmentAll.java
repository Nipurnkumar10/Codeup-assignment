/**
 * Assignment: 1
 * Author: Nipurn Kumar
 */

import java.util.Scanner;

public class AssignmentAll {

    // ---------- Helper Methods ----------
    public static boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }

    public static int toDigit(char character) {
        return character - '0';
    }

    public static int customSqrt(int number) {
        int result = 0;
        while ((result + 1) * (result + 1) <= number) {
            result++;
        }
        return result;
    }

    // ---------- Task 1 ----------
    public static String expandEncodedString(String inputString) {
        String expandedResult = "";
        int index = 0;

        while (index < inputString.length()) {
            char currentCharacter = inputString.charAt(index);

            if ((currentCharacter < 'a' || currentCharacter > 'z')) {
                index++;
                continue;
            }
            index++;

            int count = 0;
            boolean digitFound = false;
            while (index < inputString.length() && isDigit(inputString.charAt(index))) {
                digitFound = true;
                count = count * 10 + toDigit(inputString.charAt(index));
                index++;
            }
            if (!digitFound) {
                count = 1;
            }

            for (int repeat = 0; repeat < count; repeat++) {
                expandedResult = expandedResult + currentCharacter;
            }
        }
        return expandedResult;
    }

    // ---------- Task 2 ----------
    public static String compressRuns(String inputString) {
        String compressedResult = "";
        int index = 0;

        while (index < inputString.length()) {
            char currentCharacter = inputString.charAt(index);
            int count = 1;
            index++;

            while (index < inputString.length() && inputString.charAt(index) == currentCharacter) {
                count++;
                index++;
            }
            compressedResult = compressedResult + currentCharacter + count;
        }
        return compressedResult;
    }

    // ---------- Task 3 ----------
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0) return false;

        int limit = customSqrt(number);
        int divisor = 3;

        while (divisor <= limit) {
            if (number % divisor == 0) return false;
            divisor += 2;
        }
        return true;
    }

    // ---------- Task 4 ----------
    private static final String[] belowTwenty = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen"
    };
    private static final String[] tensArray = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static String numberToWords(int number) {
        if (number <= 0 || number > 1000) {
            return "invalid input";
        }
        if (number == 1000) {
            return "one thousand";
        }

        String wordsResult = "";

        if (number >= 100) {
            int hundreds = number / 100;
            wordsResult = wordsResult + belowTwenty[hundreds] + " hundred";
            number = number % 100;
            if (number != 0) wordsResult = wordsResult + " ";
        }

        if (number >= 20) {
            int tens = number / 10;
            wordsResult = wordsResult + tensArray[tens];
            int remainder = number % 10;
            if (remainder != 0) {
                wordsResult = wordsResult + " " + belowTwenty[remainder];
            }
        } else if (number > 0) {
            wordsResult = wordsResult + belowTwenty[number];
        }

        return wordsResult.trim();
    }

    // ---------- Task 5 ----------
    public static int lengthOfLongestSubstring(String inputString) {
        if (inputString == null) return 0;

        int[] lastSeenIndex = new int[256];
        for (int i = 0; i < lastSeenIndex.length; i++) {
            lastSeenIndex[i] = -1;
        }

        int maxLength = 0;
        int leftPointer = 0;

        for (int rightPointer = 0; rightPointer < inputString.length(); rightPointer++) {
            char currentCharacter = inputString.charAt(rightPointer);

            if (lastSeenIndex[currentCharacter] >= leftPointer) {
                leftPointer = lastSeenIndex[currentCharacter] + 1;
            }
            lastSeenIndex[currentCharacter] = rightPointer;

            int currentLength = rightPointer - leftPointer + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        return maxLength;
    }

    // ---------- Main Menu ----------
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Assignment Menu =====");
            System.out.println("1. Expand encoded string");
            System.out.println("2. Character frequency (run-length encoding)");
            System.out.println("3. Prime number checker");
            System.out.println("4. Number to words (1-1000)");
            System.out.println("5. Longest substring without repeating chars");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = userInput.nextInt();
            userInput.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter encoded string: ");
                    String encodedInput = userInput.nextLine();
                    System.out.println("Expanded: " + expandEncodedString(encodedInput));
                    break;

                case 2:
                    System.out.print("Enter string to compress: ");
                    String inputString = userInput.nextLine();
                    System.out.println("Compressed: " + compressRuns(inputString));
                    break;

                case 3:
                    System.out.print("Enter number: ");
                    int primeCandidate = userInput.nextInt();
                    if (isPrime(primeCandidate))
                        System.out.println("The given number is PRIME");
                    else
                        System.out.println("The given number is NOT prime");
                    break;

                case 4:
                    System.out.print("Enter number (1-1000): ");
                    int number = userInput.nextInt();
                    System.out.println("In words: " + numberToWords(number));
                    break;

                case 5:
                    System.out.print("Enter string: ");
                    String inputForSubstring = userInput.nextLine();
                    System.out.println("Length of longest substring: " +
                            lengthOfLongestSubstring(inputForSubstring));
                    break;

                case 0:
                    System.out.println("Exiting. Bye!");
                    userInput.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
