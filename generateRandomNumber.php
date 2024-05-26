<?php
session_start();

// Function to generate a random number within the specified range
function generateRandomNumber($min, $max) {
    return rand($min, $max);
}

// Function to play a round of the game
function playRound($randomNumber, $attemptsLimit) {
    $score = 0;

    for ($attempts = 1; $attempts <= $attemptsLimit; $attempts++) {
        if (isset($_POST['guess'])) {
            $guess = intval($_POST['guess']);
            if ($guess == $randomNumber) {
                echo "Congratulations! You guessed the number.";
                $score = $attemptsLimit - $attempts + 1;
                break;
            } elseif ($guess < $randomNumber) {
                echo "Try a higher number.";
            } else {
                echo "Try a lower number.";
            }
        }
    }

    return $score;
}

// Main game logic
if (isset($_POST['start'])) {
    $minRange = $_POST['min_range'];
    $maxRange = $_POST['max_range'];
    $attemptsLimit = $_POST['attempts_limit'];

    if ($minRange >= $maxRange) {
        echo "Invalid range. Minimum number must be less than maximum number.";
    } else {
        $randomNumber = generateRandomNumber($minRange, $maxRange);
        $_SESSION['randomNumber'] = $randomNumber;
        $_SESSION['attemptsLimit'] = $attemptsLimit;
    }
}

if (isset($_SESSION['randomNumber']) && isset($_SESSION['attemptsLimit'])) {
    $randomNumber = $_SESSION['randomNumber'];
    $attemptsLimit = $_SESSION['attemptsLimit'];

    if (isset($_POST['guess'])) {
        $score = playRound($randomNumber, $attemptsLimit);
        echo "Your score for this round is: " . $score;
        unset($_SESSION['randomNumber']);
        unset($_SESSION['attemptsLimit']);
    }
}
?>
