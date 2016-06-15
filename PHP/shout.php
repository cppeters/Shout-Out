<?php
	if (isset($_GET['action']))
		$action = $_GET['action'];
	else
		$action = 'list';
	if (!isset($db)) {
		require('dbconnect.php');
		$db = get_connection();
	}

?>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>Song</title>
	<link rel="stylesheet" href="styles/main.css">
</head>

<body>
<?php include('header.php'); ?>

  <section>
  <?php
  	switch($action) {
			case 'list':
				include('shout_list.php');
				break;
			case 'search':
				include('shout_search.php');
				break;
			case 'login':
				include('shout_login.php');
				break;
			case 'fav':
				include('shout_register.php');
				break;
			case 'register':
				include('shout_register.php');
				break;
			default:
	}
	?>
  </section>

<footer>
	<p>&copy; 2015 TCSS445</p>
  </footer>
</body>
</html>
