<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/jsp/comm/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物品管理</title>
<script type="text/javascript">
	$(document).ready(function(){
		 $('#tt').tree({        
		     url:"<c:url value='/goodsmanagement/tree.action'/>",
		     onContextMenu: function(e, node){
		 		e.preventDefault();
		 		// 选择节点
		 		$('#tt').tree('check', node.target);
		 		var geng = $('#tt').tree('getRoot').id;
				var node = $('#tt').tree('getSelected').id;
				if(node!=geng){
					$("#mm").children().eq(4).attr("style","display:none");
				}else{
					$("#mm").children().eq(4).attr("style","display:block");
				}
		 		// 显示上下文菜单
		 		$('#mm').menu('show', {
		 			left: e.pageX,
		 			top: e.pageY
		 		});
		 	}
		 }); 
		 
		 
	});
	//添加
	function append(){
		$("#save").attr("onclick","saveuser('treeadd')");
		var node = $('#tt').tree('getSelected');
		$("#inputone").attr("value",node.id);
		if(node.children==""){
			$("#inputtwo").attr("value",node.id+"01");
		}else{
			var val = node.children[node.children.length-1].id
			$("#inputtwo").attr("value","0"+(parseInt(val)+1));
		}
	}
	//修改
	function updatenode(){
		cancel();
		$("#save").attr("onclick","saveuser('treeupdate')");
		var node = $('#tt').tree('getSelected');
		var fath = $('#tt').tree('getParent',node.target);
		$("#inputtwo").attr("value",node.id);
		$("#inputone").attr("value",fath.id);
		$("#inputthree").attr("value",node.text);
	}
	//删除
	function removenode(){
		var node = $('#tt').tree('getSelected');
		var child = $('#tt').tree('isLeaf',node.target);
		if(!child){//有子节点
			$.messager.confirm('提示框', '你确定要删除本节点及其子节点吗?',function(r){
				if(r){
					$('#tt').tree('remove',node.target);
					$.post("<c:url value='/OrganServlet.do?fun=treeremove&id='/>"+node.id);
				}
			})
		}
		if(child){
			$.messager.confirm('提示框', '你确定要删除吗?',function(r){
				if(r){
					$('#tt').tree('remove',node.target);
					$.post("<c:url value='/goodsmanagement/removetree.action?goods.num='/>"+node.id);
				}
			});
		}
	}
	//保存修改和添加
	function saveuser(obj){
		//把id为#ff的form表单提交
		$('#ff').form('submit', {
			url: "<c:url value='/goodsmanagement/save.action?fun='/>"+obj,
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 当form不合法的时候隐藏工具条
				}
				return isValid;	// 返回false将停止form提交 
			},
			success: function(){
				$.messager.progress('close');	// 当成功提交之后隐藏进度条
				//验证
				$("#tt").tree("reload");
				$.messager.alert('提示','已保存');
				cancel();
			}
		});
	}
	function cancel(){
		$('#ff').form('reset');
		$('input').attr("value","");
	}
	function refulsh(){
		$("#tt").tree("reload");
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west'" title="物品树" style="width:50%;">
		<ul id="tt"></ul>
    	<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="append()" data-options="iconCls:'icon-add'">添加节点</div>
			<div onclick="removenode()" data-options="iconCls:'icon-remove'">删除节点</div>
			<div onclick="updatenode()" data-options="iconCls:'icon-edit'">修改节点</div>
			<div onclick="refulsh()" data-options="iconCls:'icon-reload'">刷新</div>
		</div>
	</div>
	<div data-options="region:'east'" title="物品详情" style="width:50%;">
		<div style="width:100%;height:100%;text-align:center">
    	<div class="tutintro">
	    	<form id="ff" method="post"> 
	    		<div class="fitem one">  
			        <label for="fatherId">父编码:</label>  
			        <input type="text" name="goods.father_num" id="inputone" readonly/>  
			    </div>  
			    <div class="fitem">  
			        <label for="id">编码:</label>  
			        <input type="text" name="goods.num" id="inputtwo" readonly/>  
			    </div>  
			    <div class="fitem">  
			        <label>物品名称:</label>  
			        <input class="easyui-validatebox" name="goods.name" type="text" id="inputthree" data-options="required:true"/>  
			    </div> 
			    <div id="dlg-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser()" iconcls="icon-save" id="save">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()" iconcls="icon-cancel">取消</a>
			    </div> 
			</form>
		</div>
		</div>
	</div>
</body>
</html>