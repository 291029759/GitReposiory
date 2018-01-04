<%--
  Created by IntelliJ IDEA.
  User: LeslieLee
  Date: 2017/11/6 0008
  Time: 22:04
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>ETL管理平台</title>
<link href="/js_css/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
<link href="/js_css/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css">
<script src="/js_css/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="/js_css/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/js_css/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="/js_css/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="/js_css/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
</head>
<body>
	<script type="text/javascript">
		var id = "";

		$(function() {
			var grid = $("#maingrid").ligerGrid({
				minWidth : 800,
				columns : [ {
					display : "ID",
					name : "id"
				}, {
					display : "任务类型",
					name : "task_type"
				}, {
					display : "任务结果",
					name : "task_result"
				}, {
					display : "任务描述",
						name : "task_desc"
				}, {
					display : "任务数",
					name : "task_num"
				}, {
                    display : "创建时间",
                    name : "create_date"
                }, {
                    display : "任务执行日期",
                    name : "task_date"
                }, {
                    display : "etl任务执行人员",
                    name : "etl_user_name"
                }, {
                    display : "ETL时间戳",
                    name : "etl_time"
                }],
				dataAction : "local",
				//数据请求地址
				url : '/etl/allTaskInfos.do',
				async : true,
				method : 'get',
				//数据书否分页，默认为true
				usePager : true,
				pageSize : "30",//分页页面大小
				rownumbers : true,
				onSelectRow : function(rowdata, rowindex) {
					id = rowdata.id;
				}
			});
		});
	</script>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display: none;"></div>
</body>
</html>
