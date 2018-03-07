<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转账列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:choose> 
			<c:when test="${show == true}"> 
				<li><a href="javascritp:void(0);" onclick="history.go(-1)">资金明细详情</a></li>
				<li class="active"><a href="${ctx}/mtloanrd/mtLoanrd/form?id=${mtLoanrd.id}">查看全部</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${ctx}/mtloanrd/mtLoanrd/details">资金明细详情</a></li>
				<li class="active"><a href="${ctx}/mtloanrd/mtLoanrd/form?id=${mtLoanrd.id}">查看全部</a></li>
			</c:otherwise>
		</c:choose>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtLoanrd" action="${ctx}/mtloanrd/mtLoanrd/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<input type="text" htmlEscape="false" value="${fns:getProductByGrade(mtLoanrd.projectNo).title}" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户订单号：</label>
			<div class="controls">
				<form:input path="merBillNo" htmlEscape="false"  class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转出方姓名：</label>
			<div class="controls">
				<input type="text" htmlEscape="false" value="${fns:findUser(mtLoanrd.outipsacctno).userRealName}" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转入方姓名：</label>
			<div class="controls">
				<input type="text" htmlEscape="false" value="${fns:findUser(mtLoanrd.inipsacctno).userRealName}" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账订单号：</label>
			<div class="controls">
				<form:input path="ipsbillno" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账金额</label>
			<div class="controls">
				<form:input path="ipstrdamt" htmlEscape="false" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账类型</label>
			<div class="controls">
				<input type="text" htmlEscape="false" value="${fns:fundType(mtLoanrd.transferType)}" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账状态</label>
			<div class="controls">
				<input type="text" htmlEscape="false" value="${fns:getDictLabel(mtLoanrd.trdstatus, 'loanrd_trdStatus', '')}" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账处理时间：</label>
			<div class="controls">
				<input name="ipsdotime" type="text" class="input-xlarge" value="<fmt:formatDate value="${mtLoanrd.ipsdotime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>