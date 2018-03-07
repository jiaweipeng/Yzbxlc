<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投资人收益列表</title>
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
		<li class="active"><a href="${ctx}/mtegsrord/mtEgsrord/">投资人收益列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtEgsrord" action="${ctx}/mtegsrord/mtEgsrord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<th>借款者</th>
				<th>期数</th>
				<th>首款总额</th>
				<th>应收本金</th>
				<th>应收利息</th>
				<th>收益状态</th>
				<th>预计收益时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtEgsrord">
			<tr>
				<td><a href="${ctx}/mtegsrord/mtEgsrord/form?id=${mtEgsrord.id}">
					${mtEgsrord.borrower}
				</a></td>
				<td>
					${mtEgsrord.numpds}
				</td>
				<td>
					${mtEgsrord.total}
				</td>
				<td>
					${mtEgsrord.receivable}
				</td>
				<td>
					${mtEgsrord.interest}
				</td>
				<td>
					${fns:getDictLabel(mtEgsrord.type, 'Income_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtEgsrord.recenttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>