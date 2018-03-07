<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
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
		<li class="active"><a href="${ctx}/notice/mtNotice/nlist">通知列表</a></li>
		<!--<shiro:hasPermission name="notice:mtNotice:edit"><li><a href="${ctx}/notice/mtNotice/form">通知内容</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="mtNotice" action="${ctx}/notice/mtNotice/nlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>标题</th>
				<th>发件人</th>
				<th>收件人</th>
				<th>通知类型</th>
				<th>操作者</th>
				<th>时间</th>
				<shiro:hasPermission name="notice:mtNotice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtNotice">
			<tr>
				<td><a href="${ctx}/notice/mtNotice/form?id=${mtNotice.id}">
					${mtNotice.title}
				</a></td>
				<td>
					${mtNotice.sender}
				</td>
				<td>
					${fns:getmtUserById(mtNotice.userid).userRealName}
				</td>
				<td>
					${fns:getDictLabel(mtNotice.type, 'send_notice', '')}
				</td>
				<td>
					${fns:getUserById(mtNotice.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${mtNotice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="notice:mtNotice:edit"><td>
    				<a href="${ctx}/notice/mtNotice/form?id=${mtNotice.id}">查看内容</a>
					<a href="${ctx}/notice/mtNotice/delete?id=${mtNotice.id}" onclick="return confirmx('确认要删除该文章管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>