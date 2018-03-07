<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台收益管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnDerive").click(function(e) {
				var urlPath = ".${ctx}/pnuelist/mtPnuelist/testmain";
				$.post(urlPath, function(data,status) {
					alert('success');
					if(status == 'success') {
						alert('success');
					}
				});
			});
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
				<li><a href="${ctx}/pnuelist/mtPnuelist/totalList">每日收益列表</a></li>
				<li class="active"><a href="${ctx}/pnuelist/mtPnuelist/list">平台收益详情</a></li>
			</c:when>
			<c:otherwise>
				<li class="active"><a href="${ctx}/pnuelist/mtPnuelist/list">平台收益详情</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<c:if test="${show == false}">
	<form:form id="searchForm" modelAttribute="mtPnuelist" action="${ctx}/pnuelist/mtPnuelist/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称</label>
				<form:input path="title" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>操作类型</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sitelucre_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				
				<a href="${ctx}/pnuelist/mtPnuelist/deriveData">
					<input class="btn btn-primary" type="button" value="数据导出"/>
				</a>
				<li class="btns"><a href="javascript:" class="btn btn-primary" onclick="self.location=document.referrer;">返回</a> </li> 
				 <li class="btns"> <a href="javascript:location.reload()" class="btn btn-primary">刷新</a></li >
			</li>
			<li class="clearfix"></li>			
		</ul>
	</form:form>
	</c:if>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>姓名</th>
				<th>产品名称</th>
				<th>收益金额</th>
				<th>还款期数</th>
				<th>收益类型</th>
				<th>收益时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtPnuelist">
			<tr>
				<td>
					${fns:getmtUserById(mtPnuelist.mtUserId).userNickName}
				</td>
				<td>
					${fns:getmtUserById(mtPnuelist.mtUserId).userRealName}
				</td>
				<td>
					${fns:getProductByGrade(mtPnuelist.pid).title}
				</td>
				<td>
					${mtPnuelist.inmount}
				</td>
				<td>
					<c:choose> 
						<c:when test="${mtPnuelist.nper == null}">0</c:when>
						<c:otherwise>${mtPnuelist.nper}</c:otherwise>
					</c:choose>
				</td>
				<td>
					${fns:getDictLabel(mtPnuelist.type, 'sitelucre_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${mtPnuelist.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>