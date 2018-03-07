<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".sendall").click(function(e) {
				var isChecked = $(this).prop("checked");
				$(".sendout").prop("checked", isChecked);
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function getid() {
			var uid = null;
			var ischecked = false;
			var url = "${ctx}/notice/mtNotice/uform";
			var length = $(document).find(".sendout").length;
			for(var i=0; i<length; i++) {
				// 判断选项是否都选中了
				var isChe = $(document).find(".sendout").get(i).checked;
				if(isChe == true) {
					if(uid == null) {
						uid = $(document).find(".sendout").get(i).parentNode.id;
					} else {
						uid += ","+$(document).find(".sendout").get(i).parentNode.id;
					}
					ischecked = true;
				}
			}
			if(ischecked) {
				window.location.href=url+"?uid="+uid;
			} else {
				alert("请选择收件人");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/mtNotice/ulist">客户列表</a></li>
		<shiro:hasPermission name="notice:mtNotice:edit"><li><a href="javascript:getid();">发送通知</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mtUser" action="${ctx}/notice/mtNotice/ulist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="userRealName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户电话：</label>
				<form:input path="userTel" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				
				<th>
					<input class="sendall" type="checkbox" />
				</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>电话</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mtUser" varStatus="list">
			<tr>
				
				<td>
					<span id="${mtUser.id}">
	            		<input class="sendout" type="checkbox" />
	            	</span>
                </td>
				<td>
					${mtUser.userNickName}
				</td>
				<td>
					${mtUser.userRealName}
				</td>
				<td>
					${mtUser.userTel}
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>