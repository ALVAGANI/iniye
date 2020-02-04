<?php
	require_once('koneksi.php');

	$Email = $_GET['Email'];
		if(!$Email){
	  		echo json_encode(array('message'=>'required field is empty'));
		}	else {
			$query = mysqli_query($CON, "DELETE FROM perangkat WHERE Email='$Email'");
				if($query){
				    echo json_encode(array('message'=>'perangkat data successfully deleted.'));
				  	}
  		else{
    		echo json_encode(array('message'=>'perangkat data failed to delete.'));
  		}
	}
?>