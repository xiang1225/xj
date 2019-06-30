<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$id=$_GET['id'];
	$db_selected = mysql_select_db("Small-program",$con);
	$sql="select pic1,pic2,pic3,pic4 from GPic where goodsID='$id'";
	$result=mysql_query($sql);
	$row=mysql_fetch_assoc($result);
	$row=json_encode($row);
	echo $row;
	mysql_close($con);
?>