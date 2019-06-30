<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	//获取传值
	
	//操作数据库
	$db_selected = mysql_select_db("Small-program",$con);
	$id=$_GET['id'];
	$sql="select * from collection where userid=$id";
	$result=mysql_query($sql);
	$goods=array();
	$i=0;
	while($row=mysql_fetch_assoc($result)){
		$goods[$i]=$row;
		$i++;
	}
	echo json_encode(array('goods'=>$goods));
	mysql_close($con);
?>