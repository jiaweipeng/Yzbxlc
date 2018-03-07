<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/auth/mtAuth/ulist">客户列表</a></li>
	<!-- 隐藏 
		<shiro:hasPermission name="mtuser:mtUser:edit"><li><a href="${ctx}/mtuser/mtUser/form">客户添加</a></li></shiro:hasPermission>
	-->
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/auth/mtAuth/ulist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<!-- 隐藏 
			<li><label>身份证号：</label>
				<form:input path="userIdNumber" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>用户电话：</label>
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			-->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a href="javascript:" class="btn btn-primary" onclick="self.location=document.referrer;">返回</a> </li> 
			 <li class="btns"> <a href="javascript:location.reload()" class="btn btn-primary">刷新</a></li >
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<!--  
				<th>用户名</th>
			-->
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>实名认证</th>
				<th>银行卡认证</th>
				<th>手机认证</th>
				<th>现场认证</th>
				<th>视频认证</th>
				<th>邮箱认证</th>
				
				<!-- 隐藏 
				<shiro:hasPermission name="mtuser:mtUser:edit"><th>操作</th></shiro:hasPermission>
				-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUser">
		<!-- 
			<tr>
				<td><a href="${ctx}/mtuser/mtUser/form?id=${mtUser.id}">
					
				</a></td>
			 
				<td>
					${mtUser.userAgentId}
				</td>
			-->
			<!-- 用户名 -->
				<td>
					${mtUser.userNickName}
				</td>
				<td>
					${mtUser.userRealName}
				</td>
				<!-- 手机号 -->
				<td>
					${mtUser.userTel}
				</td>
				
				<!-- 已认证和未认证 需要带上颜色 绿色和红色 -->
				<td>
					<c:if test="${mtUser.isAuth==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isAuth, 'auth_state', '')}</font> 
					</c:if>
					
					<c:if test="${mtUser.isAuth==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isAuth, 'auth_state', '')}</font> 
					</c:if>
				</td>
				<td>
					<c:if test="${mtUser.isBindBank==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isBindBank, 'auth_state', '')}</font>
					</c:if>
					
					<c:if test="${mtUser.isBindBank==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isBindBank, 'auth_state', '')}</font>
					</c:if>
				</td>
				<td>
					<c:if test="${mtUser.isBindTel==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isBindTel, 'auth_state', '')}</font>
					</c:if>
					
					<c:if test="${mtUser.isBindTel==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isBindTel, 'auth_state', '')}</font>
					</c:if>
				</td>
				<!-- 现场认证 -->
				<td>
					<c:if test="${mtUser.isSceneAuth==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isSceneAuth, 'auth_state', '')}</font>
					</c:if>
					
					<c:if test="${mtUser.isSceneAuth==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isSceneAuth, 'auth_state', '')}</font>
					</c:if>
				</td>
				<!-- 视频认证 -->
				<td>
					<c:if test="${mtUser.isVideoAuth==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isVideoAuth, 'auth_state', '')}</font>
					</c:if>
					
					<c:if test="${mtUser.isVideoAuth==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isVideoAuth, 'auth_state', '')}</font>
					</c:if>
				</td>
				<td>
					<c:if test="${mtUser.isBindMail==1}">
					<font color="#00CC00">${fns:getDictLabel(mtUser.isBindMail, 'auth_state', '')}</font>
					</c:if>
					
					<c:if test="${mtUser.isBindMail==2}">
					<font color="#FF0000">${fns:getDictLabel(mtUser.isBindMail, 'auth_state', '')}</font>
					</c:if>
				</td>

				<!-- 隐藏 
				<shiro:hasPermission name="mtuser:mtUser:edit"><td>
				
    				<a href="${ctx}/mtuser/mtUser/form?id=${mtUser.id}">修改</a>
					<a href="${ctx}/mtuser/mtUser/delete?id=${mtUser.id}" onclick="return confirmx('确认要删除该客户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>