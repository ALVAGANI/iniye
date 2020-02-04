<?php
	require_once('koneksi.php');
	$result = array();
	$query = mysqli_query($CON,"SELECT * FROM perangkat ORDER BY nama DESC");
	while($row = mysqli_fetch_assoc($query)){
	  $result[] = $row;
	}
	echo json_encode(array('result'=>$result));
?>