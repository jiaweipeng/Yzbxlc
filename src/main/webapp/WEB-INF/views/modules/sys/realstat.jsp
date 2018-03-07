<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实时访客</title>
	<meta name="decorator" content="default"/>
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
.clear{
    clear: both;
}
</style>
</head>
<body>
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
                <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">跳出次数</td></tr>
            <tr><td class="totalValue">${bounce_count }</td></tr>
                                </tbody></table>
    </td>
                <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">跳出率</td></tr>
            <tr><td class="totalValue">${bounce_rate }</td></tr>
                                </tbody></table>
    </td>
                <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">平均页面点击</td></tr>
            <tr><td class="totalValue"> ${nb_actions_per_visit }</td></tr>
                                </tbody></table>
    </td>
           <td bgcolor="white" align="center" class="totalTable">
        <table width="100%">
                        <tbody><tr><td class="totalLabel">平均停留时间</td></tr>
            <tr><td class="totalValue"> ${avg_time_on_site/1000 }秒</td></tr>
                                </tbody></table>
    </td>
        </tr>
</tbody></table>
<div id="widgetIframe"><iframe width="100%" height="350" src="http://47.92.85.77:81/piwik/index.php?module=Widgetize&action=iframe&widget=1&moduleToWidgetize=Live&actionToWidgetize=widget&idSite=1&period=day&date=today&disableLink=1&widget=1&token_auth=574e872eb614be64a429eb76fb9e14e4" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"></iframe></div>

</tbody></table>
</body>
</html>