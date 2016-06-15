<?php

	function get_connection() {
		$dsn = 'mysql:host=127.0.0.1:3306;dbname=shoutdb';
		$userid = 'root'; //Change this to yours
		$password = ''; //Change this to yours

		try {
		    $db = new PDO($dsn, $userid, $password);
		}
		catch(PDOException $e) {
			echo "Error connecting to database";
	    }
	    	return $db;
	}
?>