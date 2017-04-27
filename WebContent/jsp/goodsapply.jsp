<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/jsp/comm/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物资申请</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#win").window("close");
		var tabTop = "<c:url value='/json/goodsapply.json'/>";
		var rowurl = "<c:url value='/goodsapply/find'/>";
		var posturl = "<c:url value='/goodsapply/save'/>";
		var newRow = "{\"id\":\"\",\"goodscode\":\"\",\"goodtype\":\"\",\"names\":\"\",\"spec\":\"\",\"model\":\"\",\"unit\":\"\",\"number\":\"\",\"applicant\":\"\",\"applytime\":\"\"}";
		var hidcolumns = "id,approver,approvaltime";//隐藏列字段名
		var id = "id";//主键字段名
		var stateOption = [
							{valueField:'1',textField:'待审核'},
							{valueField:'2',textField:'已通过'},
							{valueField:'3',textField:'未通过'}
					 	];
		var formartColumns = [{
						 		 field: 'state',
						 		 formatter:function(value, row, index){
						 			 var showTxt="";
						 			 $(stateOption).each(function(){
						 				 if(value==this.valueField){
						 					 showTxt=this.textField;
						 				 }
						 			 });
						 			 return showTxt;
						 		 },
						 		 editor: {  
						         	type: 'combobox',  
						 		    options: {  
						 		          data: stateOption,  
						 		          valueField: 'valueField',  
						 		          textField: 'textField',  
						 		          disabled:'false',
						 		          panelHeight: 'auto'  
						 		    }  
						         }  
						 	}];
		pagecomm(tabTop,rowurl,posturl,newRow,hidcolumns,id,"",formartColumns,"1",[],false,true);
		$.post("<c:url value='/goodsapply/getgoods?goods.level=2'/>",function(data){
			for (var i = 0; i < data.length; i++) {
				$("#source .goodscode").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
			}
		},"json");
		$('#win').window({  
			maximized:true,
			onBeforeOpen:function(){
				$("#source tr").clone().appendTo("#copy");
			},
			onClose:function(){
				$("#copy").find("tr").eq(0).nextAll().remove();
				$('#dg').datagrid('reload');
			}
        }); 
	});
	function goodsname(obj,level,index){
		var father_num = $(obj).val();
		var goodsnameObj = $(obj).parent().parent().find("td").eq(index).children();
		$.post("<c:url value='/goodsapply/getgoods?goods.level="+level+"&goods.num='/>"+father_num,function(data){
			$(goodsnameObj).html("<option value=''>-请选择-</option>");
			for (var i = 0; i < data.length; i++) {
				$(goodsnameObj).append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
			}
		},"json");
	}
	function addrow(){
		$("#source tr").clone().appendTo("#copy");
	}
	function delrow(obj){
		var a = $("#copy tr").length;
		if(a==2){
			alert("必须保留一行！");
		}else{
			$(obj).parent().parent().remove();
		}
	}
	function save(){
		var trnum = $(".datas");
		var allstr = "";
		for(var i = 0 ; i < trnum.length ; i++){
			var rowstr = "";
			var tds = $(trnum[i]).children().not(".del");
			$(tds).each(function(){
				var inputobj = $(this).children();
				var str = $(inputobj).val();
				if(rowstr!=""){
					rowstr+=",";
				}
				rowstr+=str;
			});
			if(allstr!=""){
				allstr+="|";
			}
			allstr+=rowstr;
		}
		alert(allstr);
		$.ajax({
            type:"POST",
            url:"<c:url value='/goodsapply/save'/>",
            data:{"goodsApplyArray":allstr},
            datatype: "json",  //"xml", "html", "script", "json", "jsonp", "text".
            success:function(){
            	alert("提交成功");
				$("#win").window("close");
            }
         });
  }
</script>
</head>
<body>
	<table id="dg"></table>
	<div id="win" class="easyui-window" title="物资申请" style="width:800px;height:450px;top:15px"  
        data-options="iconCls:'icon-save',modal:true,closed:true" >
        <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        	<div data-options="region:'center'" style="width:100%;">
        		<%-- <form action="<c:url value='/goodsapply/save'/>" method="post" id="formApply"> --%>
        			申请人：<input type="text" readonly="readonly" value="${person.name}"/><br/>
			    	<table border="1" id="copy">
				    	<tr>
				    		<th width="130">物品类型</th>
				    		<th width="130">物品名称</th>
				    		<th width="130">规格</th>
				    		<th width="180">型号</th>
				    		<th width="180">计量单位</th>
				    		<th width="180">数量</th>
				    		<th width="100" align="center"><input type="button" value="添加" onclick="addrow()"/></th>
				    	</tr>
				    </table>
        		<!-- </form> -->
		    </div> 
	        <div data-options="region:'south'" style="height:40px;width:100%;text-align:center;padding-top:5px">
			    <a href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconcls="icon-save">保存</a>
		    </div> 
        </div>
    </div>
    
    <div style="display: none">
    	<table border="1" id="source">
		    <tr class="datas" align="center">
		    	<td class="del">
		    		<select class="goodscode" onchange="goodsname(this,'3','1')">
		    			<option value="">-请选择-</option>
		    		</select>
		    	</td>
		    	<td class="del">
		    		<select class="names" onchange="goodsname(this,'4','2')">
		    			<option value="">-请选择-</option>
		    		</select>
		    	</td>
		    	<td>
		    		<select name="goodsapply.goodscode" class="spec">
		    			<option value="">-请选择-</option>
		    		</select>
		    	</td>
		    	<td>
		    		<input type="text" name="goodsapply.model" class="model"/>
		    	</td>
		    	<td>
		    		<input type="text" name="goodsapply.unit" class="unit"/>
		    	</td>
		    	<td>
		    		<input type="text" name="goodsapply.number" class="number"/>
		    	</td>
		    	<td class="del">
		    		<input type="button" value="删除" onclick="delrow(this)"/>
		    	</td>
		    </tr>
		</table>
    </div>
</body>
</html>