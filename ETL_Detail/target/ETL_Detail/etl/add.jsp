<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*,ule.com.etl.model.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ETL管理平台</title>
</head>
<body>
	<form action="submit.do" method="post">
		<input type="hidden" name="SEQ_ID" value="${book.SEQ_ID}">
		<table>
			<tr>
				<td>存储过程名称:</td>
				<td><input type="text" name="PROC_NAME" style="width:500px" value="${book.PROC_NAME}"></td>
			</tr>
			<tr>
				<td>存储过程依赖表:</td>
				<td><input type="text" name="TABLE_NAME" style="width:500px" value="${book.TABLE_NAME}"></td>
			</tr>
			<tr>
				<td>是否ODS表:</td>
				<td>
				<select name="TABLE_IS_ODS" style="width:100px">
						<c:if test="${book.TABLE_IS_ODS==1||book.TABLE_IS_ODS==null}">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
						</c:if>
						<c:if test="${book.TABLE_IS_ODS==0}">
							<option value="1" >是</option>
							<option value="0" selected="selected">否</option>
						</c:if>
				</select>
				</td>
			</tr>
			<tr>
				<td>是否启用:</td>
				<td><select name="FLAG" style="width:100px">
						<c:if test="${book.FLAG==1||book.FLAG==null}">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
						</c:if>
						<c:if test="${book.FLAG==0}">
							<option value="1" >是</option>
							<option value="0" selected="selected">否</option>
						</c:if>
				</select>
				</td>
			</tr>
			<tr>
				<td>优先等级:</td>
				<td><input type="text" name="LEV" style="width:50px" value="${book.LEV}"></td>
			</tr>
			<tr>
				<td>结果表:</td>
				<td><input type="text" name="RESULT_TABLE" style="width:100px" value="${book.RESULT_TABLE}"></td>
			</tr>
		</table>
		<input type="submit" value="提交"> &nbsp;
		<input type="reset" value="重置"> &nbsp;
		<a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
	</form>
</body>
</html>