<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>友情连接管理</title>
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
		<li class="active"><a href="${ctx}/mtlinks/mtlinks/">友情连接列表</a></li>
		<shiro:hasPermission name="mtlinks:mtlinks:edit"><li><a href="${ctx}/mtlinks/mtlinks/form">友情连接添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtlinks" action="${ctx}/mtlinks/mtlinks/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>类型:</label>
				<form:select path="type" class="input-xlarge" style="width:200px">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('links_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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
				<th>类型</th>
				<th>排序</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="mtlinks:mtlinks:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtlinks">
			<tr>
				<td>
					${mtlinks.title}
				</td>
				
				
				<td>
					${fns:getDictLabel(mtlinks.type, 'links_type', '')}
				</td>
				<td>
					${mtlinks.sort}
				</td>
				
				<td>
					${fns:getDictLabel(mtlinks.stats, 'show_hide', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtlinks.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="mtlinks:mtlinks:edit"><td>
    				<a href="${ctx}/mtlinks/mtlinks/form?id=${mtlinks.id}">修改</a>
					<a href="${ctx}/mtlinks/mtlinks/delete?id=${mtlinks.id}" onclick="return confirmx('确认要删除该友情连接吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>