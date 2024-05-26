import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int totalRounds = 3;
        int attemptsLimit = 5;
        int totalScore = 0;
        int highScore = 0;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("Each round, you have " + attemptsLimit + " attempts to guess the number within a specified range.");
        System.out.println("You will play " + totalRounds + " rounds.");

        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("\nRound " + round);
            System.out.print("Enter the minimum number of the range: ");
            int newMinRange = scanner.nextInt();
            System.out.print("Enter the maximum number of the range: ");
            int newMaxRange = scanner.nextInt();

            if (newMinRange >= newMaxRange) {
                System.out.println("Invalid range. Minimum number must be less than maximum number. Skipping round...");
                continue;
            }

            minRange = newMinRange;
            maxRange = newMaxRange;

            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int score = playRound(randomNumber, attemptsLimit);
            totalScore += score;
            if (score > highScore) {
                highScore = score;
            }
            System.out.println("Your score for round " + round + " is: " + score);
            System.out.println("Current High Score: " + highScore);
        }

        System.out.println("\nGame over!");
        System.out.println("Your total score is: " + totalScore);
        scanner.close();
    }

    private static int playRound(int randomNumber, int attemptsLimit) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int attempts = 1; attempts <= attemptsLimit; attempts++) {
            System.out.println("\nAttempt " + attempts + "/" + attemptsLimit);
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number.");
                score = attemptsLimit - attempts + 1;
                break;
            } else if (guess < randomNumber) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }

            if (attempts == attemptsLimit) {
                System.out.println("Sorry, you have exhausted all attempts. The correct number was: " + randomNumber);
            }
        }

        return score;
    }
}
