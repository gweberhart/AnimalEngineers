<%@page import="org.ae.server.frame.ServerUtils"%>
<%@page import="org.ae.client.model.Rescue"%>
<% String baseURL = request.getServerName(); %>
<% String idParm = request.getParameter("id"); %>
<% String institutionID = ServerUtils.getInstitutionID(baseURL, idParm); %> 
<% String imagePath = ServerUtils.getImagePath(institutionID); %>
<% Rescue rescue = Rescue.findRescueByRescueId(institutionID); %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="AEHOME.css" />
<title><%=rescue.getName()%></title>
<script type="text/javascript" language="javascript"
	src="AEHOME/AEHOME.nocache.js"></script>
</head>
<body>
<style>
#loading {
	position: absolute;
	left: 45%;
	top: 25%;
	padding: 2px;
	margin-left: -45px;
	z-index: 20001;
	border: 1px solid #ccc;
}
#loading .loading-indicator {
	background: #eef;
	font: bold 13px tahoma, arial, helvetica;
	padding: 10px;
	margin: 0;
	height: auto;
	color: #444;
}
#loading .loading-indicator img {
	margin-right: 8px;
	float: left;
	vertical-align: top;
}
#loading-msg {
	font: normal 10px arial, tahoma, sans-serif;
}
body {
	font: 100%/ 1.4 Verdana, Arial, Helvetica, sans-serif;
	background: <%=rescue.getPageBackgroundColor()%>;
	margin: 0;
	padding: 0;
	color: #000;
}
.dialogFont {
	font: 120%/ 1.4 Verdana, Arial, Helvetica, sans-serif;
}
.header {
	background: black;
	background: repeat-x;
	background-image: url("<%=imagePath%>header_piece.png");
}

.leftBackground {
	background-image: url("<%=imagePath%>GreyhoundLove.jpg");
}
.topBannerStrip {
	background-color: black;
}
.footer {
	font: 80%/ 1.4 Verdana, Arial, Helvetica, sans-serif;
	background: repeat-x;
	background-image: url("<%=imagePath%>footerpiece.png");
	height: 31px;
	width: 100%;
}
.aeFooterText {
	background-color: transparent;
	float: left;
	margin-top: 7px;
	margin-left: 10px;
	text-decoration: none;
}
.aeFooterTextR {
	background-color: transparent;
	float:right;
	margin-top: 7px;
	margin-right: 5px;
	text-decoration: none;
}
a.links{ 
	color:<%=rescue.getForegroundColor()%>; 
}
.container {
	width: 100%;
}
.ImagePointer-enter { 
  cursor:pointer; 
  cursor:hand; 
} 

</style>
<iframe src="javascript:''" id="__gwt_historyFrame"
	style="width: 0; height: 0; border: 0"></iframe>
<noscript>
<div
	style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
Your web browser must have JavaScript enabled in order for this
application to display correctly.</div>
</noscript>
<script type="text/javascript">document.getElementById('loading').innerHTML = '';</script>
<div id="loading">	
<div class="loading-indicator"><img
	src="ExtGWT/images/default/shared/large-loading.gif" /> <%=rescue.getName()%><br/>
<span id="loading-msg">Loading application...</span></div>
</div>
<div class="container">
<div class="header">
<% if (ServerUtils.isGreyhounds(baseURL,idParm)) { %>
<div><p style="padding: 5px">Nevada Greyhounds Unlimited</p></div>
<% } else { %>
<img src="<%=imagePath%>header_left.png" alt="Left Header"/>
<% } %>
</div>
<div class="aeBackgroundColor" id="aeToolBar"></div>
<div class="centered" align="center">
<div id="aeBanner"></div>
<div id="aeMainContent"></div>
<div id="static">
<img src="<%=imagePath%>mainIntro.png" alt="Kindess to animals please."/>
</div>
</div>

<div id="scollBarOffset" style="height:10px"></div> 

<div class="footer">
<div class="aeFooterText">
<a class="links" href="mailto:<%=rescue.getEmailAddress()%>?subject=Animals">Contact</a>
<% if (rescue.getHasFosterApplication()) { %>
&nbsp;&nbsp;&nbsp;
<a class="links" href="<%=imagePath%>nnvbrFosterAgreement.pdf" target="_blank">Foster Application</a>
<% } %>	
<% if (rescue.getHasAdoptionApplication()) { %>
&nbsp;&nbsp;&nbsp;
<a class="links" href="<%=imagePath%>adoption.pdf" target="_blank">Adoption Application</a>
<% } %>

<% if (ServerUtils.isBulldog(baseURL, institutionID)) { %>
&nbsp;&nbsp;&nbsp;
<a class="links" href="https://www.facebook.com/pages/Northern-Nevada-Bulldog-Rescue/181671535207210?ref=hl" target="_blank">Visit us on Facebook</a>
and
<a class="links" href="http://www.petfinder.com/shelters/NV132.html" target="_blank">petfinder.com</a>
<% } %>		

</div>
<span style="color:<%=rescue.getForegroundColor()%>; text-decoration: none;" class="aeFooterTextR">copyright &copy; 2011 <%=rescue.getName()%>&nbsp;&nbsp;</span>
</div>


</div>
</body>
</html>
