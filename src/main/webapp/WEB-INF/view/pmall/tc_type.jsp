<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/common.jsp"%>
<html>
<head>
    <%--<script charset="utf-8" src="tcjs/jquery.min.js?v=01291"></script>--%>
    <%--<script charset="utf-8" src="tcjs/global.js?v=01291"></script>--%>
    <%--<script charset="utf-8" src="tcjs/bootstrap.min.js?v=01291"></script>--%>
    <%--<script charset="utf-8" src="tcjs/template.js?v=01291"></script>--%>

    <%--<link rel="stylesheet" href="tccss/bootstrap.css?v=01291">--%>
    <%--<link rel="stylesheet" href="tccss/style.css?v=1?v=01291">--%>
    <%--<link rel="stylesheet" href="tccss/member.css?v=01291">--%>
    <%--<link rel="stylesheet" href="tccss/order3.css?v=01291">--%>
        <%@include file="/WEB-INF/view/common/script_tc.jsp"%>
    <%@include file="/WEB-INF/view/common/tc_css.jsp"%>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no" name="format-detection">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
        <title>分类</title>
</head>
<body>
<header class="header">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.go(-1);">返回</a>
            <div class="tit">分类</div>
            <div class="sousuo" id="sousou"><img src="<c:url value="/resources/tcimages/sou.png"/>"></div>
        </div>
    </div>
</header>
<div style="overflow: hidden;position: fixed;width: 100%;z-index: 10000;top:0px;">
    <div class="order_top_count" style="display:none;">
        <div class="order_top">
            <div class="order_a_l">
                <div id="nav-left_ll"><img src="<c:url value="/resources/tcimages/order_top_l.png" />"></div>
            </div>
            <div class="order_sou">
                <form action="/dmz/pmall/index" method="get" id="searchform" name="searchform">
                    <input name="entity.name" id="keyword" placeholder="搜索商品" type="text" value="">
                    <span class="pro_sou" style="cursor: pointer;" onclick="searchproduct();"><img src="<c:url value="/resources/tcimages/Search.png"/> "></span>
                </form>
            </div>
        </div>
    </div>
</div>
<!--
<div class="sou_count">
	<div class="sou_cou_i sou_none">
			<div class="sou_cou_h sou_cou_h1">猜你喜欢</div>
			<a href="http://wx.aimbeauty.cn/product.html?cps_id=92491&amp;k=%E7%B2%BE%E5%8D%8E"><div class="sou_cou_h2">精华</div></a>
	</div>
	<div class="sou_cou_i">
			<a href="http://wx.aimbeauty.cn/product.html?cps_id=92491&amp;k=%E9%9D%A2%E8%86%9C"><div class="sou_cou_h">面膜</div></a>
			<a href="http://wx.aimbeauty.cn/product.html?cps_id=92491&amp;k=%E6%B4%81%E9%9D%A2"><div class="sou_cou_h2">洁面</div></a>
	</div>
	<div class="sou_cou_i ">
			<a href="http://wx.aimbeauty.cn/product.html?cps_id=92491&amp;k=%E9%98%B2%E6%99%92%E9%9C%9C"><div class="sou_cou_h">防晒霜</div></a>
			<a href="http://wx.aimbeauty.cn/product.html?cps_id=92491&amp;k=%E8%A1%A5%E6%B0%B4"><div class="sou_cou_h2">补水</div></a>
	</div>
</div>
-->

<div class="container">
    <div class="row" id="row_5">
        <div class="sort-arat" style="margin-top: 10px;">


        </div>

        <div class="mt10 white-bg">
            <h4 class="sort-tit">品牌分类</h4>
            <div class="sort-arat brand-areat">
                <ul>

                    <c:forEach items="${list}" var="l">
                        <li>
                            <c:if test="${l.type==0}">
                                <a href="<c:url value="/dmz/pmall/tc/type.html?parentType=${l.id}"/>">
                                    <img alt="${l.name}" src="${l.imgUrl}" style="height: 39px;" />
                                </a>
                            </c:if>
                            <c:if test="${l.type==1}">
                                <a href="<c:url value="/dmz/pmall/index.html?entity.mallGoodsType.id=${l.id}"/>">
                                    <img alt="${l.name}" src="${l.imgUrl}" style="height: 39px;" />
                                </a>
                            </c:if>
                        </li>
                    </c:forEach>


                </ul>
            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="foot-con">
        <div class="foot-con_2">
            <a href="<c:url value="/dmz/pmall/tc/index.html"/>" class="active">
                <i class="navIcon home"></i>
                <span class="text">首页</span>
            </a>
            <%--<a href="category.html">--%>
            <a href="<c:url value='/dmz/pmall/tc/index.html'/>">
                <i class="navIcon sort"></i>
                <span class="text">分类</span>
            </a>
            <%--<a href="shopcart.html">--%>
            <a href="/pmall/shopcart/index.html">
                <i class="navIcon shop"></i>
                <span class="text">购物车<span class="badge" id="shopcartQuantity">${pmshopcart.quantity}</span></span>
            </a>
            <%--<a href="userhome.html" >--%>
            <a href="<c:url value='/vmall/uc//index.html'/> " >
                <i class="navIcon member"></i>
                <span class="text">用户中心</span>
            </a>
        </div>
    </div>
</footer>
</body>
</html>
