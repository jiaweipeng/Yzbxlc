<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网站信息管理</title>
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
		<li class="active"><a href="${ctx}/webinfo/mtWebinfo/">网站信息列表</a></li>
		<shiro:hasPermission name="webinfo:mtWebinfo:edit"><li><a href="${ctx}/webinfo/mtWebinfo/form">网站信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtWebinfo" action="${ctx}/webinfo/mtWebinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>参数：</label>
				<form:input path="pname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>参数值：</label>
				<form:input path="pvalues" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>参数</th>
				<th>参数值</th>
				<th>变量名</th>
				<th>类型</th>
				<shiro:hasPermission name="webinfo:mtWebinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtWebinfo">
			<tr>
				<td><a href="${ctx}/webinfo/mtWebinfo/form?id=${mtWebinfo.id}">
					${mtWebinfo.pname}
				</a></td>
				<td>
					${mtWebinfo.pvalues}
				</td>
				<td>
					${mtWebinfo.varname}
				</td>
				
				<td>
					${fns:getDictLabel(mtWebinfo.type, 'webinfo', '')}
				</td>
				
				<shiro:hasPermission name="webinfo:mtWebinfo:edit"><td>
    				<a href="${ctx}/webinfo/mtWebinfo/form?id=${mtWebinfo.id}">修改</a>
					<a href="${ctx}/webinfo/mtWebinfo/delete?id=${mtWebinfo.id}" onclick="return confirmx('确认要删除该网站信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>