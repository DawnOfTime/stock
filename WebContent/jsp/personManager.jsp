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
		var tabTop = "<c:url value='/json/personState.json'/>";
		var rowurl = "<c:url value='/personnel/find'/>";
		var posturl = "<c:url value='/personnel/save'/>";
		var newRow = "{\"id\":\"\",\"name\":\"\",\"sex\":\"\",\"birthday\":\"\",\"id_card_num\":\"\",\"department_num\":\"\",\"rnum\":\"\",\"remarks\":\"\"}";
		var hidcolumns = "id,birthday,id_card_num,password,rnum,remarks";//隐藏列字段名
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
						 		          disabled:'false',
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
						 		          disabled:'false',
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
						 		          disabled:'false',
						 		          panelHeight: 'auto'  
						 		    }  
						         }  
						 	}];
				//要禁用的按钮
				var disableadd = true;
				var disabledel = true;
				
				var formattercol=[{'field': "isnull",
			        'title': "操作",
			        'width': 100,
			        'align': 'center',
			        "formatter":function(value, row, index){
			    		return '<a onclick="resetpassword(this)" id="btn" href="javascript:void(0);" iconCls="icon-add">重置密码</a>';
			        }}];
				
				pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,formattercol,formartColumns,"",[],disableadd,disabledel);
				//pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,"",formartColumns);
			},"json");
		},"json");
	});
	
	function resetpassword(obj){
		var id = $(obj).parent().parent().parent().eq(0).children().eq(0).text();
		var rowindex = $('#dg').datagrid('getRowIndex',id);
		var row = $('#dg').datagrid('getData').rows[rowindex];
		if (row == null){
            $.messager.alert("系统提示", "请选择您要修改的数据！");
            return;
        }
		if (row){
			$.messager.confirm('提示','确定要重置该账户的密码吗？',function(r){   
			    if (r){   
			        //alert(id);   
			        //window.location.href="<c:url value='/personnel/resetpassword?personnel.id='/>"+id;
			        $.post("<c:url value='/personnel/resetpassword?personnel.id='/>"+id);
			        $("#dg").datagrid("load");
			        $.messager.alert('提示','该账户密码已重置！');   
			    }   
			});
        }
		/* var id = $(obj).parent().parent().parent().eq(0).children().eq(0).text();
		var rowindex = $('#dg').datagrid('getRowIndex',id);
		var row = $('#dg').datagrid('getData').rows[rowindex];
		if (row == null){
            $.messager.alert("系统提示", "请选择您要修改的数据！");
            return;
        }
        if (row){
        	$('#tt').tree({  
        		lines:'ture',
   			 	checkbox:'ture',
	   		    url:"<c:url value='/rule/menuTree?rule.id='/>"+id
	   		});
        	$("#saveid").attr("value",id);
        	$("#win").window("open");
        } */
	}
</script>
</head>
<body>
	<table id="dg"></table>
</body>
</html>