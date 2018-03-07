<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台收益管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/pnuelist/mtPnuelist/totalList");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/pnuelist/mtPnuelist/list");
			$("#searchForm").submit();
	    	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pnuelist/mtPnuelist/totalList">每日收益列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtPnuelist" action="${ctx}/pnuelist/mtPnuelist/totalList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收益时间:</label>
				<input name="createDate" type="text" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd"/>"
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
				<th>收益总金额</th>
				<th>借款收益</th>
				<th>投资收益</th>
				<th>逾期收益</th>
				<th>收益时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtPnuelist">
			<tr>
				<td>
					${mtPnuelist.inmount}
				</td>				
				<td>
					${fns:sumInmount(mtPnuelist.findtime, 0)}
				</td>				
				<td>
					${fns:sumInmount(mtPnuelist.findtime, 1)}
				</td>				
				<td>
					${fns:sumInmount(mtPnuelist.findtime, 2)}
				</td>				
				<td>
					<fmt:formatDate value="${mtPnuelist.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="${ctx}/pnuelist/mtPnuelist/list?date=${mtPnuelist.createDate}">查看详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>