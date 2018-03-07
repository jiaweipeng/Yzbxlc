<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收益列表</title>
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
		<li class="active"><a>用户收益详情</a></li>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>期数</th>
				<th>应收本金</th>
				<th>应收利息</th>
				<th>收益总金额</th>
				<th>收益状态</th>
				<th>收益时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtEgsrord">
			<tr>
				
			
				<td>
					${mtEgsrord.numpds}
				</td>
				
				<td>
					${mtEgsrord.receivable}
				</td>
				<td>
					${mtEgsrord.interest}
				</td>
				
				<td>
					${mtEgsrord.interest+mtEgsrord.receivable}
				</td>
						
				
				<td>
					<c:if test="${mtEgsrord.type==0}">
						<font color="#FF0000">${fns:getDictLabel(mtEgsrord.type, 'Income_status', '')}</font>
					</c:if>
					
					<c:if test="${mtEgsrord.type==1}">
						<font color="#00CC00">${fns:getDictLabel(mtEgsrord.type, 'Income_status', '')}</font>
					</c:if>
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