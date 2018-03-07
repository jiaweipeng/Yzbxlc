<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var isChecked = false;
					var length = $(document).find(".from-mode").length;
					for(var i=0; i<length; i++) {
						// 判断选项是否都选中了
						var isChe = $(document).find(".from-mode").get(i).checked;
						if(isChe == true) {
							isChecked = true;
						}
					}
					if(isChecked == true) {
						loading('正在提交，请稍等...');
						form.submit();
					} else {						
						alert('请选择发送方式');
					}
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
		<li><a href="${ctx}/notice/mtNotice/ulist">客户列表</a></li>
		<li class="active"><a href="${ctx}/notice/mtNotice/uform?id=${mtUser.id}">发送通知<shiro:lacksPermission name="mtuser:mtUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtNotice" action="${ctx}/notice/mtNotice/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送方式：</label>
			<div class="controls" >
				<input class="from-mode" name="site" type="checkbox" /><label for="url">站内信</label>
				<input class="from-mode" name="phone" type="checkbox" /><label for="url">手机短信</label>
				<input class="from-mode" name="email" type="checkbox" /><label for="url">电子邮件</label>
			</div>
		</div>
		<div class="form-actions">
			<input type="hidden" name="uid" value="${uid}" />
			<shiro:hasPermission name="notice:mtNotice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 送"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>