<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>认证管理</title>
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
		<li class="active"><a>认证列表</a></li>
	<c:if test="${authtype==5}">
		<shiro:hasPermission name="auth:mtAuth:edit"><li><a href="${ctx}/auth/mtAuth/form?type=5">认证添加</a></li></shiro:hasPermission>
	</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="mtAuth" action="${ctx}/auth/mtAuth/list?type=${authtype}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
		<!-- 手机认证模糊查询 -->
		<c:if test="${authtype==3 }">
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
		</c:if>
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
			<!-- 实名认证 -->
			<c:if test="${authtype==1}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>身份证号</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
			</c:if>
			<!-- 银行卡认证 -->
			<c:if test="${authtype==2}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>开户银行</th>
				<th>开户行支行名称</th>
				<th>银行卡号</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</c:if>
			<!-- 手机认证 -->
			<c:if test="${authtype==3}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</c:if>
			<!-- 现场认证 -->
			<c:if test="${authtype==4}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</c:if>
			<!-- 视频认证 -->
			<c:if test="${authtype==5}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</c:if>
			<!-- 邮箱认证 -->
			<c:if test="${authtype==6}">
				<th>用户名</th>
				<th>真实姓名</th>
				<th>邮箱号</th>
				<th>认证状态</th>
				<th>认证类型</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</c:if>
				<shiro:hasPermission name="auth:mtAuth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtAuth">
			<tr>
		<!-- 实名认证 -->
		<c:if test="${authtype==1}">
				<td>    <!--   <a href="${ctx}/auth/mtAuth/form?id=${mtAuth.id}"> -->
					${fns:getmtUserById(mtAuth.userid).userNickName}
				</a></td>
				<td>
					${mtAuth.name}
				</td>
				<td>
					${mtAuth.idcard}
				</td>
				
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<td>
				<!-- 创建时间 -->
				<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
		</c:if>
		<!-- 银行卡认证 -->
		<c:if test="${authtype==2}">
				<td> 
					${fns:getmtUserById(mtAuth.userid).userNickName}
			    </td>
			    <td>
					${mtAuth.name}
				</td>
				<td>
					${mtAuth.depositBank}
				</td>
				<td>
					${mtAuth.branchBankName}
				</td>
				<td>
					${mtAuth.bankCard}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<!-- 创建时间 -->
				<td>
				<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
					<fmt:formatDate value="${mtAuth.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtAuth.remarks}
				</td>
		  </c:if>
		  <!-- 手机认证 -->
		  <c:if test="${authtype==3}">
				<td> 
					${fns:getmtUserById(mtAuth.userid).userNickName}
			    </td>
			    <td>
					${mtAuth.name}
				</td>
				<td>
					${mtAuth.phone}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<!-- 创建时间 -->
				<td>
				<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
					<fmt:formatDate value="${mtAuth.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtAuth.remarks}
				</td>
			</c:if>
			<!-- 现场认证 -->
			<c:if test="${authtype==4}">
				<td> 
					${fns:getmtUserById(mtAuth.userid).userNickName}
			    </td>
			    <td>
					${mtAuth.name}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<!-- 创建时间 -->
				<td>
				<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
					<fmt:formatDate value="${mtAuth.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtAuth.remarks}
				</td>
			</c:if>
			<!-- 视频认证 -->
			<c:if test="${authtype==5}">
				<td> 
					${fns:getmtUserById(mtAuth.userid).userNickName}
			    </td>
			    <td>
					${mtAuth.name}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<!-- 创建时间 -->
				<td>
				<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
					<fmt:formatDate value="${mtAuth.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtAuth.remarks}
				</td>
			</c:if>
			<!-- 邮箱认证 -->
			<c:if test="${authtype==6}">
				<td> 
					${fns:getmtUserById(mtAuth.userid).userNickName}
			    </td>
			    <td>
					${mtAuth.name}
				</td>
				<td>
					${mtAuth.email}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authState, 'auth_state', '')}
				</td>
				<td>
					${fns:getDictLabel(mtAuth.authType, 'auth_type', '')}
				</td>
				<!-- 创建时间 -->
				<td>
					<fmt:formatDate value="${mtAuth.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>					                         
				</td>
				<td>
					<fmt:formatDate value="${mtAuth.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mtAuth.remarks}   
				</td>
			</c:if>
				<shiro:hasPermission name="auth:mtAuth:edit"><td>
    				<a href="${ctx}/auth/mtAuth/form?type=${authtype}&id=${mtAuth.id}">修改</a>
					<a href="${ctx}/auth/mtAuth/delete?id=${mtAuth.id}" onclick="return confirmx('确认要删除该认证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>