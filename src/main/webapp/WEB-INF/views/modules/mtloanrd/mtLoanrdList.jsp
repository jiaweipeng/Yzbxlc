<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转账列表管理</title>
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
		<li class="active"><a>资金明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtLoanrd" action="${ctx}/mtloanrd/mtLoanrd/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称</label>
				<form:input path="title" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>			
			<li><label>操作类型</label>
				<select name="transferType" class="input-medium">
					<option value=""></option>
					<c:forEach items="${fns:fundAllType()}" var="item">
						<c:choose> 
							<c:when test="${mtLoanrd.transferType == item.key}"> 
								<option value ="${item.key}" selected="selected">${item.value}</option>
							</c:when> 
							<c:otherwise> 
								<option value ="${item.key}">${item.value}</option>
							</c:otherwise> 
						</c:choose>
					</c:forEach>
				</select>
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
				<th>产品名称</th>
				<th>操作金额</th>
				<th>操作类型</th>
				<th>创建时间</th>
				<shiro:hasPermission name="mtloanrd:mtLoanrd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtLoanrd">
			<tr>
				<td>
					${fns:getProductByGrade(mtLoanrd.projectNo).title}
				</td>
				<td>
					${mtLoanrd.ipstrdamt}
				</td>
				<td>
					${fns:fundType(mtLoanrd.transferType)}
				</td>				
				<td>
					<fmt:formatDate value="${mtLoanrd.createDate}" pattern="yyyy-MM-dd"/>
				</td>				
				<shiro:hasPermission name="mtloanrd:mtLoanrd:edit"><td>
    				<a href="${ctx}/mtloanrd/mtLoanrd/details?projectNo=${mtLoanrd.projectNo}&transferType=${mtLoanrd.transferType}">详情列表</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>