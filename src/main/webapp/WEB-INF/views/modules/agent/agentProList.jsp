<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
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
	 <c:forEach items="${products}" var="mtPro">
		${mtPro.useragent}
	</c:forEach> 
	
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品标号</th>
				<th>产品标题</th>
				<th>预期收益百分比</th>
				<th>募集期开始时间</th>
				<th>募集期结束</th>
				<th>结算方式</th>
				<th>借款类型</th>
				<th>展示天数</th>
				<th>最低投资金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${products}" var="mtPro">
			<tr>
				<td>
					${mtPro.pid}
				</td>
				<td>
					${mtPro.title}
				</td>
				<td>
					${mtPro.hopepercent}
				</td>
				<td>
					${mtPro.starttime}
				</td>
				<td>
					${mtPro.endtime}
				</td>
				<td>
					${mtPro.balancetype}
				</td>
				<td>
					${mtPro.unit}
				</td>
				<td>
					${mtPro.displaydays}
				</td>
				<td>
					${mtPro.lowmoney}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div style="text-align:right"><a href="${ctx}/mtuser/mtUser/agentPassList"><input type="button" value="返回"/></a></div>
</body>
</html>