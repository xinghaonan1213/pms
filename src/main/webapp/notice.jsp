<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
	<!--导入moment.js时间插件-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/moment.js"></script>
<script type="text/javascript">
	//一点击通知公告就进行向后台发送ajax请求
	//所以需要放置在就绪函数中
	$(function () {
		//发送ajax请求
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/notice/getNoticeList",
			success:function (msg) {
				//将后端数据显示到前台
				if (msg.map.statusCode == 200){
				    //1.遍历数据
					$(msg.map.page.list).each(function (index,item) {
					    //添加的内容为
						var tr="<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                            	+"<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
                        		+"<td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>"
                        		+"<td>"+item.ntitle+"</td>"
                        		+"<td align='center'><span >"+item.remark+"</span></td>"
							    +"<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                        		+"<td><a >删除</a> | <a>编辑</a></td>"
                        		+"</tr>" ;
						//在对应的位置处添加数据
						/*$("#table_list").find("tr:eq(1)").after(tr);*/
						$("#tr0").before(tr);
                    });
                    //2.遍历分页
           var div="<div></div>";
           var uri = msg.map.requestURI;
           //var firstPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+uri+"?pageNum=1"+msg.map.queryStr+"'>首页</a>";
           var firstPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+uri+"?pageNum=1"+msg.map.queryStr+"'>首页</a>";
           var prePage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum-1)+msg.map.queryStr+"'>上一页</a>";
           var nextPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum+1)+msg.map.queryStr+"'>下一页</a>";
           var endPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+msg.map.page.pages+msg.map.queryStr+"'>末页</a>";
           var pages="";
           $(msg.map.page.navigatepageNums).each(function (index,item) {
				pages=pages+" &nbsp;<a onclick='findList(this.name)' href='javascript:void(0)' name='"+uri+"?pageNum="+item+msg.map.queryStr+"'>"+item+"</a> &nbsp;"
           })
           $(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#page_td"));
				}
            }
		});
    });
	function findList(obj) {

        $.ajax({
            type:"get",
            url:obj,
            success:function (msg) {
                //将后端数据显示到前台
                if (msg.map.statusCode == 200){
                    //原来的tr删掉
					$("tr[name=appendtr]").remove();
                    //1.遍历数据
                    $(msg.map.page.list).each(function (index,item) {
                        //添加的内容为
                        var tr="<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                            +"<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
                            +"<td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>"
                            +"<td>"+item.ntitle+"</td>"
                            +"<td align='center'><span >"+item.remark+"</span></td>"
                            +"<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                            +"<td><a >删除</a> | <a>编辑</a></td>"
                            +"</tr>" ;
                        //在对应的位置处添加数据
                        /*$("#table_list").find("tr:eq(1)").after(tr);*/
                        $("#tr0").before(tr);
                    });
                }
            }
        });
        return false;
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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table id="table_list" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>项目完成的咋样?</td>
	<td align="center"><span >最近工作累不？项目完成的咋样?加油哈</span></td>
	<td>2015-02-03</td>
	<td><a >删除</a></td>
</tr>--%>


<tr id="tr0" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td id="page_td" height="36" colspan="12" align="center">

	</td>
</tr>
</table>

</form>
  

</body>
</html>