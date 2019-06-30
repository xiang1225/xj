<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$fenl = $_GET['fenl'];
	$a=mysql_select_db("Small-program",$con);
	$sql="select * from goods where goodsType='$fenl'";
	$result=mysql_query($sql);
	$users=array();
	$i=0;
	while($row=mysql_fetch_assoc($result)){
		$users[$i]=$row;
		$i++;
	}
	echo json_encode(array('goods'=>$users));
	mysql_close($con);
?>