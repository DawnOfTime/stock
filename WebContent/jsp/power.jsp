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
		$("#win").window("close");
		var tabTop = "<c:url value='/json/rule.json'/>";
		var rowurl = "<c:url value='/rule/find'/>";
		var posturl = "<c:url value='/rule/save'/>";
		var newRow = "{\"id\":\"\",\"rname\":\"\",\"rnum\":\"\"}";
		var hidcolumns = "id";//隐藏列字段名
		var id = "id";//主键字段名
		var formattercol=[{'field': "isnull",
	        'title': "操作",
	        'width': 100,
	        'align': 'center',
	        "formatter":function(value, row, index){
	    		return '<a onclick="fenpei(this)" id="btn" href="#" iconCls="icon-add">分配权限</a>';
	        }}];
		pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,formattercol);
	});
	function fenpei(obj){
		var id = $(obj).parent().parent().parent().eq(0).children().eq(0).text();
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
        }
	}
	function save(){
		var roleid = $("#saveid").val();
		var menus=$('#tt').tree('getChecked');
		var menuIds="";// 定义一个json对象
		for(var i=0;i<menus.length;i++){
			menuIds+=menus[i].id;
			if(i<menus.length-1){
				menuIds+=",";
			}
		}
		if(menus.length<1){
			$.messager.confirm('提示框', '当前角色未选权限,确定继续?',function(r){
				if(r){
					saveRoleMenus(roleid,menuIds);
					$("#win").window("close");
				}
			});
		}else{
			$.messager.confirm('提示框', '新选择的权限将会覆盖原来的权限,确定继续?',function(r){
				if(r){
					saveRoleMenus(roleid,menuIds);
					$("#win").window("close");
				}
			});
		}

	}
	//保存
	function saveRoleMenus(roleid,menuIds){
		//alert(roleid+"   "+menuIds);
		$.ajax({
            type:"POST",
            url:"<c:url value='/rule/powerSet'/>",
            data:{"roleid":roleid,"menuIds":menuIds},
            datatype: "json"  //"xml", "html", "script", "json", "jsonp", "text".
         });
	}
</script>
</head>
<body>
	<table id="dg"></table>
	<div id="win" class="easyui-window" title="权限设置" style="width:600px;height:400px;top:50px"  
        data-options="iconCls:'icon-save',modal:true">
        <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
	        <div data-options="region:'center'" style="width:100%;">
		    	<ul id="tt"></ul>
		    </div> 
	        <div data-options="region:'south'" style="height:40px;width:100%;text-align:center;padding-top:5px">
	        	<form>
	        		<input type="text" id="saveid" name="saveid"/>
			    	<a href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconcls="icon-save">保存</a>
	        	</form>
		    </div> 
        </div>
    </div>
</body>
</html>