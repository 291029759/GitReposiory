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

<span style="color: #1542B3 " >请输入要查询的依赖表名称 :  </span>&nbsp;<input type="text" name="table_name" id="table_name"> &nbsp;<button id ="btnLoad">         表查询</button><br><br>
<span style="color: #1542B3 " >请输入要查询的存储过程名称:</span>&nbsp;<input type="text" name="table_name" id="proc_search"> &nbsp;<button id ="btnLoadProc">    存储过程查询</button><br><br>
<span style="color: #1542B3 " >请输入要查询的结果表名称 :  </span>&nbsp;<input type="text" name="table_name" id="result_search"> &nbsp;<button id ="btnLoadResult">结果表查询</button><br><br>
<script type="text/javascript">
    var d = new Date();
    var id = "";
    $(function() {
        var grid = $("#maingrid").ligerGrid({
            minWidth : 800,
            columns : [ {
                display : "存储过程名称",
                name : "pROC_NAME"
            }, {
                display : "依赖的表名",
                name : "tABLE_NAME"
            }, {
                display : "是否ODS表",
                name : "tABLE_IS_ODS",
                render : function(rowData) {
                    if (rowData.tABLE_IS_ODS == "1") {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }, {
                display : "结果表",
                name : "rESULT_TABLE"
            } , {
                display : "是否启用",
                name : "fLAG",
                render : function(rowData) {
                    if (rowData.fLAG == "1") {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }, {
                display : "更新时间",
                name : "uPDATE_TIME"
            }, {
                display : "更新人",
                name : "uPDATE_USER"
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
                },{
                    text : '所有存储过程初始化',
                    click : itemclick,
                    op : 'init',
                    img : '/js_css/ligerUI/skins/icons/initialize.gif'
                },{
                    text : '单个存储过程初始化',
                    click : itemclick,
                    op : 'sing',
                    img : '/js_css/ligerUI/skins/icons/initialize.gif'
                },{
                    text : '清洗失败统计信息',
                    click : itemclick,
                    op : 'fail',
                    img : '/js_css/ligerUI/skins/icons/failure.gif'
                },{
                    text : '重新清洗',
                    click : itemclick,
                    op : 'recl',
                    img : '/js_css/ligerUI/skins/icons/recleansing.gif'
                },{
                    text : '查找',
                    click : itemclick,
                    op : 'find',
                    img : '/js_css/ligerUI/skins/icons/search.gif'
                },{
                    text : '刷新返回',
                    click : itemclick,
                    op : 'goback',
                    img : '/js_css/ligerUI/skins/icons/home.gif'
                }]
            },
				dataAction : "local",
				//数据请求地址
				url : '/etl/etlbooks.do?d=' + d.getTime(),
				async : true,
				method : 'get',
				//数据书否分页，默认为true
				usePager : true,
				pageSize : "50",//分页页面大小
				rownumbers : true,
				onSelectRow : function(rowdata, rowindex) {
					id = rowdata.sEQ_ID;
				}
        });
        $("#btnLoadResult").click(function () {
            var result_table = $("#result_search").val();
            grid.set({
                dataAction : "local",
                //数据请求地址
                url : '/etl/etlbooks.do?d=' + d.getTime(),
                async : true,
                method : 'get',
                //数据书否分页，默认为true
                usePager : true,
                pageSize : "50",//分页页面大小
                rownumbers : true,
                onSelectRow : function(rowdata, rowindex) {
                    id = rowdata.sEQ_ID;
                },
                parms:[{name:"result_table",value:result_table}]
            });
            grid.loadData();
        });

        $("#btnLoad").click(function () {
            var table_name = $("#table_name").val();
            grid.set({
                dataAction : "local",
                //数据请求地址
                url : '/etl/etlbooks.do?d=' + d.getTime(),
                async : true,
                method : 'get',
                //数据书否分页，默认为true
                usePager : true,
                pageSize : "50",//分页页面大小
                rownumbers : true,
                onSelectRow : function(rowdata, rowindex) {
                    id = rowdata.sEQ_ID;
                },
                parms:[{name:"table_name",value:table_name}]
            });
            grid.loadData();
        });
        $("#btnLoadProc").click(function () {
            var proc_name = $("#proc_search").val();
            grid.set({
                dataAction : "local",
                //数据请求地址
                url : '/etl/etlbooks.do?d=' + d.getTime(),
                async : true,
                method : 'get',
                //数据书否分页，默认为true
                usePager : true,
                pageSize : "50",//分页页面大小
                rownumbers : true,
                onSelectRow : function(rowdata, rowindex) {
                    id = rowdata.sEQ_ID;
                },
                parms:[{name:"proc_name",value:proc_name}]
            });
            grid.loadData();
        })
        function itemclick(item) {
            if (item.op == "add") {
                location.href = "/etl/etlAdd.jsp";
            }
            if (item.op == "edit") {
                if (id != "") {
                    location.href = "/etl/update.do?id=" + id;
                } else {
                    $.ligerDialog.warn('没有选中编辑行');
                }
            }
            if (item.op == "del") {
                if (id != "") {
                    $.ajax({
                        url : "/etl/delete.do?id=" + id,
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
            if (item.op == "init") {
                location.href = "/etl/initProcedure.do";
            }
            if (item.op == "fail") {
                location.href = "/etl/procdureFail.jsp";
            }
            if (item.op == "recl") {
                location.href = "/etl/recleaning.jsp";
            }
            if (item.op == "sing") {
                location.href = "/etl/singleProcInit.jsp";
            }
            if (item.op == "find") {
                location.href = "/etl/findTable.jsp";
            }
            if (item.op == "goback") {
                location.href = "/etl/etlbooks.jsp";
            }
        }
    });
</script>
<div class="l-clear"></div>
<div id="maingrid"></div>
<div style="display: none;"></div>
</body>

</html>
