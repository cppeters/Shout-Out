<?php
	echo '<h1>Register</h1>';

?>

<form action="shout_register_new.php" method="post">
<label>Enter UserID:</label>
<input type="text" name="userID" required /><br>
<label>Enter First Name:</label>
<input type="text" name="firstName" required /><br>
<label>Enter Last Name:</label>
<input type="text" name="lastName" /><br>
<label>Enter Password:</label>
<input type="text" name="password" /><br>
<input type="submit" value="Register" />
</form>
