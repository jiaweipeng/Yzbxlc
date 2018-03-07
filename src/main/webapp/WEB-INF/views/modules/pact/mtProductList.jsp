<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理管理</title>
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
		<li class="active"><a>产品列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标号</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>产品标题</th>
				<th>预期收益百分比</th>
				<th>计划金额</th>
				<th>状态</th>
				<th>募集开始时间</th>
				<th>募集结束时间</th>
				<!-- 隐藏 
				<th>投资总人数</th>
				-->
				<shiro:hasPermission name="product:mtProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtProduct">
			<tr>
				<td>
					${mtProduct.pid}
				</td>
				<td>
					${fns:getmtUserById(mtProduct.userid).userNickName}
				</td>
				<td>
					${fns:getmtUserById(mtProduct.userid).userRealName}
				</td>
				<td>
					${mtProduct.title}
				</td>
				<td>
					${mtProduct.hopepercent}
				</td>
				<td>
					${mtProduct.planmoney}
				</td>
				<td>
					${fns:getDictLabel(mtProduct.status, 'product_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${mtProduct.endtime}" pattern="yyyy-MM-dd"/>
				</td>
				<!-- 隐藏 
				<td>
					0
				</td>
				-->
				<shiro:hasPermission name="product:mtProduct:edit"><td>
				<!-- 隐藏 
    				<a href="${ctx}/pact/mtPact/list?type=1&pid=${mtProduct.pid}">查看投资人协议</a>
    			-->
    				<a href="http://or6py3kp2.bkt.clouddn.com/${fns:findPact(mtProduct.pid).pactSite}">查看借款协议</a>
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>