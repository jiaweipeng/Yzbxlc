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
		<li class="active"><a href="${ctx}/mtuser/mtUser/">客户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/mtuser/mtUser/userlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>电话号:</label>
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
		
			<li><label style="width: 91px">注册开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUser.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			
			<li><label style="width: 91px">注册结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUser.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>身份证号</th>
				<th>用户电话</th>
				<th>账户余额</th> 
				<th>冻结资金</th> 
				<th>注册时间</th>
				<shiro:hasPermission name="mtuser:mtUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUser">
			<tr>
				<td>
					${mtUser.userNickName}
				</td>
				<td>
					${mtUser.userRealName}
				</td>
				<td>
					${mtUser.userIdNumber}
				</td>
				<td>
					${mtUser.userTel}
				</td>
				<td>
					${mtUser.userAccount}
				</td>
				<td>
					${fns:findFreeze(mtUser.ipsAcctNo)}
				</td>
				<td>
					<fmt:formatDate value="${mtUser.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="mtuser:mtUser:edit"><td>
    				<a href="${ctx}/mtuser/mtUser/form?id=${mtUser.id}">修改</a>
					<a href="${ctx}/mtuser/mtUser/delete?id=${mtUser.id}" onclick="return confirmx('确认要删除该客户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>