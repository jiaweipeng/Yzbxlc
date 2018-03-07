<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理管理</title>
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
			
			var balance0 = $("#balance-type-hide").find("option[value='0']");
			var balance1 = $("#balance-type-hide").find("option[value='1']");
			var balance2 = $("#balance-type-hide").find("option[value='2']");
			
			$("#unitChange").change(function(){				
				if($(this).val()==1) {
					// tian	
					$("#balance-type").find("option").remove();
					$("#balance-type").append(balance2);
				} else {
					$("#balance-type").find("option").remove();
					$("#balance-type").append(balance0);
					$("#balance-type").append(balance1);
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/mtProduct/?type=${type}">产品管理列表</a></li>
		<li class="active"><a href="${ctx}/product/mtProduct/form?id=${mtProduct.id}">产品管理<shiro:hasPermission name="product:mtProduct:edit">${not empty mtProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="product:mtProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mtProduct" action="${ctx}/product/mtProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>用户信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group">
							<input name="uid" type="hidden" value="${mtUser.id}" />
						</div>
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<input name="userNickName" readonly="readonly" type="text" value="${mtUser.userNickName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input name="userRealName" readonly="readonly" type="text" value="${mtUser.userRealName}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别：</label>
							<div class="controls">
								<select name="userSex" class="input-xlarge">
									<option value="" label=""/>
									<c:forEach items="${fns:getDictList('sex')}" var="item">
										<c:choose> 
											<c:when test="${mtUser.userSex == item.value}"> 
												<option value ="${item.value}" selected="selected">${item.label}</option>
											</c:when>
											<c:otherwise>
												<option value ="${item.value}">${item.label}</option>
											</c:otherwise> 
										</c:choose> 
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号：</label>
							<div class="controls">
								<input name="userTel" readonly="readonly" type="text" value="${mtUser.userTel}" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">紧急联系人：</label>
							<div class="controls">
								<input name="userSosName" type="text" value="${mtUser.userSosName}" class="form-control required" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">紧急联系电话：</label>
							<div class="controls">
								<input name="userSosTel" type="text" value="${mtUser.userSosTel}" class="form-control required" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出生日期：</label>
							<div class="controls">
								<input name="userBrith" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="<fmt:formatDate value="${mtUser.userBrith}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最高学历：</label>
							<div class="controls">
								<select name="eduBackground" class="input-xlarge ">
									<option value="" label=""/>
									<c:forEach items="${fns:getDictList('user_edu_bg')}" var="item">
										<c:choose> 
											<c:when test="${mtUser.eduBackground == item.value}"> 
												<option value ="${item.value}" selected="selected">${item.label}</option>
											</c:when>
											<c:otherwise> 
												<option value ="${item.value}">${item.label}</option>
											</c:otherwise> 
										</c:choose> 
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label	">婚姻状况：</label>
							<div class="controls">
								<select name="maritalStatus" class="input-xlarge ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('is_married')}" var="item">
										<c:choose> 
											<c:when test="${mtUser.maritalStatus == item.value}"> 
												<option value ="${item.value}" selected="selected">${item.label}</option>
											</c:when> 
											<c:otherwise> 
												<option value ="${item.value}">${item.label}</option>
											</c:otherwise> 
										</c:choose> 
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">户籍地址：</label>
							<div class="controls">
								<textarea name="censusRegisterAddr" rows="4" cols="20" class="input-xxlarge">${mtUser.censusRegisterAddr}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">现居地址：</label>
							<div class="controls">
								<textarea name="currentAddress" rows="4" cols="20" class="input-xxlarge">${mtUser.currentAddress}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">工作情况：</label>
							<div class="controls">
								<textarea name="jobDescription" rows="4" cols="20" class="input-xxlarge">${mtUser.jobDescription}</textarea>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>产品信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">产品标号：</label>
							<div class="controls">
								<form:input path="pid" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品标题：</label>
							<div class="controls">
								<form:input path="title" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款金额：</label>
							<div class="controls">
								<form:input path="planmoney" htmlEscape="false" class="input-xlarge number required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系人：</label>
							<div class="controls">
								<form:input path="contact" htmlEscape="false" maxlength="30" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系电话：</label>
							<div class="controls">
								<form:input path="contactTel" htmlEscape="false" class="input-xlarge  number"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">收益率：</label>
							<div class="controls">
								<form:input path="hopepercent" htmlEscape="false" class="input-xlarge required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">募集开始时间：</label>
							<div class="controls">
								<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required"
									value="<fmt:formatDate value="${mtProduct.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">募集结束时间：</label>
							<div class="controls">
								<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required"
									value="<fmt:formatDate value="${mtProduct.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款类型：</label>
							<div class="controls">
								<form:select id="unitChange" path="unit" class="input-xlarge required">
									<option value=""></option>
									<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款周期：</label>
							<div class="controls">
								<form:input path="cycle" type="number" min="1" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
								<span>（月/日）</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结算方式：</label>
							<div class="controls">
								<select id="balance-type-hide" style="display:none" class="input-xlarge">
									<c:forEach items="${fns:getDictList('balancetype')}" var="item">
										<option value ="${item.value}" selected="selected">${item.label}</option>
									</c:forEach>
								</select>
								<select id="balance-type" name="balancetype" class="input-xlarge required">									
									<c:forEach items="${fns:getDictList('balancetype')}" var="item">
										<c:choose>
											<c:when test="${mtProduct.unit == 1}">
												<c:choose> 
													<c:when test="${mtProduct.balancetype == item.value}"> 
														<option value ="${item.value}" selected="selected">${item.label}</option>
													</c:when> 
													<c:otherwise>
														<c:if test="${item.value == 2}">
															<option value ="${item.value}">${item.label}</option>
														</c:if>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose> 
													<c:when test="${mtProduct.balancetype == item.value}"> 
														<option value ="${item.value}" selected="selected">${item.label}</option>
													</c:when> 
													<c:otherwise>
														<c:if test="${!(item.value == 2)}">
															<option value ="${item.value}">${item.label}</option>
														</c:if>
													</c:otherwise> 
												</c:choose>
											</c:otherwise> 
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">风险程度：</label>
							<div class="controls">
								<form:select path="risk" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('risk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">展示天数：</label>
							<div class="controls">
								<form:input path="displaydays" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最低投资金额：</label>
							<div class="controls">
								<form:input path="lowmoney" htmlEscape="false" class="input-xlarge number required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">最高投资金额：</label>
							<div class="controls">
								<form:input path="highmoney" htmlEscape="false" class="input-xlarge number required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">当前募集进度：</label>
							<div class="controls">
								<form:input path="currentpercent" htmlEscape="false" class="input-xlarge number"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品状态：</label>
							<div class="controls">
								<%@ include file="./mtStatusForm.jsp"%>
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label">是否为质押标：</label>
							<div class="controls">
								<form:select path="type" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款收费费率：</label>
							<div class="controls">
								<form:input path="managecost" htmlEscape="false" class="input-xlarge number required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投资收费费率：</label>
							<div class="controls">
								<form:input path="investcost" htmlEscape="false" class="input-xlarge number required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投标奖励类型：</label>
							<div class="controls">
								<form:select path="rewardtype" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('rewardtype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">奖励金额：</label>
							<div class="controls">
								<form:input path="rewardamout" htmlEscape="false" class="input-xlarge  number"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">借款用途：</label>
							<div class="controls">
								<form:textarea path="userfor" htmlEscape="false" rows="4" class="input-xxlarge "/>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>安全措施</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">保证金：</label>
							<div class="controls">
								<form:input path="cashdeposit" htmlEscape="false" class="input-xlarge  number"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">担保机构：</label>
							<div class="controls">
								<select name="guarantee" class="input-xlarge">
									<option value="0">无担保</option>
									<c:forEach items="${fns:getGuateeAllList()}" var="item">
										<c:choose> 
											<c:when test="${mtProduct.guarantee == item.id}"> 
												<option value ="${item.id}" selected="selected">${item.msname}</option>
											</c:when> 
											<c:otherwise> 
												<option value ="${item.id}">${item.msname}</option>
											</c:otherwise> 
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">投资期限类型：</label>
							<div class="controls">
								<form:select path="duetime" class="input-xlarge required">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('duetime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">计划介绍：</label>
							<div class="controls">
								<form:textarea path="planinfo" htmlEscape="false" rows="4" class="input-xxlarge "/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">风控措施：</label>
							<div class="controls">
								<form:textarea path="riskcontrol" htmlEscape="false" rows="4" class="input-xxlarge "/>
							</div>
						</div>
						<c:if test="${fns:findPact(mtProduct.pid) != null}">
							<div class="control-group">
								<label class="control-label">借款合同：</label>
								<div class="controls">
									<a href="http://or6py3kp2.bkt.clouddn.com/${fns:findPact(mtProduct.pid).pactSite}" target="_blank">点击查看借款合同</a>
								</div>
							</div>
						</c:if>
						<div class="control-group">
							<label class="control-label">备注信息：</label>
							<div class="controls">
								<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>抵押物信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group"></div>
						<div class="control-group">
							<label class="control-label">汽车品牌：</label>
							<div class="controls">
								<form:input path="carBrand" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车型号：</label>
							<div class="controls">
								<form:input path="carModel" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车颜色：</label>
							<div class="controls">
								<form:input path="carColor" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车燃料：</label>
							<div class="controls">
								<form:input path="carFuel" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车排量：</label>
							<div class="controls">
								<form:input path="carDischarge" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车公里数：</label>
							<div class="controls">
								<form:input path="carKilometer" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车购买时间：</label>
							<div class="controls">
								<input name="carBuytime" type="text" maxlength="20" class="input-xlarge Wdate required"
									value="<fmt:formatDate value="${mtProduct.carBuytime}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车登记时间：</label>
							<div class="controls">
								<input name="carRegtime" type="text" maxlength="20" class="input-xlarge Wdate required"
									value="<fmt:formatDate value="${mtProduct.carRegtime}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车购买价格：</label>
							<div class="controls">
								<form:input path="carBuyPrice" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">汽车评估价格：</label>
							<div class="controls">
								<form:input path="carEstimate" htmlEscape="false" class="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">抵押物图片：</label>
							<div class="controls">
								<form:hidden id="pawnimg" path="pawnimg" htmlEscape="false" maxlength="255" class="input-xlarge"/>
								<sys:ckfinder input="pawnimg" type="files" uploadPath="/product/mtProduct" selectMultiple="true"/>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>首页推荐</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<div class="control-group"></div>				
						<div class="control-group">
							<label class="control-label">是否推荐：</label>
							<div class="controls">
								<form:select path="recommend" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('yes_no')}" var="item" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">推荐位顺序：</label>
							<div class="controls">
								<form:input path="recommendidx" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
							</div>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		
		<div class="form-actions">
			<shiro:hasPermission name="product:mtProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>