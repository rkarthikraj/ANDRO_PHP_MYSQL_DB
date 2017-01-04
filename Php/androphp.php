<?php
if($_GET['type']=="sel")
{
$id=$_GET['id'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("INPUT",$con) or die("database error");
$result=mysql_query("SELECT * FROM Details WHERE ID = $id",$con);
echo "<table border='2'>";
$result1 = array();
while($row = mysql_fetch_array($result))
	{
		array_push($result1,array(
			'id'=>$row['ID'],
			'name'=>$row['NAME'],
			'place'=>$row['PLACE'],
		));
	}
echo json_encode($result1);
}
else if($_GET['type']=="ins")
{
$name=$_GET['name'];
$place=$_GET['place'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("INPUT",$con) or die("database error");
$query=mysql_query("insert into Details values('','$name','$place')");
$result1=@mysql_query("select * from Details");
echo "<table border='2'>";
while($row=mysql_fetch_array($result1))
{
echo "<tr>";
echo "<td>",$row['ID'],"</td><td>",$row['NAME'],"</td><td>",$row['PLACE'],"</td>";
echo "</tr>";
}
echo "</table>";
}
else if($_GET['type']=="upd")
{
$id=$_GET['id'];
$name=$_GET['name'];
$place=$_GET['place'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("INPUT",$con) or die("database error");
$query=mysql_query("UPDATE Details SET NAME = '$name', PLACE = '$place' WHERE ID = '$id'",$con);
$result1=@mysql_query("select * from Details");
echo "<table border='2'>";
while($row=mysql_fetch_array($result1))
{
echo "<tr>";
echo "<td>",$row['ID'],"</td><td>",$row['NAME'],"</td><td>",$row['PLACE'],"</td>";
echo "</tr>";
}
echo "</table>";
}
else if($_GET['type']=="del")
{
$id=$_GET['id'];
$name=$_GET['name'];
$place=$_GET['place'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("INPUT",$con) or die("database error");
$query=mysql_query("DELETE FROM Details WHERE id = $id",$con);
$result1=@mysql_query("select * from Details");
echo "<table border='2'>";
while($row=mysql_fetch_array($result1))
{
echo "<tr>";
echo "<td>",$row['ID'],"</td><td>",$row['NAME'],"</td><td>",$row['PLACE'],"</td>";
echo "</tr>";
}
echo "</table>";
}
?>