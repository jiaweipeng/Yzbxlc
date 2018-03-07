<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>还款列表管理</title>
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
		<li class="active"><a href="${ctx}/repayment/mtRepayment/?type=1">还款列表</a></li>
		<!--<shiro:hasPermission name="repayment:mtRepayment:edit"><li><a href="${ctx}/repayment/mtRepayment/form">还款列表添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="mtRepayment" action="${ctx}/repayment/mtRepayment/?type=${type}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>产品名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>产品名称</th>
				<th>借款金额</th>
				
				<th>应还本金</th>
				<th>应还利息</th>
				<th>还款期数</th>
				<th>还款状态</th>
				<c:if test="${type == 1}">
					<th>逾期天数</th>
					<th>逾期金额</th>
				</c:if>
				<!-- 
				<th>实际还款日期</th>
				 -->
				<shiro:hasPermission name="repayment:mtRepayment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtRepayment">
			<tr>
				<td>
					${fns:getmtUserById(mtRepayment.userid).userNickName}					
				</td>
				<td>
					${fns:getmtUserById(mtRepayment.userid).userRealName}
				</td>				
				<td>
					${fns:getProductByGrade(mtRepayment.grade).title}
				</td>
				<td>
					${fns:getProductByGrade(mtRepayment.grade).planmoney}
				</td>
				
				<td>
					${mtRepayment.yetcapital}
				</td>
				<td>
					${mtRepayment.yetinterest}
				</td>
				<td>
					${mtRepayment.numperiods}
				</td>
				<td>
					${fns:getDictLabel(mtRepayment.state, 'back_status', '')}
				</td>
				
				<c:if test="${type == 1}">
					<td>
						${mtRepayment.overduedays}
					</td>
					<td>
						${mtRepayment.withanakin}
					</td>
				</c:if>
				
				<td>
					<fmt:formatDate value="${mtRepayment.duedate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<!-- 
				<td>
					<fmt:formatDate value="${mtRepayment.actualDuedate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 -->
				<shiro:hasPermission name="repayment:mtRepayment:edit"><td>
    				<a href="${ctx}/repayment/mtRepayment/form?id=${mtRepayment.id}">查看/修改</a>
					<a href="${ctx}/repayment/mtRepayment/delete?id=${mtRepayment.id}" onclick="return confirmx('确认要删除该还款列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>