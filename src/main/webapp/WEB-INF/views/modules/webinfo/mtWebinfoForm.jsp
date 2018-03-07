<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网站信息管理</title>
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
		<li><a href="${ctx}/webinfo/mtWebinfo/">网站信息列表</a></li>
		<li class="active"><a href="${ctx}/webinfo/mtWebinfo/form?id=${mtWebinfo.id}">网站信息<shiro:hasPermission name="webinfo:mtWebinfo:edit">${not empty mtWebinfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="webinfo:mtWebinfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtWebinfo" action="${ctx}/webinfo/mtWebinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">参数：</label>
			<div class="controls">
				<form:input path="pname" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数值：</label>
			<div class="controls">
				<form:input path="pvalues" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">logo图片：</label>
			<div class="controls">
				<form:hidden id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="logo" type="files" uploadPath="/webinfo/mtWebinfo" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变量名：</label>
			<div class="controls">
				<form:input path="varname" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('webinfo')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			
			
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="webinfo:mtWebinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>