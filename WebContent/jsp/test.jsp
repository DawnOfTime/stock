<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/jsp/comm/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function FindData(){  
    $('#dg').datagrid('load',{
    		num:$("#PersonCode").val()
        }  
    );  
}  
</script>
</head>
<body>
   <table id='dg' class="easyui-datagrid" style="width:600px;height:500px" url="<c:url value='/menu/find'/>"
           rownumbers="true" toolbar="#searchtool" loadMsg="正在查询...">  
        <thead>  
            <tr>  
                <th field="num" Width="80">菜单编号</th>  
                <th field="name" width="80">菜单名称</th>  
            </tr>  
        </thead>        
    </table>  
      
    <div id="searchtool" style="padding:5px">  
        <span>工号:</span><input type="text" id="PersonCode" value="" size=10 />  
        <span>考勤年月:</span><input type="text" id="KQYM" value="" size=10 />  
          <a href="javascript:FindData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
    <div> 
</body>
</html>