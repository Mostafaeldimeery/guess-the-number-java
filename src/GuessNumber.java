
import java.util.*;

public class GuessNumber {

    // create nested enum for usage
    enum Difficulty {
        EASY(1, 50, 10),
        NORMAL(0, 100, 10),
        HARD(1, 1000, 12);

        final int lowBound;
        final int highBound;
        final int attemptsLeftThisRound;

        Difficulty(int lowBound, int highBound, int attemptsLeftThisRound) {
            this.lowBound = lowBound;
            this.highBound = highBound;
            this.attemptsLeftThisRound = attemptsLeftThisRound;
        }

        static Difficulty fromString(String difficulty) {

            if (difficulty == null)
                throw new IllegalArgumentException("null difficulty");

            if (difficulty.equals("e"))
                return Difficulty.EASY;
            if (difficulty.equals("n"))
                return Difficulty.NORMAL;
            if (difficulty.equals("h"))
                return Difficulty.HARD;

            throw new IllegalArgumentException("unknown difficulty " + difficulty);
        }
    }

    public static void main(String[] args) {

        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int bestScore = 2000;

        // outer loop
        while (playAgain) {

            System.out.println("choose the difficulty, easy (e), normal (n) or hard (h)");
            Difficulty diff;
            try {
                diff = Difficulty.fromString(scanner.next().trim().toLowerCase());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage() + "defualting to normal");
                diff = Difficulty.NORMAL;
            }

            Set<Integer> previousValidGuesses = new HashSet<>();
            int guesses = 0;
            int currentGuess = -1;
            int lowBound = diff.lowBound;
            int highBound = diff.highBound;
            int attemptsLeftThisRound = diff.attemptsLeftThisRound;
            System.out.println("The game is simple. Guess the number, which is from " + diff.lowBound + " - "
                    + diff.highBound + " you have only " + diff.attemptsLeftThisRound
                    + " attempts this round, lets see if you can win.");
            System.out.println("Enter a number, Guess #" + (guesses + 1));
            int secret = random.nextInt(lowBound, highBound + 1);

            // inner loop
            do {
                try {

                    currentGuess = scanner.nextInt();

                    if (currentGuess < diff.lowBound || currentGuess > diff.highBound) {
                        System.out.println("Out of Range (" + lowBound + " - " + highBound + ") , try again");
                        System.out.println("guess #" + (guesses + 1) + " Attempts left: " + attemptsLeftThisRound);

                    } else if (previousValidGuesses.contains(currentGuess)) {
                        System.out.println("you already tried the number " + currentGuess + " pick another number.\n ");
                        System.out.println("guess #" + (guesses + 1) + " Attempts left: " + attemptsLeftThisRound);

                    } else if (currentGuess > secret) {
                        previousValidGuesses.add(currentGuess);
                        guesses++;
                        attemptsLeftThisRound--;

                        if (maxReached(attemptsLeftThisRound, secret))
                            break;

                        System.out.println(
                                "too high, guess #" + (guesses + 1) + " Attempts left: " + attemptsLeftThisRound);

                    } else if (currentGuess < secret) {
                        previousValidGuesses.add(currentGuess);
                        guesses++;
                        attemptsLeftThisRound--;
                        if (maxReached(attemptsLeftThisRound, secret))
                            break;

                        System.out.println("too low, guess #" + (guesses + 1) + " and the Attempts left: "
                                + attemptsLeftThisRound);

                    } else if (currentGuess == secret) {
                        previousValidGuesses.add(currentGuess);
                        guesses++;
                        if (guesses < bestScore) {
                            bestScore = guesses;
                        }
                        System.out.println(
                                "YOU WIN! ->" + " Number of guesses = " + guesses + " your bestscore is: " + bestScore);
                        break;

                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please enter a whole number (" + lowBound + " - " + highBound + ")");
                    scanner.nextLine();

                }

            } while (currentGuess != secret);

            playAgain = askPlayAgian(scanner);
        }
    }

    // end of the main method
    // helper method for counting attempts
    private static boolean maxReached(int attemptsLeft, int secret) {
        if (attemptsLeft == 0) {
            System.out.println("You Lost, no more attempts left the number was: " + secret);

            return true;
        }
        return false;
    }

    private static boolean askPlayAgian(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to play again? (yes/no)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("yes"))
                return true;
            if (answer.equalsIgnoreCase("no")) {
                System.out.println("thank you for playing my game!");
                return false;
            }
            System.out.println("Invalid input, enter (yes/no) (cas-sensitive)");

        }
    }

}
