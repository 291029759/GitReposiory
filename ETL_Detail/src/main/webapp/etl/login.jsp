<%@ page language="java" import="java.util.*,ule.com.etl.model.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> ETL管理平台</title>
<style type="text/css">
	table{width:300px;margin:400px auto;}
</style>
</head>
<body>
	<form action="entry.do" method="post">
		<table>
			<tr>
				<td style="text-align:left;">用户名：</td><td style="text-align:left;"><input type="text" name="username" value=""></td>
			</tr>
			<tr>
				<td style="text-align:left;">密&nbsp;码：</td><td  style="text-align:left;"><input type="password" name="pwd" value=""></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录"></td><td><input type="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>