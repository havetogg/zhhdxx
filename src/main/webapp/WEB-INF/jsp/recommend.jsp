<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/22
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>微信达人推荐</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/backstage.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/page.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jQuery-1.11.3.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/ajaxfileupload.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js" ></script>
    <!--分页-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/page.js" ></script>

    <script type="text/javascript">
        var pageData = "";
        $(function(){
            initRecommendInfo();

            $("#search").click(function () {
                if($("#startDate").val() == ""&&$("#endDate").val() == ""){

                }else{
                    var data = {};
                    if($("#startDate").val() != ""){
                        data.startDate = $("#startDate").val();
                    }
                    if($("#endDate").val() != ""){
                        data.endDate = $("#endDate").val();
                    }
                    initRecommendInfo(data);
                }
            })
            $("#export").click(function () {
                var url = getRootPath()+"/recommend/exportExcel";
                $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
            })
            //表格兼容性偶数行的背景色
            $("table tr:nth-child(odd)").css("background-color","#f4f4f4");
            $("table tr th").css("background-color","#fff");

        })
        function initRecommendInfo(data){
            $("#tb").html("");
            $.ajax({
                url: getRootPath()+"/recommend/list",
                type: "post",
                data: data,
                dataType: "json",
                success: function (data) {
                    if(data != null&&data != "null"&&data != undefined&&data != "undefined"&&data.length>0){
                        pageData = data;
                        var listCount = data.length;
                        $("#page").initPage(listCount,1,GG.kk);
                    }
                }
            })
        }
        var GG = {
            "kk":function(mm){
                if(pageData != ""){
                    $("#tb").html("") ;
                    var begin = (mm-1)*10;
                    var html = "";
                    for(var i=begin;i<begin+10;i++){
                        if(pageData[i] != undefined&&pageData[i].recomCode != null) {
                            html += "<tr><td>" + pageData[i].recomCode + "</td>";
                            html += "<td>" + pageData[i].count + "</td>";
                            html += "<td>" + pageData[i].createTime + "</td></tr>";
                        }
                    }
                    $("#tb").html(html);
                }
            }
        }
    </script>
</head>
<body>
<!--head-->
<div class="head">
    <a href="javascript:logout();">注销</a>
    <!--<a href="javascript:login();">登录</a>-->
    <p>欢迎<span id="userName"></span></p>
</div>
<!--main-->
<div class="main">
    <!--main-left-->
    <div class="main_left">
        <ul>
            <li class="active">
                <a href="javascript:;">微信达人推荐</a>
            </li>
            <li>
                <a href="javascript:;">周计划</a>
            </li>
            <li>
                <a href="javascript:;">周计划2</a>
            </li>
            <li>
                <a href="http://nanjing.cmbchina.com/cmbbanknew/login.action">霸王餐</a>
            </li>
        </ul>
    </div>
    <!--main-right-->
    <div class="main_right">
        <div class="search">
            <label>起始日期</label>
            <input class="time" type="date" id="startDate"/>
            <i></i>
            <label>结束日期</label>
            <input class="time" type="date" id="endDate"/>
            <a class="btn2" href="javascript:;" id="export">数据导出</a>
            <a class="btn1" href="javascript:;" id="search">查询</a>
        </div>
        <table class="staff">
            <%--<colgroup>
                <col width="33%" />
                <col width="33%" />
                <col width="33%" />
            </colgroup>--%>
            <thead>
            <tr>
                <th>编号</th>
                <th>数据</th>
                <th>创建时间</th>
            </tr>
            </thead>
            <tbody id="tb">
                <%--<tr>
                    <td>064646454</td>
                    <td>1000</td>
                    <td>2017-5-1</td>
                </tr>
                <tr>
                    <td>064646454</td>
                    <td>1000</td>
                    <td>2017-5-1</td>
                </tr>
                <tr>
                    <td>064646454</td>
                    <td>1000</td>
                    <td>2017-5-1</td>
                </tr>--%>
            </tbody>
        </table>
        <!--分页-->
        <div class="zzsc">
            <ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
        </div>
    </div>
</div>
</body>
</html>
