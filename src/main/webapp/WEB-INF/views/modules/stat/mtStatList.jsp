<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxStatic }/stat/echarts.common.min.js""></script>
	<title>网站流量统计</title>
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
	<style type="text/css">
table{
  border-collapse:collapse;
  font-size:10pt;
}
.totalTable{
  border:1px solid #E5E5E5;
}
.totalLabel{
  color: #666;
  text-align: center;
}
.totalValue{
  font-size:14pt;
  text-align:center;
}
.detailLine{
  border-bottom:1px solid #E5E5E5;
}

</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stat/mtStat/">网站流量统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mtStat" action="${ctx}/stat/mtStat/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
				<li><label style="width:91px">开始时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium" value="${start }"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:91px">结束时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium"  value="${end }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a href="javascript:" class="btn btn-primary" onclick="self.location=document.referrer;">返回</a> </li> 
			 <li class="btns"> <a href="javascript:location.reload()" class="btn btn-primary">刷新</a></li >
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<h3> 日期： ${start } 至 ${end }</h3>
	<table width="100%" cellspacing="0" cellpadding="5" align="center" class="summary">
<tbody><tr>
            <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">浏览量(PV)</td></tr>
           						 <tr><td class="totalValue">${pv }</td></tr>
                       </tbody></table>
    </td>
                <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">访客数(UV)</td></tr>
            <tr><td class="totalValue">${uv }</td></tr>
                                </tbody></table>
    </td>
        </tr>
</tbody></table>

 <div id="main" style="width: 100%;height:400px;"></div>
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        option = {
        	    title: {
        	        text: '实时流量图'
        	    },
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['浏览量(PV)','访客数(UV)']
        	    },
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    xAxis: {
        	        type: 'category',
        	        boundaryGap: false,
        	        data: []
        	    },
        	    yAxis: {
        	        type: 'value'
        	    },
        	    series: [
        	        {
        	            name:'浏览量(PV)',
        	            type:'line',
        	            stack: '总量',
        	            data:[]
        	        },{
        	            name:'访客数(UV)',
        	            type:'line',
        	            stack: '总量',
        	            data:[]
        	        }
        	    ]
        	};


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
            		<script>
            		myChart.setOption({
            			  series: [
            				  {
                  	            name:'访客数(UV)',
                  	            type:'line',
                  	            stack: '总量',
                  	            data:${uvs}
                  	        },{
                  	            name:'浏览量(PV)',
                  	            type:'line',
                  	            stack: '总量',
                  	            data:${pvs}
                  	        }
                  	    ] ,xAxis: {
                	        type: 'category',
                	        boundaryGap: false,
                	        data:${hours }
                	    },
            			});
             		</script>

<table width="100%" cellspacing="0" cellpadding="5" class="table">
    <!-- 表格的thead -->
        <tbody>
    <h3>分时段流量详情</h3>    
        <tr class="thead" bgcolor="#f4f4f4" color="#5D5D5D">
               <td width="14%">浏览量(PV)</td>
                <td width="14%">访客数(UV)</td>
    			  <td width="14%">访问时段</td>
            </tr> 
            			<c:forEach items="${detail}" var="d">
                        <tr>
                                  <td class="detailLine">${d.pv }</td>
               					<td class="detailLine">${d.uv }</td>
                                <td class="detailLine"> ${d.hour }:00  ~ ${d.hour +1 }:00  </td>
                            </tr>
                   </c:forEach>
            </tbody>
            </table>

	
	
	
<table cellspacing="0" cellpadding="5" class="table" style="width:100%">
    <!-- 表格的thead -->
        <tbody>
        <h3>入口页面流量详情</h3>    
        <tr class="thead" bgcolor="#f4f4f4" color="#5D5D5D">
               <td width="14%">浏览量(PV)</td>
                <td width="14%">标题</td>
    			  <td width="14%">地址</td>
            </tr> 
            			<c:forEach items="${title_details}" var="d">
                        <tr>
                        			<td class="detailLine">${d.titlepv }</td>
               					<td class="detailLine">${d.title }</td>
                                 <td class="detailLine"> ${d.url} </td>
                            </tr>
                   </c:forEach>
            </tbody>
</table>

	<div class="pagination">${page}</div>
</body>
</html>