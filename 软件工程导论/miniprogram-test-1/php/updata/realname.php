<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$a=mysql_select_db("Small-program",$con);
	$major=$_GET['major'];
	$class=$_GET['class'];
	$realname=$_GET['reaname'];
	$studyId=$_GET['studyId'];
	$id=$_GET['id'];
	$sql="update member set studyid='$studyId',realName='$realname',speciality='$major',gread='$class',AdminID=1 where id='$id'";
	if(mysql_query($sql)){
		echo "success";
	}else{
		echo "error";
	}
	mysql_close($con);
?>