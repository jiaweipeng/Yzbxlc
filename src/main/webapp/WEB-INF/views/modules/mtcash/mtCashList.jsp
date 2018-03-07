<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现列表</title>
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
		<li class="active"><a>提现列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtPnuelist" action="${ctx}/mtcash/mtCash/list?id=${userid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtCash.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtCash.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>商户订单号</th>
				<th>订单号</th>
				<th>平台手续费</th>
				<th>手续费</th>
				<th>提现金额</th>
				<th>提现状态</th>
				<th>提现时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtCash">
			<tr>
				<td>
					${mtCash.merbillno}
				</td>
				<td>
					${mtCash.ipsbillno}
				</td>
				
				<td>
					${mtCash.merfee}
				</td>
				<td>
					${mtCash.ipsfee}
				</td>
				<td>
					${mtCash.ipstrdamt}
				</td>
				<td>
					<c:if test="${mtCash.trdstatus==0 }">
						<font style="color: green;">${fns:getDictLabel(mtCash.trdstatus, 'cash_trdStatus', '')}</font>
					</c:if>
					
					<c:if test="${mtCash.trdstatus==1 }">
						<font style="color: red;">${fns:getDictLabel(mtCash.trdstatus, 'cash_trdStatus', '')}</font>
					</c:if>
					
					<c:if test="${mtCash.trdstatus==2 }">
						<font style="color: red ;">${fns:getDictLabel(mtCash.trdstatus, 'cash_trdStatus', '')}</font>
					</c:if>
					
				</td>
				<td>
					<fmt:formatDate value="${mtCash.ipsdotime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>