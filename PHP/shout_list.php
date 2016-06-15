
<!DOCTYPE html>

<html lang="en">
<body>

  <section>
	<p><a href="shout.php?action=fav">Favorites</a></p>
  </section>

</body>
</html>

<?php

echo '<h1>Song List</h1>';

if (isset($_POST['title']))
    $filter = addslashes($_POST['title']);
else
    $filter = '';

if (!isset($db)) {
    require('dbconnect.php');
    $db = get_connection();
}

$select_statement = "SELECT artistName, songName, albumName, albumGenre, songLength FROM Song NATURAL JOIN Album ";
if ($filter != '')
    $select_statement .= "WHERE songName LIKE '%$filter%';";
else
    $select_statement .= ";";
$shouts = $db->query($select_statement);

if ($shouts != null) {
    echo "<table border='1'>";
    echo "<tr><th>Song</th><th>Artist</th><th>Album</th><th>Length</th><th>Genre</th></tr>";
    foreach ($shouts as $shout) {
        $title = htmlentities($shout[0], ENT_QUOTES);
        #echo $title;
        echo "<td>" . $shout[1] . "</td>";
        echo "<td>" . $shout[0] . "</td>";
        echo "<td>" . $shout[2] . "</td>";
		echo "<td>" . $shout[4] . "</td>";
		echo "<td>" . $shout[3] . "</td></tr>";
    }
    echo "</table>";
}
?>
