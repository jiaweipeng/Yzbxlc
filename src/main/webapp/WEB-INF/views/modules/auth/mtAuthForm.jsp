<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>认证管理</title>
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
		<li><a href="${ctx}/auth/mtAuth/list?type=5">认证列表</a></li>
		<li class="active"><a href="${ctx}/auth/mtAuth/form?id=${mtAuth.id}">认证<shiro:hasPermission name="auth:mtAuth:edit">${not empty mtAuth.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="auth:mtAuth:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtAuth" action="${ctx}/auth/mtAuth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		
	<!-- 实名认证 -->
	<c:if test="${type==1}">
	
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 修改用户名--------------------------------------------------------------------------------------------  -->
				<input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idcard" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<!-- 隐藏 
		<div class="control-group">
			<label class="control-label">身份证正反面图片：</label>
			<div class="controls">
				<form:hidden id="picture" path="picture" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="picture" type="files" uploadPath="/auth/mtAuth" selectMultiple="true"/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
</c:if>
		
	<!-- 银行卡认证 -->
	<c:if test="${type==2}">	
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 隐藏 
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			-->
			    <input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户银行：</label>
			<div class="controls">
				<form:input path="depositBank" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行支行名称：</label>
			<div class="controls">
				<form:input path="branchBankName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="bankCard" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</c:if>
		
	<!-- 手机认证 -->
	<c:if test="${type==3}">
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 隐藏 
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			-->
			<input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
     </c:if>
		
	<!-- 现场认证 -->
	<c:if test="${type==4}">
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 隐藏 
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			-->
			<input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</c:if>
		
		
	<!-- 视频认证 -->
	<c:if test="${type==5}">
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 隐藏 
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-xlarge  digits required"/>
			-->
			    <input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
					
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频文件：</label>
			<div class="controls">
				<form:hidden id="videoFile" path="videoFile" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				<sys:ckfinder input="videoFile" type="files" uploadPath="/auth/mtAuth" selectMultiple="true"/>
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</c:if>
		
	<!-- 邮箱认证 -->
	<c:if test="${type==6}">
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
			<!-- 隐藏 
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			-->
			<input name="userNickName" readonly="readonly" type="text" value="${fns:getmtUserById(mtAuth.userid).userNickName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱号：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<form:select path="authState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证类型：</label>
			<div class="controls">
				<form:select path="authType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="10" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="auth:mtAuth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</c:if>
	
	</form:form>
	
</body>
</html>