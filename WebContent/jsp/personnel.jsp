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
		var tabTop = "<c:url value='/json/personnel.json'/>";
		var rowurl = "<c:url value='/personnel/find'/>";
		var posturl = "<c:url value='/personnel/save'/>";
		var newRow = "{\"id\":\"\",\"name\":\"\",\"sex\":\"\",\"birthday\":\"\",\"id_card_num\":\"\",\"department_num\":\"\",\"rnum\":\"\",\"remarks\":\"\"}";
		var hidcolumns = "id,password,state";//隐藏列字段名
		var id = "id";//主键字段名
		
		var sexOption=[
							   	{valueField:'1',textField:'男'},
								{valueField:'0',textField:'女'}
						 	];
		var isenabledOption=[
							   	{valueField:'1',textField:'在职'},
								{valueField:'2',textField:'离职'},
								{valueField:'3',textField:'休假'},
								{valueField:'4',textField:'请假'}
						 	];
		//获取角色json
		$.post("<c:url value='/personnel/ruletype'/>",function(data){
			var ruletype = data;
			//获取部门json
			$.post("<c:url value='/personnel/department'/>",function(data1){
				var department = data1;
				var formartColumns=
						 	[{
						 		 field: 'sex',
						 		 formatter:function(value, row, index){
						 			 var showTxt="";
						 			 $(sexOption).each(function(){
						 				 if(value==this.valueField){
						 					 showTxt=this.textField;
						 				 }
						 			 });
						 			 return showTxt;
						 		 },
						 		 editor: {  
						         	type: 'combobox',  
						 		    options: {  
						 		          data: sexOption,  
						 		          valueField: 'valueField',  
						 		          textField: 'textField',  
						 		          panelHeight: 'auto'  
						 		    }  
						         }  
						 	},{
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
						 	},{
						 		 field: 'rnum',
						 		 formatter:function(value, row, index){
						 			 var showTxt="";
						 			 $(ruletype).each(function(){
						 				 if(value==this.valueField){
						 					 showTxt=this.textField;
						 				 }
						 			 });
						 			 return showTxt;
						 		 },
						 		 editor: {  
						         	type: 'combobox',  
						 		    options: {  
						 		          data: ruletype,  
						 		          valueField: 'valueField',  
						 		          textField: 'textField',  
						 		          panelHeight: 'auto'  
						 		    }  
						         }  
						 	},{
						 		 field: 'department_num',
						 		 formatter:function(value, row, index){
						 			 var showTxt="";
						 			 $(department).each(function(){
						 				 if(value==this.valueField){
						 					 showTxt=this.textField;
						 				 }
						 			 });
						 			 return showTxt;
						 		 },
						 		 editor: {  
						         	type: 'combobox',  
						 		    options: {  
						 		          data: department,  
						 		          valueField: 'valueField',  
						 		          textField: 'textField',  
						 		          panelHeight: 'auto'  
						 		    }  
						         }  
						 	}];
				pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,"",formartColumns);
			},"json");
		},"json");
	});
	/* function test(){
		var inputs = $("#test").find("td");
		for (var i = 0; i < inputs.length; i++) {
			var inputobja = $(inputs[i]).html();
			var inputobjb = $(inputs).eq(i).html();
			alert(inputobja+"    "+inputobjb);
		}
	} */
</script>
</head>
<body>
	<table id="dg"></table>
	
	<!-- <table id="test">
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
		</tr>
	</table>
	<input type="button" value="test" onclick="test()"/> -->
</body>
</html>