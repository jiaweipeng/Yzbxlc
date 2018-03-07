<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>担保机构</title>
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
		<li class="active"><a href="${ctx}/mtguatee/mtGuatee/">担保机构列表</a></li>
		<shiro:hasPermission name="mtguatee:mtGuatee:edit"><li><a href="${ctx}/mtguatee/mtGuatee/form">担保机构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtGuatee" action="${ctx}/mtguatee/mtGuatee/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('guatee_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>类型</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('guatee_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>担保机构名称</th>
				<th>担保机构地址</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>公司概况</th>
				<th>发展历史</th>
				<th>状态</th>
				<th>类型 </th>
				<shiro:hasPermission name="mtguatee:mtGuatee:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtGuatee">
			<tr>
				<td><a href="${ctx}/mtguatee/mtGuatee/form?id=${mtGuatee.id}">
					${mtGuatee.msname}
				</a></td>
				<td>
					${mtGuatee.msaddress}
				</td>
				<td>
					${mtGuatee.contacts}
				</td>
				<td>
					${mtGuatee.number}
				</td>
				<td>
					${mtGuatee.comprofile}
				</td>
				<td>
					${mtGuatee.history}
				</td>
				<td>
					${fns:getDictLabel(mtGuatee.state, 'guatee_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtGuatee.type, 'guatee_type', '')}
				</td>
			
				<shiro:hasPermission name="mtguatee:mtGuatee:edit"><td>
    				<a href="${ctx}/mtguatee/mtGuatee/form?id=${mtGuatee.id}">修改</a>
					<a href="${ctx}/mtguatee/mtGuatee/delete?id=${mtGuatee.id}" onclick="return confirmx('确认要删除该担保机构列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>