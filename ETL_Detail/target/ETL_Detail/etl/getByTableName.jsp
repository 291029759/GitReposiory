<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="ule.com.etl.model.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title> ETL管理平台</title>
</head>
<body>
<form action="cleanProcedureEntiy.do" method="post">
    <table border="1" cellspacing="0">
        <tr>
            <th></th>
            <th>依赖的表名</th>
            <th>存储过程名称</th>
            <th>是否ODS表</th>
            <th>是否启用</th>
            <th>优先级</th>
            <th>更新时间</th>
            <th>更新人</th>
            <th>初始化</th>
        </tr>
            <c:forEach items="${lists}" var ="node">
                <tr>
                    <td><input type="checkbox" name="PROC_NAME" value="${node.PROC_NAME}"></td>
                    <td> <c:out value="${node.TABLE_NAME}"></c:out></td>
                    <td> <c:out value="${node.PROC_NAME}"></c:out></td>
                    <td>
                        <c:if test="${node.TABLE_IS_ODS==1}">
                            <option value="1" selected="selected">是</option>
                        </c:if>
                        <c:if test="${node.TABLE_IS_ODS==0}">
                            <option value="0" selected="selected">否</option>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${node.FLAG==1}">
                            <option value="1" selected="selected">是</option>
                        </c:if>
                        <c:if test="${node.FLAG==0}">
                            <option value="0" selected="selected">否</option>
                        </c:if>
                    </td>
                    <td> <c:out value="${node.LEV}"></c:out></td>
                    <td> <c:out value="${node.UPDATE_TIME}"></c:out></td>
                    <td> <c:out value="${node.UPDATE_USER}"></c:out></td>
                    <td><a href="singleProcInit.jsp?PROC_NAME=${node.PROC_NAME}" style="text-decoration: none;color: red">进入初始化页面</a></td>
                </tr>
            </c:forEach>
        </form>
    </table>
    重新清洗的时间：
    <input type="text" name="day_time" >
    <br>
    <input type="submit" value="提交"> &nbsp;
    <input type="reset"	value="重置"> &nbsp;
    <a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
</form>

</body>
</html>
