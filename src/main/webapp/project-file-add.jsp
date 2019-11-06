<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加附件</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		//一点击添加按钮就实现异步刷新，显示项目的名称
		$(function () {
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/pro/jsonList",
				success:function (msg) {
					//获取的是集合数据，需要遍历
					$(msg).each(function (index,item) {
					    var option="<option value='"+item.pid+"'>"+item.pname+"</option>"
						$("#pros").append(option);
                    });
                }
			});
        });
		function commit() {
			//$("#form2").submit();
			var pid=$("#pros").val();
            var attname=$("#attname").val();
            var attdis=$("#attdis").val();
            var file=$("#file")[0].files[0];
            var remark=$("#remark").val();

			var formdata=new FormData();
			formdata.append("proFk",pid);
            formdata.append("attname",attname);
            formdata.append("attdis",attdis);
            formdata.append("attachment",file);
            formdata.append("remark",remark);
			//实现异步提交
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/attach/saveInfo",
				data:formdata,
				case:false,         //不要在浏览器端有缓存
				processData:false,  //告诉服务器不要进行数据转换
				contentType:false,  //告诉服务器不要设置编码
				
				success:function (msg) {
					if (msg.statusCode == 200){
						window.location.href="${pageContext.request.contextPath}/project-file.jsp";
					}else {
					    if (confirm("您要离开当前页面吗？？？")){
                            window.location.href="${pageContext.request.contextPath}/project-file.jsp";
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
    当前位置:项目管理>>添加附件
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<!--文件的上传和下载的要求
1.enctype=multipart/form-data
2.method=post
3.<input type="file">
-->
<form id="form2" name="form2" action="${pageContext.request.contextPath}/attach/saveInfo" enctype="multipart/form-data" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加附件&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="pros" name="proFk">
			<option value=0>---请选择---</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="26" id="attname" name="attname"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="52" id="attdis" name="attdis"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="file" id="file" name="attachment"/>
	</td>
</tr>


<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 id="remark" name="remark"></textarea>
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