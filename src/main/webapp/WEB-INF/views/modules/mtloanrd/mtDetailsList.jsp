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
		<c:choose> 
			<c:when test="${show == true}"> 
				<li><a href="${ctx}/mtloanrd/mtLoanrd/list">资金明细列表</a></li>
				<li class="active"><a>资金明细详情</a></li>
			</c:when>
			<c:otherwise>
				<li class="active"><a>资金明细详情</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<c:if test="${show == false}">
	<form:form id="searchForm" modelAttribute="mtLoanrd" action="${ctx}/mtloanrd/mtLoanrd/details" method="post" class="breadcrumb form-search">
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
	</c:if>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>转出方姓名</th>
				<th>转入方姓名</th>
				<th>转账订单号</th>
				<th>转账金额</th>
				<th>操作类型</th>
				<th>转账状态</th>
				<th>转账处理时间</th>
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
					${fns:findUser(mtLoanrd.outipsacctno).userRealName}
				</td>
				<td>
					${fns:findUser(mtLoanrd.inipsacctno).userRealName}
				</td>
				<td>
					${mtLoanrd.ipsbillno}
				</td>				
				<td>
					${mtLoanrd.ipstrdamt}
				</td>
				<td>
					${fns:fundType(mtLoanrd.transferType)}
				</td>
				<td>
					${fns:getDictLabel(mtLoanrd.trdstatus, 'loanrd_trdStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtLoanrd.ipsdotime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="mtloanrd:mtLoanrd:edit"><td>
    				<a href="${ctx}/mtloanrd/mtLoanrd/form?id=${mtLoanrd.id}&show=${show}">查看全部</a>
					<a href="${ctx}/mtloanrd/mtLoanrd/delete?id=${mtLoanrd.id}" onclick="return confirmx('确认要删除该转账列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>