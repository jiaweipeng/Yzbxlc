<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户充值列表</title>
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
		<li class="active"><a>用户充值列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/mtrecharge/mtRecharge/userlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>电话号码：</label>
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
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
				<th>用户名</th>
				<th>姓名</th>
				<th>联系电话</th>
				<th>充值总金额</th>
				<shiro:hasPermission name="mtrecharge:mtRecharge:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="mtUser">
		<c:if test="${!empty fns:getTotalipsTrdAmt(mtUser.id)}">
			<tr>
				<td>
					${mtUser.userNickName}
				</td>
				<td>
					${mtUser.userRealName }
				</td>
				<td>
					${mtUser.userTel}
				</td>
				<td>
					${fns:getTotalipsTrdAmt(mtUser.id)}
				</td>
				<shiro:hasPermission name="mtrecharge:mtRecharge:view"><td>
    				<a href="${ctx}/mtrecharge/mtRecharge/list?id=${mtUser.id}">查询充值列表</a>
				</td></shiro:hasPermission>
			</tr>
		</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>