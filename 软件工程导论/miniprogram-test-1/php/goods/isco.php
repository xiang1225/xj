<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$uid = $_GET['uid'];
	$gid = $_GET['Gid'];
	$a=mysql_select_db("Small-program",$con);
	$sql="select id from collection where userid=$uid and goodsid=$gid";
	$result=mysql_query($sql,$con);
	$msg=mysql_fetch_assoc($result);
	if($msg!=null){
		echo '1';
	}else{
		echo '0';
	}
	mysql_close($con);
?>