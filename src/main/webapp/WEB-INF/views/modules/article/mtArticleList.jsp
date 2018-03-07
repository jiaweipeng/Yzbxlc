<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理管理</title>
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
		<li class="active"><a href="${ctx}/article/mtArticle/">文章管理列表</a></li>
		<shiro:hasPermission name="article:mtArticle:edit"><li><a href="${ctx}/article/mtArticle/form">文章管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtArticle" action="${ctx}/article/mtArticle/" method="post" class="breadcrumb form-search">
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
				<th>类型</th>
				<th>状态</th>
				<th>排序</th>
				<th>创建者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="article:mtArticle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtArticle">
			<tr>
				<td><a href="${ctx}/article/mtArticle/form?id=${mtArticle.id}">
					${mtArticle.title}
				</a></td>
				<td>
					${fns:getDictLabel(mtArticle.type, 'article_type', '')}
				</td>
				<td>
					${fns:getDictLabel(mtArticle.status, 'status', '')}
				</td>
				<td>
					${mtArticle.sort}
				</td>
				<td>
					${mtArticle.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${mtArticle.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="article:mtArticle:edit"><td>
    				<a href="${ctx}/article/mtArticle/form?id=${mtArticle.id}">修改</a>
					<a href="${ctx}/article/mtArticle/delete?id=${mtArticle.id}" onclick="return confirmx('确认要删除该文章管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>