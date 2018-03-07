<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投资人记录</title>
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
		<li class="active"><a>用户投资记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtProduct" action="${ctx}/mtinvest/mtInvest/list?id=${id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 91px">投标开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtInvest.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width: 91px">投标结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtInvest.endTime}" pattern="yyyy-MM-dd"/>"
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
		<thead >
			<tr >
				<th>产品标号</th>
				<th >订单号</th>
				<th>用户名</th>
				<th>姓名</th> 
				<th>投资金额</th>
				<th>投资状态</th>
				<th>投标时间</th>
				<shiro:hasPermission name="mtinvest:mtInvest:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="mtInvest">
			
			<tr>
				<td>
					${mtInvest.projectNo}
				</td>
				<td>
					${mtInvest.ipsBillNo}
				</td>
					
				<td>
					${fns:getmtUserById(mtInvest.mtUserId).userNickName}
				</td>
				<td>
					${fns:getmtUserById(mtInvest.mtUserId).userRealName}
				</td>
				<td>
					${mtInvest.ipsTrdAmt}
				</td>
				
				<td>
					${fns:getDictLabel(mtInvest.trdStatus, 'invest_trdStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtInvest.ipsDoTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="mtinvest:mtInvest:view"><td>
    				<a href="${ctx}/mtinvest/mtInvest/mtinvestlist?id=${mtInvest.id}">查看收益详情</a>
				</td></shiro:hasPermission>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>