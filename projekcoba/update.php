<?php

require_once('koneksi.php');

	$Nama = $_POST['Nama'];
	$Email = $_POST['Email'];
	$No_Kartu = $_POST['No_Kartu'];
	$No_Perangkat = $_POST['No_Perangkat'];
	$Username = $_POST['Username'];
	$Password = $_POST['Password'];

	if(!$Nama || !$Email || !$No_Kartu || !$No_Perangkat || !$Username || !$Password){
 			 echo json_encode(array('message'=>'required field is empty.'));
	} else {
		$query = mysqli_query($CON, "UPDATE perangkat SET Nama='$Nama', No_Kartu=$No_Kartu, No_Perangkat=$No_Perangkat, Username='$Username', Password='$Password' WHERE Email = '$Email'");
		if($query){
    		echo json_encode(array('message'=>'perangkat data successfully updated.'));
  		}else{
    		echo json_encode(array('message'=>'perangkat data failed to update.'));
  	}
  }
?>