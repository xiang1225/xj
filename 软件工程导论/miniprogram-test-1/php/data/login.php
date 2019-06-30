<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
//	mysql_set_charset($con,'utf8');
	//获取传值
	$code = $_GET['code'];//小程序传来的code值
    $nick = $_GET['nick'];//小程序传来的用户昵称
    $imgUrl = $_GET['avaurl'];//小程序传来的用户头像地址
	//查询数据库
	$a=mysql_select_db("Small-program",$con);
	$sql_select="select * from member where user='$nick'";
	$result=mysql_query($sql_select,$con);
	$msg=mysql_fetch_assoc($result);
	if($msg!=null){
			$msg=json_encode($msg);
			echo $msg;
	}else{
		$sql_update="INSERT INTO member (user,imgurl) values ('$nick','$imgUrl')";
		mysql_query($sql_update);
		$sql_seecta="select * from member where user='$nick'";
		$result=mysql_query($sql_seecta);
		$msg=mysql_fetch_assoc($result);
		if($msg!=null){
			$msg=json_encode($msg);
			echo $msg;
		}
	}
	
	mysql_close($con);
?>