<%--
  Created by IntelliJ IDEA.
  User: LeslieLee
  Date: 2017/11/13 0008
  Time: 12:04
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*,ule.com.etl.model.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ETL管理平台</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta charset="UTF-8">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="/js_css/jquery/jquery-1.9.0.min.js"></script>

    <script type="text/javascript">
        ////////添加一行、删除一行封装方法///////
        /**
         * 为table指定行添加一行
         * tab 表id
         * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
         * trHtml 添加行的html代码
         */
        function addTr(mytable, row, trHtml) {
            //获取table最后一行 $("#tab tr:last")
            //获取table第一行 $("#tab tr").eq(0)
            //获取table倒数第二行 $("#tab tr").eq(-2)
            var $tr = $("#" + mytable + " tr").eq(row);
            if ($tr.size() == 0) {
                alert("指定的table id或行数不存在！");
                return;
            }
            $tr.after(trHtml);
        }
        function delTr(ckb) {
            //获取选中的复选框，然后循环遍历删除
            var ckbs = $("input[name=" + ckb + "]:checked");
            if (ckbs.size() == 0) {
                alert("要删除指定行，需选中要删除的行！");
                return;
            }
            ckbs.each(function () {
                $(this).parent().parent().remove();
            });
        }
        /**
         * 全选
         * allCkb 全选复选框的id
         * items 复选框的name
         */
        function allCheck(allCkb, items) {
            $("#" + allCkb).click(function () {
                $('[name=' + items + ']:checkbox').attr("checked", this.checked);
            });
        }
        ////////添加一行、删除一行测试方法///////
        $(function () {
            //全选
            allCheck("allCkb", "ckb");
        });
        function addTr2(mytable, row) {
            var trHtml = "<tr align='center'> <input type=\"hidden\" name=\"SEQ_ID\">" +
                "<td style='width: 10%'><input type='checkbox' name='ckb' /></td>" +
                "<td class='tesla'><input type='text'  name=\"PROC_NAME\"/></td>" +
                "<td class='tesla'><input type='text'  name=\"DAY_TIME\"/></td>" +
                "</tr>";
            addTr(mytable, row, trHtml);
        }
        function delTr2() {
            delTr('ckb');
        }
        function toVaild() {
            var tableObj = document.getElementById('mytable');
            var input_datas = tableObj.getElementsByTagName("input");
            for(var i=0 ;i<input_datas.length;i++){
                if(input_datas[i].type == 'text'){
                    if(input_datas[i].value == ''){
                        alert(' null value exists, please continue complete or delete the row ...')
                        return false ;
                    }
                }
            }
        }
    </script>
    <style type="text/css">
        tr td {
            width: 20%
        }
        .tesla {
            height: 99%;
            width: 99%
        }
        #need {
            height: 99%;
            width: 99%
        }
    </style>
</head>
<body>
<form action="singProcleInit.do" method="post" onsubmit="return toVaild()">
    <table border="1px #ooo" id="mytable" cellpadding="0" cellspacing="0" width="30%">
        <tr align="center">
            <td style="width: 10%"><input id="allCkb" type="checkbox"/></td>
            <td>存储过程名称</td>
            <td>初始化时间</td>
        </tr>
        <tr align="center">
            <td style="width: 10%"><input type="checkbox" name="checkbox"></td>
            <td class="tesla"><input id="need" type="text" name="PROC_NAME" value="${param.PROC_NAME}"></td>
            <td class="tesla"><input type="text" name="DAY_TIME"></td>
        </tr>
    </table>
    <input type="submit" value="提交"> &nbsp;
    <input type="button" onclick="addTr2('mytable', -1)" value="添加"> &nbsp;
    <input type="button" onclick="delTr2()" value="删除"> &nbsp;
    <a href="etlbooks.jsp"><img src="/js_css/ligerUI/skins/icons/home.gif"/>返回</a>
</form>
</body>
</html>
