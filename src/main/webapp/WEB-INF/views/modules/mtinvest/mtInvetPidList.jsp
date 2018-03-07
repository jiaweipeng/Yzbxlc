<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品列表</title>
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
	<form:form id="searchForm" modelAttribute="mtProduct" action="${ctx}/mtinvest/mtInvest/Pidlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="80" class="input-medium"/>
			</li>
			
			<li><label  style="width: 40px">状态:</label>
				<form:select path="status" class="input-xlarge" style="width:200px">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('product_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			<li><label style="width: 91px">募集开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width: 91px">募集结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtProduct.endtime}" pattern="yyyy-MM-dd"/>"
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
				<th>产品标题</th>
				<th>投资次数</th>
				<th>年利率(%)</th>
				<th>借款金额</th>
				<th>募集进度(%)</th>
				<th>已投金额</th>
				<th>可投金额</th>
				<th>结算方式</th>
				<th>产品状态</th>
				<th>募集开始时间</th>
				<th>募集结束时间</th>
				<th>产品发布时间</th>
				<shiro:hasPermission name="mtinvest:mtInvest:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtProduct">
			<c:if test="${mtProduct.status!=0 && mtProduct.status!=2 && mtProduct.status!=1}">
			<tr>
				<td>
					${mtProduct.title}
				</td>
				<td>
					${fns:getcountname(mtProduct.id)}
				</td>
				<td>
					${mtProduct.hopepercent}
				</td>
				<td>
					${mtProduct.planmoney}
				</td>
				<td>
					${mtProduct.currentpercent}
				</td>
				<td>
					${mtProduct.ivalready}
				</td>
				
				<td>
					${mtProduct.ivable}
				</td>
				
				<td>
					${fns:getDictLabel(mtProduct.balancetype, 'balancetype', '')}
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
				<td>
					<fmt:formatDate value="${mtProduct.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="mtinvest:mtInvest:view"><td>
    				<a href="${ctx}/mtinvest/mtInvest/list?id=${mtProduct.id}">查看投资详情</a>
				</td></shiro:hasPermission>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>