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
	<form:form id="inputForm" modelAttribute="mtProduct" action="${ctx}/product/mtProduct/issue" method="post" class="form-horizontal">
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
							<input name="uid" type="hidden" value="${mtUser.id}" />
						</div>
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<input name="userNickName" readonly="readonly" type="text" value="${mtUser.userNickName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input name="userRealName" readonly="readonly" type="text" value="${mtUser.userRealName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别：</label>
							<div class="controls">
								<input name="userSex" readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('sex')}" var="item"><c:if test="${mtUser.userSex==item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号：</label>
							<div class="controls">
								<input name="userTel" readonly="readonly" type="text" value="${mtUser.userTel}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出生日期：</label>
							<div class="controls">
								<input name="userBrith" readonly="readonly" type="text" value="<fmt:formatDate value="${mtUser.userBrith}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最高学历：</label>
							<div class="controls">								
								<input name="eduBackground" readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('user_edu_bg')}" var="item"><c:if test="${mtUser.eduBackground == item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">婚姻状况：</label>
							<div class="controls">								
								<input name="maritalStatus" readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('is_married')}" var="item"><c:if test="${mtUser.maritalStatus == item.value}">${item.label}</c:if></c:forEach>" class="form-control"/>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>产品信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">产品标号：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.pid}" htmlEscape="false" maxlength="100" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品标题：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.title}" htmlEscape="false" maxlength="80" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款金额：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.planmoney}" htmlEscape="false" class="input-xlarge  number"/>
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label">收益率：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.hopepercent}" htmlEscape="false" class="input-xlarge "/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">募集开始时间：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input-xlarge" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">募集结束时间：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<fmt:formatDate value="${mtProduct.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input-xlarge" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款类型：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('unit')}" var="item"><c:if test="${mtProduct.unit==item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款周期：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="${mtProduct.cycle}" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>								
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结算方式：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('balancetype')}" var="item"><c:if test="${mtProduct.balancetype == item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">风险程度：</label>
							<div class="controls">								
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('risk')}" var="item"><c:if test="${mtProduct.risk == item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label">产品状态：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('product_status')}" var="item"><c:if test="${mtProduct.status == item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否为质押标：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('yes_no')}" var="item"><c:if test="${mtProduct.type == item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款收费费率：</label>
							<div class="controls">
								<input name="managecost" readonly="readonly" type="text" value="${mtProduct.managecost}" class="input-xlarge" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投资收费费率：</label>
							<div class="controls">
								<input name="investcost" readonly="readonly" type="text" value="${mtProduct.investcost}" class="input-xlarge" />
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label">借款用途：</label>
							<div class="controls">
								<textarea name="userfor" readonly="readonly" htmlEscape="false" rows="4" class="input-xxlarge ">${mtProduct.userfor}</textarea>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>安全措施</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">保证金：</label>
							<div class="controls">
								<input name="cashdeposit" readonly="readonly" type="text" value="${mtProduct.cashdeposit}" class="input-xlarge  number"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">担保机构：</label>
							<div class="controls">
								<input name="guarantee" readonly="readonly" type="text" value="<c:forEach items="${fns:getGuateeAllList()}" var="item"><c:if test="${mtProduct.guarantee == item.id}">${item.msname}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投资期限类型：</label>
							<div class="controls">
								<input readonly="readonly" type="text" value="<c:forEach items="${fns:getDictList('duetime')}" var="item"><c:if test="${mtProduct.duetime == item.value}">${item.label}</c:if></c:forEach>" class="input-xlarge"/>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<div class="form-actions">
			<shiro:hasPermission name="product:mtProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确认发标"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>