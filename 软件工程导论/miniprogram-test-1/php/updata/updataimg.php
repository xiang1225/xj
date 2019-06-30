<?php
 	include "../contact/contact.php";
 	session_start();
	header("Content-Type:text/html;charset=utf8"); 
   	mysql_select_db("Small-program",$con); //选择数据库
	$name=$_FILES['file']['name'];
	$goodname=$_POST['goodsname'];
	$xu=$_POST['xu']+1;
	$sql1="select goodsID from goods where goodsName='$goodname'";
	$result=mysql_query($sql1);
	$msg=mysql_fetch_assoc($result);
	$msg=implode(',',$msg);
	$dir=iconv("UTF-8", "GBK","./../../img/".$msg);
	if (!file_exists($dir)){
            		mkdir ($dir,0777,true);
       	 }
	$dirName="./../../img/".$msg.'/'.$msg.'-'.$xu.'.png';
	$path=$dirName;
	if ($_FILES["file"]["error"] > 0)
	{
		echo "错误: " . $_FILES["file"]["error"] . "<br />";
	}
	if(move_uploaded_file($_FILES["file"]["tmp_name"], $path))
	{
		if($xu=='1'){
			$url='http://132.232.119.136:81/img/'.$msg.'/'.$msg.'-'.$xu.'.png';
			$sql2="INSERT INTO GPic (goodsid,pic1) values ('$msg','$url')";
			mysql_query($sql2);
			$sql3="update goods set goodsPic='$url' where goodsID='$msg'";
			mysql_query($sql3);
		}else{
			$url='http://132.232.119.136:81/img/'.$msg.'/'.$msg.'-'.$xu.'.png';
			$pic='pic'.$xu;
			$sql2="update GPic set $pic='$url' where goodsid='$msg'";
			mysql_query($sql2);
		}
	}
	mysql_close($con);
?>   
