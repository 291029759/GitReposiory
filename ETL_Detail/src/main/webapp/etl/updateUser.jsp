<%--
  Created by IntelliJ IDEA.
  User: LeslieLee
  Date: 2017/11/6 0008
  Time: 22:04
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*,ule.com.etl.model.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> ETL管理平台</title>
</head>
<body>
<form action="userSubmit.do" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <td>用户名称:</td>
            <td><input type="text" name="username" style="width:500px"  value="${user.username}">
            </td>
        </tr>
        <tr>
            <td>用户密码;</td>
            <td><input type="text" name="pwd" style="width:500px" value="${user.pwd}">
            </td>
        </tr>
        <tr>
            <td>用户邮箱;</td>
            <td><input type="text" name="email" style="width:500px" value="${user.email}">
            </td>
        </tr>
        <tr>
            <td>用户手机号码;</td>
            <td><input type="text" name="phone" style="width:500px" value="${user.phone}">
            </td>
        </tr>
        <tr>
            <td>用户是否启用:</td>
            <td><select name="flag" style="width:100px">
                <c:if test="${user.flag==1}">
                    <option value="0" >启用</option>
                    <option value="1" selected="selected">禁用</option>
                </c:if>
                <c:if test="${user.flag==0}">
                    <option value="0" selected="selected">启用</option>
                    <option value="1" >禁用</option>
                </c:if>
            </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="提交"> <input type="reset" value="重置">
    <a href="etlUsers.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
</form>
</body>
</html>