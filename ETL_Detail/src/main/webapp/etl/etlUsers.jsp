<%--
  Created by IntelliJ IDEA.
  User: LeslieLee
  Date: 2017/11/6 0008
  Time: 22:04
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					display : "用户名称",
					name : "username"
				}, {
					display : "用户密码",
					name : "pwd"
				}, {
					display : "邮箱地址",
					name : "email"
				}, {
					display : "手机号码",
					name : "phone"
				}, {
					display : "用户是否禁用",
					name : "flag"
				}],
				toolbar : {
					items : [ {
						text : '增加',
						click : itemclick,
						op : 'add',
						icon : 'add'
					}, {
						line : true
					}, {
						text : '修改',
						click : itemclick,
						op : 'edit',
						icon : 'modify'
					}, {
						line : true
					}, {
						text : '删除',
						click : itemclick,
						op : 'del',
						img : '/js_css/ligerUI/skins/icons/delete.gif'
					} ]
				},
				dataAction : "local",
				//数据请求地址
				url : '/etl/getAllUser.do',
				async : true,
				method : 'get',
				//数据书否分页，默认为true
				usePager : true,
				pageSize : "10",//分页页面大小
				rownumbers : true,
				onSelectRow : function(rowdata, rowindex) {
					id = rowdata.id;
				}
			});
			function itemclick(item) {
				if (item.op == "add") {
					location.href = "/etl/addUsers.jsp";
				}
				if (item.op == "edit") {
					if (id != "") {
						location.href = "/etl/updateUser.do?id=" + id;
					} else {
						$.ligerDialog.warn('没有选中编辑行');
					}
				}
				if (item.op == "del") {
					if (id != "") {
						 $.ajax({
							url : "/etl/deleteUser.do?id=" + id,
						    type:"post",
							dataType : "text",
							success : function(data) {
								grid.loadData();
								$.ligerDialog.warn("删除成功");
								id="";
							}
						});
					} else {
						$.ligerDialog.warn('没有选中删除行');
					}
				}
			}
		});
	</script>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display: none;"></div>
</body>
</html>
