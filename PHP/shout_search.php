<?php
	echo '<h1>Song Search Page</h1>';

?>

<form action="shout.php" method="post">
<label>Enter Keyword:</label>
<input type="text" name="title" />
<input type="submit" value="Search" />
<input type="hidden" name="action" value="list" /><br>
</form>
