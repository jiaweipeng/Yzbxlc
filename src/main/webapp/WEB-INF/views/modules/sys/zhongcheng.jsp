<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访客忠诚度</title>
	<meta name="decorator" content="default"/>
<style>
</style>
</head>
<body>

 <div id="widgetIframe"><iframe width="100%" height="350" src="http://47.92.85.77:81/piwik/index.php?module=Widgetize&action=iframe&forceView=1&viewDataTable=graphEvolution&widget=1&moduleToWidgetize=VisitFrequency&actionToWidgetize=getEvolutionGraph&idSite=1&period=day&date=today&disableLink=1&widget=1&token_auth=574e872eb614be64a429eb76fb9e14e4" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"></iframe></div>
 <div id="widgetIframe"><iframe width="100%" height="350" src="http://47.92.85.77:81/piwik/index.php?module=Widgetize&action=iframe&widget=1&moduleToWidgetize=VisitorInterest&actionToWidgetize=getNumberOfVisitsByVisitCount&idSite=1&period=day&date=today&disableLink=1&widget=1&token_auth=574e872eb614be64a429eb76fb9e14e4" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"></iframe></div>
 
</tbody></table>
</body>
</html>