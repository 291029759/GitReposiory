<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="ule.com.etl.model.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title> ETL管理平台</title>
</head>
<body>
<script TYPE="text/javascript">
    function toVaild() {
        var myproc = document.getElementsByTagName("input")
        for(var i=0 ;i<myproc.length;i++){
            if(myproc[i].type == 'text'){
                if(myproc[i].value == ''){
                    alert(' 请输入时间')
                    return false ;
                }
            }
        }
    }
</script>
	<form action="etlReplaceSubmit.do" method="post" onsubmit="return toVaild()">
		重新清洗时间：
		<input type="text" name="time" id="time">
		<input type="submit" value="提交"> &nbsp;
		<input type="reset"	value="重置"> &nbsp;
		<a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
		<table border="1" cellspacing="0" id="proc_name">
			<tr>
				<th></th>
				<th>ULE_DM/DW.存储过程名称</th>
				<th>当前状态</th>
				<th>清洗时间</th>
				<th>创建时间</th>
			</tr>
			<c:forEach var="list" items="${lists}">
				<tr>
					<td><input type="checkbox" name="checkbox" value="${list.proc_name}"></td>
					<td>${list.proc_name}</td>
					<td><c:if test="${list.flag==0}">
						未清洗
					</c:if>
					<c:if test="${list.flag==1}">
						正在清洗
					</c:if>
					<c:if test="${list.flag==2}">
						清洗完成
					</c:if>
					</td>
					<td>
					<c:choose>
						<c:when test="${list.day_time==0||list.day_time==null}">
							没有清洗时间
						</c:when>
						<c:otherwise>
							${list.day_time}
						</c:otherwise>
					</c:choose>
					</td>
					<td><fmt:formatDate value="${list.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>

		</table>
		<br>
	</form>
</body>
</html>
