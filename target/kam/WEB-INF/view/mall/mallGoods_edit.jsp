<%@ page import="com.dreamer.util.TokenInfo" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/common.jsp" %>
<div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
        <div class="modal-header bg-primary">
            <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title " id="myModalLabel"><span class="fa fa-edit fa-fw"></span>积分商城信息编辑</h4>
        </div>
        <div class="modal-body">
            <div class="container-fluid">
                <form action="<c:url value='/mall/goods/edit.json'/>" name="editForm" enctype="multipart/form-data"
                      class="form-horizontal" id="editForm" method="post">
                    <div class="row">
                        <div class="col-md-12 col-xs-12">
                            <input type="hidden" name="id" value="${parameter.entity.id}">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">商品名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="editName" tabIndex="10"
                                           name="name" value="${parameter.entity.name}"
                                           placeholder="输入商品名称">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <!--产品的描述白哦题-->
                            <div class="form-group">
                                <label for="shareName" class="col-sm-2 control-label">分享标题</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shareName" tabIndex="10"
                                           name="shareName" value="${parameter.entity.shareName}"
                                           placeholder="分享标题">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <!--产品的描述-->
                            <div class="form-group">
                                <label for="shareDetail" class="col-sm-2 control-label">分享描述</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="shareDetail" tabIndex="10"
                                           name="shareDetail" value="${parameter.entity.shareDetail}"
                                           placeholder="分享描述">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <!--商城分类-->
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商城分类</label>
                                <div class="col-sm-6">
                                    <select name="scType" id="scType"  class="form-control">
                                        <option value="0" <c:if test="${parameter.entity.scType==0}">selected</c:if>>置换系统</option>
                                        <option value="1" <c:if test="${parameter.entity.scType==1}">selected</c:if>>优惠系统</option>
                                    </select>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <!--产品分类-->
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">产品分类</label>
                                <div class="col-sm-6">

                                        <select name="mType" id="mType"  class="form-control">
                                            <%--<option >--%>
                                                <%--选择分类--%>
                                            <%--</option>--%>
                                            <%--<c:forEach items="${types}" var="type">--%>
                                                <%--<option value="${type.id}" <c:if test="${type.id eq parameter.entity.mallGoodsType.id}">selected</c:if>>--%>
                                                        <%--${type.name}--%>
                                                <%--</option>--%>
                                            <%--</c:forEach>--%>
                                        </select>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>



                            <!--产品的利润sa -->
                            <div class="form-group" style="display: none">
                                <label for="profit" class="col-sm-2 control-label">毛利润</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="profit" tabIndex="10"
                                           name="profit" value="0"
                                           placeholder="纯利润">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group">
                                <label for="pointFactor" class="col-sm-2 control-label">商品规格</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="editSpec" tabIndex="11"
                                           name="spec" value="${parameter.entity.spec}"
                                           placeholder="输入商品规格">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group">
                                <label for="img" class="col-sm-2 control-label">产品图片</label>
                                <div class="col-sm-4">
                                    <input type="file" class="form-control" id="img" tabIndex="12"
                                           name="img" accept="image/png,image/jpeg,image/gif" placeholder="产品图片">
                                    <span class="help-block">图片尺寸:200*200px 体积小于50kb</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <!--产品详情页-->
                            <div class="form-group">
                                <label for="detailImg" class="col-sm-2 control-label">产品详情</label>
                                <div class="col-sm-4">
                                    <div id="demo">
                                        <c:set value="${fn:split(parameter.entity.detailImg,'+')}" var="imgs"/>
                                        <c:forEach items="${imgs}" var="val" varStatus="i">
                                            <c:if test="${val!=''}">
                                                <div id="uploadedList_${i.index}" class="upload_append_list">
                                                    <div class="file_bar">
                                                        <div style="padding:5px;"><p class="file_name">${val}</p><span
                                                                class="ufile_del" data-index="${i.index}"
                                                                title="删除"></span>
                                                        </div>
                                                    </div>
                                                    <a style="height:100px;width:120px;" href="#" class="imgBox">
                                                        <div class="uploadImg" style="width:105px"><img
                                                                id="uploadImage_0" class="upload_image"
                                                                src="<%=TokenInfo.IMG_HEAD_PATH%>/dmz/img/goods/${val}"
                                                                style="width:expression(this.width > 105 ? 105px : this.width)">
                                                        </div>
                                                    </a>
                                                    <p id="uploadedProgress_0" class="file_progress"></p>
                                                    <p id="uploadedFailure_0" class="file_failure">上传失败，请重试</p>
                                                    <p id="uploadedSuccess_0" class="file_success"></p>
                                                </div>
                                            </c:if>

                                        </c:forEach>
                                    </div>
                                    <span class="help-block">图片尺寸:200*200px 体积小于50kb</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="currentStock" class="col-sm-2 control-label">原价</label>--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<input type="number" class="form-control" id="editPrice" tabIndex="12" required--%>
                                           <%--name="price" value="${parameter.entity.price}"--%>
                                           <%--placeholder="输入商品价格">--%>
                                    <%--<span class="help-block">非积分+现金消费时的价格</span>--%>
                                <%--</div>--%>
                                <%--<div class="col-md-4 col-xs-4 text-error"></div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label for="currentStock" class="col-sm-2 control-label">原价</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editPrice" tabIndex="12" required
                                           name="price" value="${parameter.entity.price}"
                                           placeholder="输入商品价格">
                                    <span class="help-block">产品的原价格</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group" >
                                <label for="currentPoint" class="col-sm-2 control-label">置换券</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editPointPrice" tabIndex="13"
                                           name="pointPrice" value="${parameter.entity.pointPrice}"
                                           placeholder="置换券">
                                    <span class="help-block">置换券</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group" >
                                <label for="currentBalance" class="col-sm-2 control-label">使用置换券时的价格</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editMoneyPrice" tabIndex="14"
                                           name="moneyPrice" value="${parameter.entity.moneyPrice}"
                                           placeholder="使用置换券时的价格">
                                    <span class="help-block">使用置换券时的价格</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="currentBalance" class="col-sm-2 control-label">返券额度</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editVoucher" tabIndex="15"
                                           name="voucher" value="1" required
                                           placeholder="输入商品返券额">
                                    <span class="help-block">购买此商品的返券额度</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <div class="form-group" style="display: none">
                                <label for="editVouchers" class="col-sm-2 control-label">模式</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="editVouchers" tabIndex="15"
                                           name="vouchers" value="1" required
                                           placeholder="输入商品返券模式">
                                    <span class="help-block">返利制度</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <div class="form-group" style="display: none">
                                <label for="editBenefitPoints" class="col-sm-2 control-label">返福利积分数</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editBenefitPoints" tabIndex="16"
                                           name="BenefitPoints" value="0" required
                                           placeholder="输入商品返券额">
                                    <span class="help-block">购买此商品的返福利积分数</span>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="editStockQuantity" class="col-sm-2 control-label">当前库存</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editStockQuantity" tabIndex="17"
                                           name="stockQuantity" value="${parameter.entity.stockQuantity}"
                                           placeholder="输入商品当前库存">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">是否上架商品</label>
                                <div class="col-sm-6">
                                    <%-- <div class="checkbox">
                                        <label> <input type="checkbox" id="shelf" tabIndex="16"
                                            <c:if test="${parameter.entity.shelf == true }">
                                                checked="checked"
                                            </c:if>
                                            name="shelf">是否上架
                                        </label>
                                    </div> --%>

                                    <label class="radio-inline"> <input type="radio"
                                    <c:if test="${parameter.entity.shelf}">
                                                                        checked="checked"
                                    </c:if>
                                                                        name="shelf" id="inlineRadio1" value="1">
                                        上架
                                    </label> <label class="radio-inline"> <input
                                        type="radio" name="shelf" id="inlineRadio2"
                                <c:if test="${not parameter.entity.shelf}">
                                        checked="checked"
                                </c:if>
                                        value="0"> 下架
                                </label>
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <!--是否活动-->
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">是否搞活动</label>
                                <div class="col-sm-6">
                                    <%-- <div class="checkbox">
                                        <label> <input type="checkbox" id="shelf" tabIndex="16"
                                            <c:if test="${parameter.entity.shelf == true }">
                                                checked="checked"
                                            </c:if>
                                            name="shelf">是否上架
                                        </label>
                                    </div> --%>

                                        <label class="radio-inline"> <input
                                                type="radio" name="activity" id="activity0"
                                        <c:if test="${ parameter.entity.activity!=1}">
                                                checked="checked"
                                        </c:if>
                                                value="0"> 非活动
                                        </label>

                                    <label class="radio-inline"> <input type="radio"
                                    <c:if test="${parameter.entity.activity==1}">
                                                                        checked="checked"
                                    </c:if>
                                                                        name="activity" id="activity1" value="1">
                                        活动
                                    </label>

                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>

                            <div class="form-group">
                                <label for="limitNumer" class="col-sm-2 control-label">活动限制数量</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="limitNumer"
                                           name="limitNumer" value="${parameter.entity.limitNumer}" tabIndex="17"
                                           placeholder="活动限制数量">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error">上次活动时间${parameter.entity.startTime}</div>
                            </div>

                            <div class="form-group">
                                <label for="editOrder" class="col-sm-2 control-label">排列顺序</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="editSequence"
                                           name="sequence" value="${parameter.entity.sequence}" tabIndex="17"
                                           placeholder="输入显示顺序">
                                </div>
                                <div class="col-md-4 col-xs-4 text-error"></div>
                            </div>
                            <!-- </form>-->
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <div class="col-md-6 col-xs-12">
                    <button type="button" class="btn btn-default btn-block quitBtn" tabIndex="26"
                            id="quitBtn" data-dismiss="modal" name="quitBtn" value="login"
                            tabindex="4" data-loading-text="正在返回......">
                        <span class="glyphicon glyphicon-remove-sign">&nbsp;</span>关闭
                    </button>
                </div>
                <div class="col-md-6 col-xs-12">
                    <button type="button" class="btn btn-success btn-block" form="editForm" tabIndex="27"
                            id="saveBtn" name="saveBtn" value="saveBtn" tabindex="4"
                            data-loading-text="正在提交......">
                        <span class="glyphicon glyphicon-floppy-save">&nbsp;</span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/view/common/form.jsp"></jsp:include>
<%@include file="/WEB-INF/view/common/script_hf_upload.jsp" %>
<script type="text/javascript">
    $(function () {
        init();
        $('#mySwitch').on('switch-change', function (e, data) {
            var $el = $(data.el)
                    , value = data.value;
            console.log(e, $el, value);
        });

        buildSelect(${parameter.entity.scType},${parameter.entity.mallGoodsType.id});

        $("#scType").change(function () {
            buildSelect($(this).val(),${parameter.entity.mallGoodsType.id});
        })

    });

    function buildSelect(scType,currentTypeId) {
        <%--var mTypes =$.parseJSON(${types});--%>
        $("#mType").empty();
        var mTypes =${types};
        for(var mt in mTypes){
            var mType = mTypes[mt];
            if(mType.scType!=scType)continue;//如果不是当前类型就跳过
            var option = $("<option>");
            option.val(mType.id);
            option.html(mType.name);

            if(mType.id==currentTypeId){
                option.attr("selected",true);
            }
            option.appendTo($("#mType"));
        }
        }

    function init() {
        $("#editName").focus().select();
        var btn = null;
        $("#editForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    beforeSubmit: function (arr, $form, options) {
                        btn.button("loading");
                    },
                    success: function (responseText,
                                       statusText, xhr, $form) {
                        var m = $.parseJSON(xhr.responseText);
                        btn.button("reset");
                        if (m.flag == "0") {
                            alert("保存成功");
                            $(".quitBtn").click();
//                            $("#searchDT").click();
                            location.reload();
                        } else {
                            alert("保存失败" + m.message);
                        }

                    },
                    error: function (xhr, textStatus,
                                     errorThrown) {
                        btn.button("reset");
                        alert("积分商品保存失败");
                    }
                });
            },
            rules: {
                name: {
                    required: true
                },
                spec: {
                    required: true
                },
                price: {
                    number: true,
                    min: true,
                    required: true
                },
                pointPrice: {
                    required: true,
                    number: true
                },
                moneyPrice: {
                    required: true,
                    number: true
                },
                voucher: {
                    required: true,
                    number: true,
                    min: true
                },
                vouchers: {
                    required: true
                },
                benefitPoints: {
                    required: true,
                    number: true,
                    min: true
                },
                stockQuantity: {
                    number: true
                }
            },
            onkeyup: false,
            messages: {
                name: {
                    required: "商品名必须填写"
                },
                vouchers: {
                    required: "填写返利制度"
                },
                spec: {
                    required: "商品规格必须填写"
                },
                price: {
                    number: "价格必须为数字",
                    min: "价格不能为负数",
                    required: "价格需填写"
                },
                pointPrice: {
                    number: "积分必须为数字",
                    min: "积分不能为负数",
                    required: "所需积分不能为空"
                },
                moneyPrice: {
                    number: "积分价格必须为数字",
                    min: "积分价格不能为负数",
                    required: "积分价格必须填写"
                },
                voucher: {
                    required: "返券额度需填写",
                    min: "返券额度不能为负数",
                    number: "返券额度必须为数字"
                },
                benefitPoints: {
                    required: "返福利积分数需填写",
                    number: "福利积分必须为数字",
                    min: "福利积分数不能为负"
                },
                stockQuantity: {
                    number: "当前库存必须为数字"
                }
            },
            focusInvalid: true,
            errorClass: "text-danger",
            validClass: "valid",
            errorElement: "small",
            errorPlacement: function (error, element) {
                error.appendTo(element.closest("div.form-group")
                        .children("div.text-error"));
            }
        });
        $("#editForm").find("input[type='checkbox']").change(function (e) {
            var $t = $(this);
            var next = $t.next("input[type='hidden']");
            $t.prop("checked") ? next.val(1) : next.val(0);
        });
        $("#saveBtn").click(function (e) {
            btn = $(this).button();
//			$(document.forms["editForm"]).submit();
            ZYFILE.submit();
        });
    }
</script>
