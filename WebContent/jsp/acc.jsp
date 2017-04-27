<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/jsp/comm/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金手掌仓库管理系统</title>
<script type="text/javascript">
	$(document).ready(function(){
		var tabTop = "<c:url value='/json/acc.json'/>";
		var rowurl = "<c:url value='/acc/find'/>";
		var posturl = "<c:url value='/acc/save'/>";
		var newRow = "{\"id\":\"\",\"username\":\"\",\"password\":\"\",\"type\":\"\",\"creattime\":\"\",\"updatetime\":\"\",\"state\":\"\",\"remarks\":\"\"}";
		var hidcolumns = "id";//隐藏列字段名
		var id = "id";//主键字段名
		
		var isenabledOption=[
					   	{valueField:'1',textField:'启用'},
						{valueField:'0',textField:'禁用'}
				 	];
		var formartColumns=
		 	[{
		 		 field: 'state',
		 		 formatter:function(value, row, index){
		 			 var showTxt="";
		 			 $(isenabledOption).each(function(){
		 				 if(value==this.valueField){
		 					 showTxt=this.textField;
		 				 }
		 			 });
		 			 return showTxt;
		 		 },
		 		 editor: {  
		         	type: 'combobox',  
		 		    options: {  
		 		          data: isenabledOption,  
		 		          valueField: 'valueField',  
		 		          textField: 'textField',  
		 		          panelHeight: 'auto'  
		 		    }  
		         }  
		 	}];
		
		pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,"",formartColumns);
	});
</script>
</head>
<body>
	<table id="dg"></table>
</body>
</html>