<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	//获取传值
	
	//操作数据库
	$db_selected = mysql_select_db("Small-program",$con);
	$sql_select="select * from goods";
	$result=mysql_query($sql_select);
	$users=array();
	$i=0;
	while($row=mysql_fetch_assoc($result)){
		$users[$i]=$row;
		$i++;
		
	}
	echo json_encode(array('goods'=>$users));
	mysql_close($con);
?>