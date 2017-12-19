<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
	<title> ETL管理平台</title>
	<head>
		<link href="/js_css/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
		<link href="/js_css/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css">
		<script src="/js_css/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="/js_css/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="/js_css/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
		<script src="/js_css/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
		<script src="/js_css/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	</head>
<body>
<a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
<script type="text/javascript">
    $(function() {
         $("#maingrid").ligerGrid({
             minWidth : 800,
             columns : [ {
                 display : "day_time",
                 name : "day_time"
             }, {
                 display : "存储过程名称",
                 name : "proc_name"
             }, {
                 display : "失败表统计",
                 name : "table_count"
             }, {
                 display : "ODS层表统计",
                 name : "count_ods"
             }],
            dataAction: "local",
            //数据请求地址
            url: '/etl/failProcedure.do',
            async: true,
            method: 'get',
            //数据书否分页，默认为true
            usePager: true,
            pageSize: "20",//分页页面大小
            rownumbers: true
        });
    });
</script>
<div class="l-clear"></div>
<div id="maingrid"></div>
<div style="display: none;"></div>
</body>
</html>
