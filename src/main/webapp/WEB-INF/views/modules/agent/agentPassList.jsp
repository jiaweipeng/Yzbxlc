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
		<li class="active"><a href="${ctx}/mtuser/mtUser/agentList">经纪人列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/mtuser/mtUser/agentPassList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="userIdNumber" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>用户电话：</label>
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>经济人号码</th>
				<th>真实姓名</th>
				<th>用户类型</th>
				<th>用户状态</th>
				<th>身份证号</th>
				<th>用户电话</th>
				<th>删除标记</th>
				<th>是否借款中</th>
				<shiro:hasPermission name="mtuser:mtUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUser">
			<tr>
				<td><a href="${ctx}/mtuser/mtUser/form?id=${mtUser.id}">
					${mtUser.userNickName}
				</a></td>
				<td>
					${mtUser.userAgentId}
				</td>
				<td>
					${mtUser.userRealName}
				</td>
				<td>
					${fns:getDictLabel(mtUser.userType, 'user_type', '')}
				</td>
				<td>
					${fns:getDictLabel(mtUser.userStatus, 'mtuser_status', '')}
				</td>
				<td>
					${mtUser.userIdNumber}
				</td>
				<td>
					${mtUser.userTel}
				</td>
				<td>
					${fns:getDictLabel(mtUser.delFlag, 'del_flag', '')}
				</td>
				<td>
					${fns:getDictLabel(mtUser.isInBorrowing, 'is_in_borrowing', '')}
				</td>
				<shiro:hasPermission name="mtuser:mtUser:edit"><td>
				<a href="${ctx}/mtuser/mtUser/agentprolist?useragentid=${mtUser.userAgentId}">产品列表</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>