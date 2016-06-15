<?php
	echo '<h1>Login</h1>';

?>

<form action="shout_register_new.php" method="post">
<label>Enter UserID:</label>
<input type="text" name="userID" required /><br>
<label>Enter Password:</label>
<input type="text" name="password" /><br>
<input type="submit" value="Login" />
</form>
<!DOCTYPE html>

<html lang="en">
<body>

  <section>
	<p><a href="shout.php?action=register">Register</a></p>
  </section>

  <footer>
	<p>&copy; 2015 TCSS445</p>
  </footer>
</body>
</html>