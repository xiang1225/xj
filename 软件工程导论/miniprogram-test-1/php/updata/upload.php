<?php
	include "../contact/contact.php";
	session_start();
	header("Content-type:text/html;charset=utf-8");
	$a=mysql_select_db("Small-program",$con);
	$name=$_GET['name'];
	$id=$_GET['id'];
	$number=$_GET['number'];
	$yprice=$_GET['Oprice'];
	$price=$_GET['price'];
	$detail=$_GET['contant'];
	$phone=$_GET['phone'];
	$fenl=$_GET['fenl'];
	$jdetail=substr($detail,0,150);
	$sql="INSERT INTO goods (goodsName,id,goodsType,goodsPrice,goodsDetails,goodsStatus,goodsOprice,goodsPic,goodjDetails,number,contant) values ('$name','$id','$fenl','$price','$detail','1','$yprice',' ','$jdetail','$number','$phone')";
	if(mysql_query($sql)){
		echo 'success';
	}else{
		echo 'error';
	}
?>