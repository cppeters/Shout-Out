<?php
	if (!isset($db)) {
			require('dbconnect.php');
			$db = get_connection();
	}
		try {
			global $db;
			$userID = addslashes($_POST['userID']);
			$firstName = $_POST['firstName'];
			$lastName = $_POST['lastName'];
			$password = $_POST['password'];
			$insert_statement = "INSERT INTO User VALUES($userID, $firstName, $lastName, $password);";
			//echo "$insert_statement";
			$db->exec($insert_statement);
		}
		catch(Exception $e) {
					echo "Registering User";
		}
		header('Location: shout.php?action=list');

?>
