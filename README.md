# Guess the Number �

A simple **Java console game** where you guess a secret number within a range, with three difficulty levels.

## Features
- Difficulties: `EASY (1–50)`, `NORMAL (0–100)`, `HARD (1–1000)`
- Prevents duplicate guesses
- Limits the number of attempts
- Tracks your **best score** (fewest guesses)
- Error handling for invalid or out-of-range inputs

## How to Run
This project uses `Random.nextInt(origin, bound)` which requires **Java 17 or newer**.

```bash
# Compile
javac src/GuessNumber.java

# Run
java -cp src GuessNumber
```
How to Play
- Choose difficulty: e, n, or h.
- Enter guesses when prompted.
- The game will say “too high” or “too low” and show attempts left.
- Guess correctly before you run out of attempts!

Example Output: 
choose the difficulty, easy (e), normal (n) or hard (h)
e
The game is simple. Guess the number, which is from 1 - 50 you have only 10 attempts this round.
Enter a number, Guess #1
25
too low, guess #2 and the Attempts left: 9
...
YOU WIN! -> Number of guesses = 6 your bestscore is: 6
Do you want to play again? (yes/no)

Notes
- Written in pure Java.
- Demonstrates enums, loops, conditionals, sets, and input validation.
- Created by Mostafa Eldimeery.
