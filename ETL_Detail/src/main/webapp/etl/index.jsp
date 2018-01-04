<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title> ETL管理平台</title>
    <link href="/js_css/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body{ padding:20px; margin:0;}
        #accordion1{ width:100%;overflow:hidden;}
    </style>
    <script src="/js_css/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="/js_css/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="/js_css/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#layout1").ligerLayout({
                leftWidth: 200,
                centerBottomHeight: 180
            });
            $("#accordion1").ligerAccordion(
                {
                    height: 600
                });
        });
        function openUrl(url) {
            $("#frm").attr("src",url);
        }
    </script>
    <style type="text/css">
        body
        {
            padding: 5px;
            margin: 0;
            padding-bottom: 15px;
        }
        #layout1
        {
            width: 100%;
            margin: 0;
            padding: 0;
        }
        .l-page-top
        {
            height: 40px;
            background: #f8f8f8;
            margin-bottom: 3px;
        }
        h4
        {
            margin: 20px;
        }
        #accordion1 a
        {
            cursor:pointer;
        }
    </style>
</head>
<body style="padding: 10px">
<div class="l-page-top" style="color:blue;size: 20px">ETL管理系统</div>
<div id="layout1">
    <div position="left" title="功能菜单">
        <div id="accordion1">
            <div title="系统设置">
                <ul>
                    <li><a class="l-link" onclick="openUrl('etlbooks.jsp')">清洗依赖管理</a></li>
                    <li><a class="l-link" onclick="openUrl('etlReplace.do')">存储过程重洗管理</a></li>
                    <li><a class="l-link" onclick="openUrl('etlUsers.jsp')">用户管理</a></li>
                    <li><a class="l-link" onclick="openUrl('allTaskInfos.jsp')">执行日志</a></li>
                    <%-- <li><a class="l-link" href="/etl/etlReplace.jsp">存储过程重洗管理</a></li>--%>
                </ul>
            </div>
        </div>
    </div>
    <div position="center" title="   ">
        <br />
        <iframe id="frm"  style="width:100%;height:100%" frameborder=0 scrolling=auto ></iframe>
    </div>
</div>
</body>
</html>
