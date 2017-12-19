<%--
  Created by IntelliJ IDEA.
  User: liumeng
  Date: 2017/11/14
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>findTable</title>
</head>
<body>
<form name="form" action="etlReplaceStatusProc.do" method="post" onsubmit="return toVaild()">
    输入存储过程名称:&nbsp;&nbsp; <input id="proc_name" type='text' name="proc_name">
    <input type="submit" value="提交" /></br>
</form>
<form name="form" action="etlReplaceStatusTable.do" method="post" onsubmit="return toVaild()">
    输入表名查询 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="table_name" type="text" name="table_name">
    <input type="submit" value="提交" /> </br></br>
    <a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
</form>
</body>
</html>
