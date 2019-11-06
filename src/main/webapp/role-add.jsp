<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加角色</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/script/docs.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/ztree/jquery.ztree.all-3.5.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/ztree/zTreeStyle.css">
	<script type="text/javascript">
        $(function(){

            var setting = {
                check: {
                    enable: true
                },
                async: {
                    enable: true,
                    url:"${pageContext.request.contextPath}/sources/show",
                    type:"GET",
                    autoParam:["id", "name=n", "level=lv"]
                },
            };
            $.fn.zTree.init($("#permissionTree"), setting);

        });
	</script>

	<script type="text/javascript">
		function commit() {
		    //获取前台的值
			var rolename=$("#rolename").val();
            var status=$("#status").val();
            var roledis=$("#roledis").val();

            //获取zTree对象
            var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
            //获取选中的节点  true 为选中 false为未选中的
            var nodes=treeObj.getCheckedNodes(true);
            //遍历选中的id
			var ids="";
            for (var i=0,len=nodes.length;i<len;i++){
                var ids=ids+nodes[i].id+",";
			}
			//将最后的,好截取
			ids=ids.substr(0,ids.length-1);
			//异步刷新提交数据
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/role/saveInfo",
				data:{"rolename":rolename,"status":status,"roledis":roledis,"ids":ids},
				success:function (msg) {
					if (msg.map.statusCode == 200){
					    window.location.href="${pageContext.request.contextPath}/role.jsp";
					} else{
					    if (confirm("添加失败，是否离开当前页面")){
                            window.location.href="${pageContext.request.contextPath}/role.jsp";
						}
					}
                }
			});
        }
	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:权限管理>>添加角色信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form  name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加角色&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">角色名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="rolename" name="rolename"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">状态：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="status" name="status">
			<option value=1>启用</option>
			<option value=0>禁用</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">赋菜单资源：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

		<!--div ztree容器-->
		<div class="panel-body">
			<ul id="permissionTree" class="ztree"></ul>
		</div>

	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 id="roledis" name="roledis"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>