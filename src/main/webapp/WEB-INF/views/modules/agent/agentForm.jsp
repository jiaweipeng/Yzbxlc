<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经纪人审核管理</title>
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
		<li><a href="${ctx}/mtuser/mtUser/agentList">经纪人审核</a></li>
		<li class="active"><a href="${ctx}/mtuser/mtUser/agentform?id=${mtUser.id}">经纪人审核<shiro:hasPermission name="mtuser:mtUser:edit">${not empty mtUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mtuser:mtUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtUser" action="${ctx}/mtuser/mtUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="userNickName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户邮箱：</label>
			<div class="controls">
				<form:input path="userMail" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码：</label>
			<div class="controls">
				<form:input path="userPassword" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户交易密码：</label>
			<div class="controls">
				<form:input path="userPayPassword" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经济人号码：</label>
			<div class="controls">
				<form:input path="userAgentId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户头像：</label>
			<div class="controls">
				<form:hidden id="userHeadImg" path="userHeadImg" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="userHeadImg" type="files" uploadPath="/mtuser/mtUser" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户状态：</label>
			<div class="controls">
				<form:select path="userStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('mtuser_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实名认证：</label>
			<div class="controls">
				<form:select path="isAuth" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡认证：</label>
			<div class="controls">
				<form:select path="isBindBank" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机认证：</label>
			<div class="controls">
				<form:select path="isBindTel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱认证：</label>
			<div class="controls">
				<form:select path="isBindMail" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后登录时间：</label>
			<div class="controls">
				<input name="userLastLoginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${mtUser.userLastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户Ip：</label>
			<div class="controls">
				<form:input path="userIp" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户浏览器：</label>
			<div class="controls">
				<form:input path="userBrowser" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户系统：</label>
			<div class="controls">
				<form:input path="userSystem" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生日期：</label>
			<div class="controls">
				<input name="userBrith" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${mtUser.userBrith}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户性别：</label>
			<div class="controls">
				<form:select path="userSex" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系人：</label>
			<div class="controls">
				<form:input path="userSosName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系人电话：</label>
			<div class="controls">
				<form:input path="userSosTel" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls">
				<form:select path="userType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="userIdNumber" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户电话：</label>
			<div class="controls">
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户余额：</label>
			<div class="controls">
				<form:input path="userAccount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频认证：</label>
			<div class="controls">
				<form:select path="isVideoAuth" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现场认证：</label>
			<div class="controls">
				<form:select path="isSceneAuth" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否借款中：</label>
			<div class="controls">
				<form:select path="isInBorrowing" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_in_borrowing')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
				<form:select path="eduBackground" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_edu_bg')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">婚姻状况：</label>
			<div class="controls">
				<form:select path="maritalStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_married')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作情况：</label>
			<div class="controls">
				<form:textarea path="jobDescription" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">每月收入：</label>
			<div class="controls">
				<form:select path="monthIncome" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_income')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">户籍地址：</label>
			<div class="controls">
				<form:textarea path="censusRegisterAddr" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现居地址：</label>
			<div class="controls">
				<form:textarea path="currentAddress" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mtuser:mtUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>