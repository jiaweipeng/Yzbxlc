<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>协议管理</title>
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
		<li class="active"><a href="${ctx}/pact/mtPact/">协议列表</a></li>
		<!-- 隐藏 
		<shiro:hasPermission name="pact:mtPact:edit"><li><a href="${ctx}/pact/mtPact/form">协议添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="mtPact" action="${ctx}/pact/mtPact/list?pid=${pactpid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<!-- 隐藏 
			<li><label>标号：</label>
				<form:input path="pid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
		-->
		<!-- 投资人查询
			<c:if test="${pacttype==1}">
		
			<li><label>协议编号：</label>
				<form:input path="pactId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
				<li><label>投资人姓名</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
				</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			</c:if>
		 -->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标号</th>
				<th>协议编号</th>
				<!-- 隐藏 
				<th>投资人姓名</th>
				-->
				<th>借款人姓名</th>
				<!-- 隐藏 
				<th>协议类型</th>
				-->
				<th>协议状态</th>
				<th>协议签订时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pact:mtPact:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtPact">
			<tr>
		
				<!-- 标号 -->
				<td>
					${mtPact.pid}
				</td>
				<!-- 协议编号 -->
				<td>
					${mtPact.pactId}
				</td>
				<!-- 投资人和借款人姓名
				<td>
					${fns:getmtUserById(mtPact.investId).userRealName}
					
				</td>
				 -->
				<td>
					${fns:getmtUserById(mtPact.borrowId).userRealName}
					
				</td>
				<!-- 协议类型和状态
				<td>
					${fns:getDictLabel(mtPact.pactType, 'pact_type', '')}
				</td>
				 -->
				<td>
					${fns:getDictLabel(mtPact.pactState, 'pact_state', '')}
				</td>
				<!-- 协议签订时间 -->
				<td>
					<fmt:formatDate value="${mtPact.pactSignTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${mtPact.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtPact.remarks}
				</td>
				<shiro:hasPermission name="pact:mtPact:edit"><td>
					<a href="${mtPact.pactSite}">查看协议</a>
    				<a href="${ctx}/pact/mtPact/form?id=${mtPact.id}">修改</a>
					<a href="${ctx}/pact/mtPact/delete?id=${mtPact.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>