<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/jsp/comm/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		var tabTop = "<c:url value='/json/easyuitabletop.json'/>";
		var rowurl = "<c:url value='/department/find'/>";
		var posturl = "<c:url value='/department/save'/>";
		var newRow = "{\"id\":\"\",\"num\":\"\",\"name\":\"\"}";
		var hidcolumns = "";//隐藏列字段名
		var id = "";//主键字段名
		pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id);
		
		
/*=======================================================================================*/		
		/* $('#dg').datagrid({    
		    border : 2,  
		    nowrap : false,  
		    fit : true,  
		        url: '<c:url value='/department/find'/>',  
		        frozenColumns: [[    
		                             { title: '区域名称', field: 'regionname', width: 80, sortable: true}    
		                         ]], 
		        columns: [  
		        [{"title":"1","colspan":3},  
		         {"title":"2","colspan":3}],  
		        [{"field":"id","title":"主键","width":"100","align":"center"},
		     	 {"field":"username","title":"用户名","width":"100","editor":{"type":"text"},"align":"center"},
		    	 {"field":"password","title":"密码","width":"100","editor":{"type":"text"},"align":"center"},  
		    	 {"field":"id","title":"主键","width":"100","align":"center"},
		    	 {"field":"username","title":"用户名","width":"100","editor":{"type":"text"},"align":"center"},
		    	 {"field":"password","title":"密码","width":"100","editor":{"type":"text"},"align":"center"}]],    
		        rownumbers: true   
		}); */
	});
</script>
</head>
<body>
	<table id="dg"></table>
</body>
</html>