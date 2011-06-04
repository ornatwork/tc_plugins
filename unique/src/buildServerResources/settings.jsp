<%@include file="/include.jsp"%>
<jsp:useBean id="buildsettings" type="com.deloitte.acs.BuildNumberSettings" scope="request"/>
<h2>UniqueBuildNumber plugin settings</h2>

<form method="get" id="settingform" action="/admin/buildnumbersettings.html">
<div>
  Location of build compile number file
  <BR>

<%@ page import="com.deloitte.acs.UniqueBuildnumber" %>
<%
  
  // Get the filename from the buildnumber issuing class
  String submVal = UniqueBuildnumber.getFileName();
  if( submVal != null && submVal != "" ) 
  	out.print( "<input type='text' value='" + submVal  + "' name='filename' id='filename' size='80' />" );
  else
	out.print( "<input type='text' value='' name='filename' id='filename' size='80' />" );  
%>
   
  <BR>
  <input type="submit" id="frmsubmit" value="Ok" />
</div>
<BR>
<%
  String nextVal = UniqueBuildnumber.peekNextBuildNumber();
  out.print( "Next build number from file: " + nextVal );  
%>
<br/>
<br/>