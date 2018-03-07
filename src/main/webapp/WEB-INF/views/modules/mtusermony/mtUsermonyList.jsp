<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户资金列表管理</title>
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
		<li class="active"><a href="${ctx}/mtusermony/mtUsermony/">客户资金列表列表</a></li>
		<shiro:hasPermission name="mtusermony:mtUsermony:edit"><li><a href="${ctx}/mtusermony/mtUsermony/form">客户资金列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUsermony" action="${ctx}/mtusermony/mtUsermony/fundlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="userid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开始日期：</label>
				<input name="startdate" type="text" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUsermony.startdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="enddate" type="text" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtUsermony.enddate}" pattern="yyyy-MM-dd"/>"
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
				<th>姓名</th>
				<th>充值金额</th>
				<th>提现金额</th>				
				<th>冻结资金</th>
				<th>投资金额</th>
				<th>结算本金</th>
				<th>结算收益</th>
				<th>罚息收入</th>
				<th>剩余还款</th>
				<th>还本金</th>
				<th>还收益</th>
				<th>违约金</th>
				<th>罚息支出</th>
				<th>滞纳金</th>
				<th>账户管理费</th>
				<th>奖励金额</th>
				<th>日期</th>
				<shiro:hasPermission name="mtusermony:mtUsermony:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUsermony">
			<tr>
				<td><a href="${ctx}/mtusermony/mtUsermony/form?id=${mtUsermony.id}">
					${mtUsermony.userid}
				</a></td>
				<td>
					${fns:sumUserRecharge(mtUsermony.date, mtUsermony.userid)}
				</td>
				<td>
					${fns:sumUserCash(mtUsermony.date, mtUsermony.userid)}
				</td>				
				<td>
					<!-- 3投资 -->
					${fns:sumUserLoanrd(mtUsermony.date, mtUsermony.userid, 3)}
				</td>
				<td>
					<!-- 8放款 -->
					${fns:sumUserLoanrd(mtUsermony.date, mtUsermony.userid, 8)}
				</td>
				<td>
					${fns:sumUserEgsrord(mtUsermony.date, mtUsermony.userid, 1)}
				</td>
				<td>
					${fns:sumUserEgsrord(mtUsermony.date, mtUsermony.userid, 2)}
				</td>
				<td>
					<!-- 7罚息   -->
					${fns:sumUserLoanrd(mtUsermony.date, mtUsermony.userid, 7)}
				</td>
				<td>
					${mtUsermony.remony}
				</td>
				<td>
					${fns:sumUserRepay(mtUsermony.date, mtUsermony.userid, 1)}
				</td>
				<td>
					${fns:sumUserRepay(mtUsermony.date, mtUsermony.userid, 2)}
				</td>
				<td>
					${fns:sumUserRepay(mtUsermony.date, mtUsermony.userid, 3)}
				</td>
				<td>
					${fns:sumUserRepay(mtUsermony.date, mtUsermony.userid, 4)}
				</td>
				<td>
					${fns:sumUserRepay(mtUsermony.date, mtUsermony.userid, 5)}
				</td>
				<td>
					${fns:sumInmount(mtUsermony.date, mtUsermony.userid, '')}
				</td>
				<td>
					${mtUsermony.reward}
				</td>
				<td>
					${mtUsermony.date}
				</td>
				<shiro:hasPermission name="mtusermony:mtUsermony:edit"><td>
    				<a href="${ctx}/mtusermony/mtUsermony/form?id=${mtUsermony.id}">修改</a>
					<a href="${ctx}/mtusermony/mtUsermony/delete?id=${mtUsermony.id}" onclick="return confirmx('确认要删除该客户资金列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>