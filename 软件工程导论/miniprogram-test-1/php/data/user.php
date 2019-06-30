<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
//	mysql_set_charset($con,'utf8');
	//获取传值
	$id = $_GET['id'];
	//查询数据库
	$a=mysql_select_db("Small-program",$con);
	$sql_select="select * from member where id='$id'";
	$result=mysql_query($sql_select,$con);
	$msg=mysql_fetch_assoc($result);
	if($msg!=null){
			$msg=json_encode($msg);
			echo $msg;
	}
	mysql_close($con);
?>