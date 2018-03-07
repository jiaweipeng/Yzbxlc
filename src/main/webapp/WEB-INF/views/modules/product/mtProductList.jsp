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
		<li class="active"><a>产品管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtProduct" action="${ctx}/product/mtProduct/list/?type=5" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="80" class="input-medium"/>
			</li>
			
			<c:if test="${type == 5}" >
				<li ><label style="width: 40px">状态:</label>
					<form:select path="status" class="input-medium" style="width:200px">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('product_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</li>
			</c:if>
			
			<li><label style="width:91px">募集开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:91px">募集结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium"
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
				<th>编号</th>
				<th>用户账号</th>
				<th>姓名</th>
				<th>手机号</th>
				<c:if test="${type == 0}">
					<th>联系人</th>
					<th>联系电话</th>
				</c:if>
				<th>产品标题</th>
				<th>收益率(%)</th>
				<th>借款金额</th>
				<c:if test="${type!= 0}">
					<th>结算方式</th>
					<th>审核人</th>
					<th>进度(%)</th>
				</c:if>
				<th>状态</th>
				<th>募集开始时间</th>
				<th>募集结束时间</th>
				<shiro:hasPermission name="product:mtProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtProduct">
			<tr>
				<td>${mtProduct.id }</td>
				<td>
					${fns:getmtUserById(mtProduct.userid).userNickName}
				</td>
				<td>
					${fns:getmtUserById(mtProduct.userid).userRealName}
				</td>
				<td>
					${fns:getmtUserById(mtProduct.userid).userTel}
				</td>
				<c:if test="${type == 0}">
					<td>
						${mtProduct.contact}
					</td>
					<td>
						${mtProduct.contactTel}
					</td>
				</c:if>
				<td>
					${mtProduct.title}
				</td>
				<td>
					${mtProduct.hopepercent}
				</td>
				<td>
					${mtProduct.planmoney}
				</td>
				<c:if test="${type != 0}">
					<td>
						${fns:getDictLabel(mtProduct.balancetype, 'balancetype', '')}
					</td>
					<td>
						${fns:getUserById(mtProduct.updateBy).name}
					</td>
					<td>
						${mtProduct.currentpercent}
					</td>
				</c:if>
			
				<td>
					${fns:getDictLabel(mtProduct.status, 'product_status', '')}
				</td>
				
				
				<td>
					<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					<fmt:formatDate value="${mtProduct.endtime}" pattern="yyyy-MM-dd"/>
				</td>
				
				<shiro:hasPermission name="product:mtProduct:edit"><td>
					<c:if test="${type == 0}">
						<a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}&keyt=${type}">发标</a>
					</c:if>
					<c:if test="${type == 4}">
						<a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}&keyt=${type}">放款</a>
					</c:if>
					<c:if test="${type == 10}">
						<a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}&keyt=${type}">流标</a>
					</c:if>
					<a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}">修改</a>
					<a href="${ctx}/product/mtProduct/delete?id=${mtProduct.id}" onclick="return confirmx('确认要删除该产品管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>