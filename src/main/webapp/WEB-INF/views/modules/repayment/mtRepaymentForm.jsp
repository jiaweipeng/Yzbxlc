<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>还款列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/repayment/mtRepayment/?type=1">还款列表列表</a></li>
		<li class="active"><a href="javascript:void(0);">还款列表<shiro:hasPermission name="repayment:mtRepayment:edit">${not empty mtRepayment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="repayment:mtRepayment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtRepayment" action="${ctx}/repayment/mtRepayment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<input readonly="readonly" type="text" value="${fns:getmtUserById(mtRepayment.userid).userNickName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<input readonly="readonly" type="text" value="${fns:getmtUserById(mtRepayment.userid).userRealName}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<input readonly="readonly" type="text" value="${fns:getProductByGrade(mtRepayment.grade).title}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款金额：</label>
			<div class="controls">
				<input readonly="readonly" type="text" value="${fns:getProductByGrade(mtRepayment.grade).planmoney}" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标号：</label>
			<div class="controls">
				<form:input path="grade" readonly="true" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款期数：</label>
			<div class="controls">
				<form:input path="numperiods" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应还日期：</label>
			<div class="controls">
				<input name="duedate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${mtRepayment.duedate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应还本金：</label>
			<div class="controls">
				<form:input path="yetcapital" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应还利息：</label>
			<div class="controls">
				<form:input path="yetinterest" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">罚息金额：</label>
			<div class="controls">
				<form:input path="penalty" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违约金：</label>
			<div class="controls">
				<form:input path="withanakin" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">滞纳金：</label>
			<div class="controls">
				<form:input path="latefee" readonly="true" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">逾期天数：</label>
			<div class="controls">
				<form:input path="overduedays" readonly="true" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款状态：</label>
			<div class="controls">
				<input type="text" readonly="true" htmlEscape="false" value="${fns:getDictLabel(mtRepayment.state, 'back_status', '')}" class="input-xlarge"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">是否逾期：</label>
			<div class="controls">
				<form:input path="overdue" readonly="true" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际还款日期：</label>
			<div class="controls">
				<input name="actualDuedate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${mtRepayment.actualDuedate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际交纳滞纳金金额：</label>
			<div class="controls">
				<form:input path="actualAnakin" htmlEscape="false" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际交纳违约金金额：</label>
			<div class="controls">
				<form:input path="actualLatefee" htmlEscape="false" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际交纳滞纳金时间：</label>
			<div class="controls">
				<input name="actualPayTime" type="text" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${mtRepayment.actualPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="repayment:mtRepayment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>