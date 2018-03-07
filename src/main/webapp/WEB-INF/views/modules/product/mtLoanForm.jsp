<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理管理</title>
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
		<li><a href="${ctx}/product/mtProduct/?type=${type}">产品管理列表</a></li>
		<li class="active"><a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}">产品管理<shiro:hasPermission name="product:mtProduct:edit">${not empty mtProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="product:mtProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtProduct" action="${ctx}/product/mtProduct/loan" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>用户信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group">
							<input type="hidden" value="${mtUser.id}" />
						</div>
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtUser.userNickName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtUser.userRealName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('sex')}" var="item"><c:if test="${mtUser.userSex==item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtUser.userTel}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品标题：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.title}" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款金额：</label>
							<div class="controls">
								<input readonly="readonly" type="text" class="form-control" value="${mtProduct.planmoney}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">收益率：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.hopepercent}" htmlEscape="false" class="input-xlarge required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款类型：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('unit')}" var="item"><c:if test="${mtProduct.unit==item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款周期：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.cycle}" class="form-control"/>
								<span>（月/日）</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结算方式：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('balancetype')}" var="item"><c:if test="${mtProduct.balancetype==item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品状态：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('product_status')}" var="item"><c:if test="${mtProduct.status == item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款收费费率：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.managecost}" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投资收费费率：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.investcost}" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">保证金：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.cashdeposit}" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">担保机构：</label>
							<div class="controls">
								<c:choose> 
									<c:when test="${mtProduct.guarantee == 0}"> 
										<input readonly="readonly" type="text" value="无担保" class="form-control"/>
									</c:when> 
									<c:otherwise> 
										<input readonly="readonly" type="text" value="<c:forEach items="${fns:getGuateeAllList()}" var="item"><c:if test="${mtProduct.guarantee == item.id}">${item.msname}</c:if></c:forEach>" class="input-xlarge"/>
									</c:otherwise> 
								</c:choose>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="product:mtProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确认放款"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>