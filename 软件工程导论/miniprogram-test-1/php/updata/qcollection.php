<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$a=mysql_select_db("Small-program",$con);
	$uid=$_GET['uid'];
	$gid=$_GET['gid'];
	$sql="delete from collection where userid='$uid'and goodsid='$gid'";
	if(mysql_query($sql)){
		echo '收藏成功';
	}
?>