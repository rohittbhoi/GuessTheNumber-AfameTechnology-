// Connect to the database
$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "guessing_game";

$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
