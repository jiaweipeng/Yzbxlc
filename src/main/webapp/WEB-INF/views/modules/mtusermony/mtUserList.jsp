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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mtuser/mtUser/">客户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/mtusermony/mtUsermony/userlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!-- <li><label>用户名：</label>
				<form:input path="userNickName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li> -->
			<li><label>姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label style="width: 91px">开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUser.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:false});"/>
			</li>
			<li><label style="width: 91px">结束时间：</label>
				<input name="aborttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUser.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:false});"/>
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
				<th>用户名</th>
				<th>姓名</th>
				<th>充值金额</th>
				<th>提现金额</th>				
				<!-- <th>冻结资金</th> -->
				<th>投资金额</th>
				<th>结算本金</th>
				<th>结算收益</th>
				<th>罚息收入</th>
				<th>未还金额</th>
				<th>已还本金</th>
				<th>已还收益</th>
				<th>违约金</th>
				<th>罚息支出</th>
				<th>滞纳金</th>
				<th>账户管理费</th>
				<!-- <th>奖励金额</th> -->
				<th>注册时间</th>
				<!--<shiro:hasPermission name="mtuser:mtUser:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUser">
			<tr>
				<td>
					${mtUser.userNickName}
				</td>
				<td>
					${mtUser.userRealName}
				</td>
				<td>
					${fns:sumUserRecharge(mtUser.id, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserCash(mtUser.id, starttime, aborttime)}
				</td>
				<!--<td>
					 3投资 
					${fns:sumUserLoanrd(mtUser.id, 3, starttime, aborttime)}
				</td>-->
				<td>
					<!-- 8放款 -->
					${fns:sumUserLoanrd(mtUser.id, 8, starttime, aborttime)}
				</td>
				<td>
					<!-- * -->
					${fns:sumUserReceivable(mtUser.id, starttime, aborttime)}
				</td>
				<td>
					<!-- * -->
					${fns:sumUserInterest(mtUser.id, starttime, aborttime)}
				</td>
				<td>
					<!-- 7罚息   -->
					${fns:sumUserLoanrd(mtUser.id, 7, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserWzhr(mtUser.id, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserRepay(mtUser.id, 1, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserRepay(mtUser.id, 2, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserRepay(mtUser.id, 3, starttime, aborttime)}
				</td>
				<td>
					${fns:sumUserRepay(mtUser.id, 4, starttime, aborttime)}
				</td>
				<td>
					<!-- * -->
					${fns:sumUserRepay(mtUser.id, 5, starttime, aborttime)}
				</td>
				<td>
					<!-- * -->
					${fns:sumUserInmount(mtUser.id, starttime, aborttime)}
				</td>
				<!-- <td>
					
				</td> -->
				<td>
					<fmt:formatDate value="${mtUser.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<!--<shiro:hasPermission name="mtuser:mtUser:edit"><td>
    				<a href="${ctx}/mtusermony/mtUsermony/fundlist?userid=${mtUser.id}">资金列表</a>
				</td></shiro:hasPermission>-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>