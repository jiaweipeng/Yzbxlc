<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
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
		<li><a href="${ctx}/pact/mtPact/">协议列表</a></li>
		<li class="active"><a href="${ctx}/pact/mtPact/form?id=${mtPact.id}">协议<shiro:hasPermission name="pact:mtPact:edit">${not empty mtPact.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pact:mtPact:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtPact" action="${ctx}/pact/mtPact/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>                 
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">标号：</label>
			<div class="controls">
				<form:input path="pid" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>	  
		<div class="control-group">
			<label class="control-label">协议编号：</label>
			<div class="controls">
				<form:input path="pactId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资人姓名：</label>
			<div class="controls">
				<form:input path="investId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人姓名：</label>
			<div class="controls">
				<form:input path="borrowId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">协议类型：</label>
			<div class="controls">
				<form:select path="pactType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pact_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议状态：</label>
			<div class="controls">
				<form:select path="pactState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pact_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<!-- 协议连接地址 -->
		<div class="control-group">
			<label class="control-label">协议连接地址：</label>
			<div class="controls">
				<form:input path="pactSite" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">协议签订时间：</label>
			<div class="controls">
				<input name="pactSignTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${mtPact.pactSignTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pact:mtPact:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>