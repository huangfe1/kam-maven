<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/common.jsp" %>
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
    <%@include file="/WEB-INF/view/common/script_tc.jsp" %>
    <%@include file="/WEB-INF/view/common/tc_css.jsp" %>
        <%@include file="/WEB-INF/view/common/head_css.jsp"%>
        <link href="http://www.zmz365.com:8081/data/button.css" rel="stylesheet">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no" name="format-detection">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <title>首页</title>

    <style type="text/css">
        .productItem{
            padding-right:0px !important;
            padding-left:0px !important;
        }
        .productItem .thumbnail{
            border: solid 1px #eaeae8 !important;
            border-radius: 0 !important;
        }

        .productItem:first-child>.thumbnail{
            border-right:none !important;
        }

        .productItem .caption{
            padding:0px 9px !important;
        }

        .product_img {
            width: 100%;
            min-height:150px;
            height:150px;
            width:150px;
            max-width: 150px !important;
        }
        .media a{
            display:block;
            /*width:150px;*/
        }
        .media{
            border-bottom:1px solid #e0e0e0;
        }
        .media{
            font-family: Tahoma, Helvetica, Arial, "Microsoft Yahei", STXihei,
            sans-serif;
        }
        a:hover {
            text-decoration: none !important;
        }
        .tab_tit{
            border-bottom: 1px solid #e5e5e5;
        }
    </style>

</head>
<body>
<header class="header">
    <div class="fix_nav">
        <div style="max-width:768px;margin:0 auto;height: 44px;position: relative;background:#000000;">
            <form action="<c:url value="/dmz/pmall/index"/>" method="get" id="searchform" name="searchform">
                <div class="navbar-search left-search">
                    <input type="text" id="keyword" name="entity.name" value="${parameter.entity.name}"
                           placeholder="搜索商品" class="form-control">
                </div>
                <div class="nav-right">
                    <input type="button" value="搜索" onclick="searchproduct();" class="img-responsive"
                           style="text-align:center;background:#ccc;border-radius: 5px;border:none;height:34px;vertical-align:middle;clear:both;padding:0px;width:42px;"/>
                </div>
            </form>
        </div>
    </div>
</header>

<div class="container">
    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul>
                    <li class="on">1</li>
                    <li class="on">2</li>
                    <li class="on">3</li>
                </ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                    <ul style="width: 3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
                        <li style="display: table-cell; vertical-align: top; width: 768px;min-height: 269px">
                            <%--<a href="http://m.legendshop.cn/m_search/list?categoryId=36" target="_blank">--%>
                            <a href="<c:url value='/dmz/pmall/tc/index.html'/> ">
                                <img src="<c:url value='/resources/images/tch1.jpg'/>" alt="女包"
                                     ppsrc="img/0da8eb94-0159-4700-a6a5-bc007da5a853.jpg">
                            </a>
                        </li>
                        <li style="display: table-cell; vertical-align: top; width: 768px;">
                            <a href="<c:url value='/dmz/pmall/tc/index.html'/> ">
                                <img src="<c:url value='/resources/images/tch2.jpg'/> " alt="女鞋"
                                     ppsrc="img/61647775-f5d0-4cfe-b84f-96060eb8e2e3.jpg">
                            </a>
                        </li>
                        <li style="display: table-cell; vertical-align: top; width: 768px;">
                            <a href="<c:url value='/dmz/pmall/tc/index.html'/> ">
                                <img src="<c:url value='/resources/images/tch3.jpg'/> " alt="服装"
                                     ppsrc="img/efa1dc7b-1375-4876-acae-e344cae7d55c.jpg">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <%--<script charset="utf-8" src="tcjs/TouchSlide.js"></script>--%>

    <script type="text/javascript">

        TouchSlide({
            slideCell: "#slide",
            titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
            mainCell: ".bd ul",
            effect: "left",
            autoPlay: true,//自动播放
            autoPage: true, //自动分页
            switchLoad: "_src" //切换加载，真实图片路径为"_src"
        });
    </script>
    <div class="row category">
        <%--<a href="/m_search/list?param.hot='Y'" class="col-xs-3">--%>
        <a href="<c:url value="/pmall/uc/index.html"/>" class="col-xs-3">
            <img class="img-responsive" src="<c:url value='/resources/tcimg/icon_rm.png'/>">
            <h4>我的订单</h4>
        </a>
        <%--<a href="/m_search/list?param.commend='Y'" class="col-xs-3">--%>
        <a href="<c:url value='/dmz/pmall/index.html'/> " class="col-xs-3">
            <img class="img-responsive" src="<c:url value='/resources/tcimg/icon_tm.png'/>">
            <h4>所有产品</h4>
        </a>
            <c:if test="${param.mallType==0}">
                <a href="<c:url value='/dmz/pmall/tc/index.html'/> " class="col-xs-3">
                    <img class="img-responsive" src="<c:url value='/resources/tcimg/theme.png'/>">
                    <h4>置换系统</h4>
                </a>
            </c:if>
            <c:if test="${param.mallType!=0}">
                <a href="<c:url value='/dmz/pmall/tc/index.html?mallType=0'/> " class="col-xs-3">
                    <img class="img-responsive" src="<c:url value='/resources/tcimg/theme.png'/>">
                    <h4>优惠商城</h4>
                </a>
            </c:if>

        <a href="<c:url value='/dmz/vmall/index.html'/> " class="col-xs-3">
            <img class="img-responsive" src="<c:url value='/resources/tcimg/icon_pp.png'/> ">
            <h4>代理商城</h4>
        </a>
    </div>

<div id="contentWrapper">
    <div class="row">
    <div class="tb_box" >
        <h2 class="tab_tit"> <a class="more" href="<c:url value='/dmz/pmall/tc/type.html'/> ">更多分类</a>选择分类</h2>
    <%--<c:forEach items="${pTypes}" begin="1" end="9" var="p">--%>
        <%--<div class="col-xs-4">--%>
            <%--<button type="button" class="btn btn-info">（一般信息）Info</button>--%>
        <%--</div>--%>
    <%--</c:forEach>--%>
    </div>
    </div>

    <div class="row">
        <div class="col-xs-12" style="text-align: center">
            <div id="tagsList">
                <c:forEach items="${pTypes}" begin="0" end="8" var="type" varStatus="sta">
                <c:if test="${sta.index==0}">
                <div class="row">
                    </c:if>
                    <div  class="col-xs-4 col" >
                        <button  data-id="${type.id}" type="button" style="margin:5px 0;width: 100px;line-height: 30px"
                                 class="button blue">${type.name}</button>
                    </div>
                    <c:if test="${sta.index==8}">
                </div>
                </c:if>
                <c:if test="${sta.index%3==0&&sta.index%3!=0}">
            </div>
            <c:if test="${sta.index!=8}">
            <div class="row">
                </c:if>
                </c:if>
                </c:forEach>
        </div>
    </div>
    </div>

    <!--产品块-->

        <div class="row">
            <div class="tb_box">
            <h2 class="tab_tit ">
                <%--<a class="more" href="<c:url value='/dmz/pmall/index.html'/> ">更多</a>--%>
                今日主推</h2>
        </div>
        </div>

        <c:forEach items="${ztGoods}" var="zd">
            <a href="<c:url value='/dmz/pmall/detail.html?id=${zd.id}'/>">
                <div class="row">
                <div class="col-md-12 col-xs-12 productItem">
                    <div class="media">
                        <div class="media-left media-middle"><img class="media-object product_img detailBtn" data-id="${zd.id}" src="${zd.imgUrl}" alt=""></div>
                        <div class="media-body">
                            <h5><strong>${zd.name}</strong></h5>
                            <h6>${zd.spec}</h6>
                            <h6><span class="fa fa-rmb"></span>${zd.pointPrice}置换券</h6>
                            <h6><span class="fa fa-rmb"></span>${zd.moneyPrice}现金</h6>
                            <h6>
                                <del>
                                    <small class="text-muted">原价:${zd.price}</small>
                                </del>
                            </h6>
                            <h6><span>库存:</span>${zd.stockQuantity}</h6>

                        </div>
                        <div   class="btn btn-link buyBtn pull-right" style="color:red!important;position: absolute;bottom:20px;right: 20px" role="button" data-id="23">
                            <span class="fa fa-shopping-cart fa-2x"></span>
                        </div>
                    </div>

                </div>
                </div>
            </a>
        </c:forEach>




        <div class="row">
            <div class="tb_box" >
            <h2 class="tab_tit" >分类:${pNames}</h2>
        </div>
    </div>



    <%--<!--产品块-->--%>
    <%--<div class="tb_box">--%>
    <%--<h2 class="tab_tit">--%>
    <%--<a class="more" href="http://m.legendshop.cn/m_search/list?categoryId=38">更多</a>--%>
    <%--服饰鞋帽</h2>--%>

    <%--<div class="tb_type clearfix"><a class="tb_floor" href="views.html">--%>
    <%--<img src="tcimg/0bbbb6bf-0d00-45c0-92f7-347377f2edb5.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--<a class="th_link" href="views.html">--%>
    <%--<img class="tb_pic" src="tcimg/0d86960d-20b5-4dd3-afee-8453b5ea5e95.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--<a class="th_link tb_last" href="views.html">--%>
    <%--<img class="tb_pic" src="tcimg/1e13498f-419d-4ebd-a3b2-d0ad95ceaa39.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<!--产品块-->--%>
    <%--<div class="tb_box">--%>
    <%--<h2 class="tab_tit">--%>
    <%--<a class="more" href="http://m.legendshop.cn/m_search/list?categoryId=35">更多</a>--%>
    <%--数码办公</h2>--%>

    <%--<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">--%>
    <%--<img src="tcimg/bd6771d9-f220-454d-83b2-d58d2046d23a.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--<a class="th_link" href="views.html">--%>
    <%--<img class="tb_pic" src="tcimg/04ef50d9-97f0-4fea-8359-ee21376df392.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--<a class="th_link tb_last" href="views.html">--%>
    <%--<img class="tb_pic" src="tcimg/d07a93ef-cdff-4b21-a12b-d817890aa6d6.jpg" style="width:100%;">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

</div>
<!--
<div class="foot_index">
	<div class="foot_index_tit">Aim Beauty Limited(HongKong)</div>
	<div class="foot_index_rx">服务热线：020-87774389</div>
</div>
-->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body" id='alertMessageBody'></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info btn-block quitBtn" tabIndex="26"
                            id="quitBtn" data-dismiss="modal" name="quitBtn" value="login"
                            tabindex="4" data-loading-text="正在关闭......">
                        <span class="glyphicon glyphicon-remove-sign">&nbsp;</span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

<footer class="footer">
    <div class="foot-con">
        <div class="foot-con_2">
            <a href="#" class="active">
                <i class="navIcon home"></i>
                <span class="text">首页</span>
            </a>
            <%--<a href="category.html">--%>
            <a href="<c:url value='/dmz/pmall/tc/type.html'/>">
                <i class="navIcon sort"></i>
                <span class="text">分类</span>
            </a>
            <%--<a href="shopcart.html">--%>
            <a href="/pmall/shopcart/index.html">
                <i class="navIcon shop"></i>
                <span class="text">购物车<span class="badge" id="shopcartQuantity">${pmshopcart.quantity}</span></span>
            </a>
            <%--<a href="userhome.html" >--%>
            <a href="<c:url value='/vmall/uc//index.html'/> ">
                <i class="navIcon member"></i>
                <span class="text">用户中心</span>
            </a>
        </div>
    </div>
</footer>

<script type="text/javascript">
    $(document).ready(function () {

        var mallType = "${param.mallType}";

        $(".col>button").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            var url = "<c:url value='/dmz/pmall/tc/index.html?pType='/>" + $(this).attr("data-id");
           if(mallType!=""){
               url=url+"&mallType="+mallType;
           }
            window.location.href = url;
        });

        $("#slide img").each(function () {
            var img_src = $(this).attr("_src");
            $(this).attr("src", img_src);
        });

        $("#contentWrapper").delegate(".buyBtn", "click", function(e) {
            e.preventDefault();
            e.stopPropagation();
            var id = $(this).attr("data-id");
            $.ajax("<c:url value='/pmall/shopcart/add.json'/>", {
                "type" : "POST",
                "data" : {
                    "goodsId" : id
                },
                "complete":function(xhr, ts){
                    if(xhr.status>=200 && xhr.status<300){
                        var m=$.parseJSON(xhr.responseText);
                        if(m.flag=="0"){
                            /* 	$("#alertMessageBody").empty().html("添加到购物车成功");
                             $("#myModal").modal({backdrop:"static",show:true}); */
                            $("#shopcartQuantity").html(m.data).removeClass(animatedClass)
                                .addClass(animatedClass).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function(){
                                $(this).removeClass(animatedClass);
                            });
                        }else{
                            $("#alertMessageBody").empty().html(m.message+",添加购物车操作失败").addClass("text-danger");
                            $("#myModal").modal({backdrop:"static",show:true});
                        }

                    }else{
                        if(xhr.status==401){
                            $("#alertMessageBody").empty().html("尚未登陆,转到登陆界面").addClass("text-danger");
                            $("#myModal").modal({backdrop:"static",show:true});
                            window.location=xhr.getResponseHeader("Location");
                        }else{
                            $("#alertMessageBody").empty().html("添加购物车操作失败").addClass("text-danger");
                            $("#myModal").modal({backdrop:"static",show:true});
                        }
                    }
                }
            });
        });



        var url = "<c:url value="/dmz/pmall/goods/query.json" />";

        var start = 0;
        var length = 8;

        function query() {
            $.ajaxSettings.async = false;
            var type = "${param.pType}";
            if(type==""){
                type="${pType}";
            }
            var param = {
                "start": start,
                "length": length,
                "useDatatables": true,
                "entity.mallGoodsType.id": type,
                "mallType": mallType
                <%--// "entity.name": "${parameter.entity.name}",--%>
                <%--// "entity.goodsType.id": "${parameter.entity.goodsType.id}"--%>
            };
            $.getJSON(url, param, function (goodses) {
                if (goodses.length == 0) {
                    $("#ajax_loading").html("----抱歉到底了,即将上传更多新品----").height("5px").css(" margin: 5px auto 5px;");
                } else {
                    goodses.forEach(function (data, i) {
                        var url = "<c:url value='/dmz/pmall/detail.html?id='/>" + data.id;
                        var img = data.imgUrl;
//                    if (img.indexOf("null")>-1){
//                        continue;
//                        img=goods.imgUrl;
//                    }
                        var tem =" <a href=\"<c:url value='/dmz/pmall/detail.html?id="+data.id+"'/>\">"+
                            "    <div class=\"row\">" +
                            "                <div class=\"col-md-12 col-xs-12 productItem\">" +
                            "                    <div class=\"media\">" +
                            "                        <div class=\"media-left media-middle\"><img class=\"media-object product_img detailBtn\" data-id=\""+data.id+"\" src=\""+data.imgUrl+"\" alt=\"\"></div>" +
                            "                        <div class=\"media-body\">" +
                            "                            <h5><strong>"+data.name+"</strong></h5>" +
                            "                            <h6>"+data.spec+"</h6>" +
                            "                            <h6><span class=\"fa fa-rmb\"></span>"+data.pointPrice+"置换券</h6>" +
                            "                            <h6><span class=\"fa fa-rmb\"></span>"+data.moneyPrice+"</h6>" +
                            "                            <h6>" +
                            "                                <del>" +
                            "                                    <small class=\"text-muted\">原价:"+data.price+"</small>" +
                            "                                </del>" +
                            "                            </h6>" +
                            "                            <h6><span>库存:</span>"+data.stockQuantity+"</h6>" +
                            "                        </div>" +
                            "                        <div   class=\"btn btn-link buyBtn pull-right\" style=\"color:red!important;position: absolute;bottom:20px;right: 20px\" role=\"button\" data-id=\"23\">\n" +
                            "                            <span class=\"fa fa-shopping-cart fa-2x\"></span>" +
                            "                        </div>" +
                            "                    </div>" +
                            "                </div>" +
                            "                </div>";+
                            "                </div>";


//                        var template='<div class="row"><div class="col-md-12 col-xs-12 productItem">'+'<div class="media"><div class="media-left media-middle">'+
//                            '<img class="media-object product_img detailBtn" data-id='+data.id+' src="'+data.imgUrl+'" alt=""></div>'+
//                            '<div class="media-body">'+
//                            '<h5><strong>'+data.name+'</strong></h5>'+
//                            '<h6>'+data.spec+'</h6>'+
//                            '<h6><span class="fa fa-rmb"></span>'+data.pointPrice+'置换券</h6>'+
//                            '<h6><span class="fa fa-rmb"></span>'+data.moneyPrice+'现金</h6>'+
//                            '<h6><del><small class="text-muted">原价:'+data.price+'</small></del></h6>'+
//                            '<h6><span >库存:</span>'+data.stockQuantity+'</h6>'+
//                                '</div>'+
////                                    '<h6><span class="fa fa-rmb">分享利润:</span>'+data.vouchers.substring(0,6)+'</h6>'+
//                            '<div class="btn btn-link buyBtn pull-right" style="color:red!important;position: absolute;bottom:20px;right: 20px" role="button" data-id='+data.id+
//                            '</div><span class="fa fa-shopping-cart fa-2x"></span></div></div>';
                        $(tem).appendTo($("#contentWrapper"));
                    });
                    start += goodses.length;
                }
            });
            $.ajaxSettings.async = true;
        }

        function onScroll() {
            // Check if we're within 100 pixels of the bottom edge of the broser window.
            var winHeight = window.innerHeight ? window.innerHeight : $(window).height(), // iphone fix
                closeToBottom = ($(window).scrollTop() + winHeight > $(document).height() - 100);

            if (closeToBottom) {
                // Get the first then items from the grid, clone them, and add them to the bottom of the grid
                // var $items = $('li', $container),
                //     $firstTen = $items.slice(0, 10).clone().css('opacity', 0);
                // $container.append($firstTen);
                query();

            }
        }

        query();
        $(window).bind('scroll', onScroll);


    });

    function searchproduct() {
        var keyword = document.getElementById("keyword").value;
        if (keyword == undefined || keyword == null || keyword == "") {
            alert("请输入搜索关键字！");
            return false;
        }
        document.getElementById("searchform").submit();
    }
</script>
</body>
</html>

