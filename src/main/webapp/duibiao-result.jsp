<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        //一进入此页面就进行异步刷新页面
        $(function () {
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/benchmarking/list/2018",
                success:function (msg) {

                    //初始化echarts
                  var chart=  echarts.init(document.getElementById("main"));
                    // 指定图表的配置项和数据
                    var companys=[];
                    var salesAcount=[];

                    //获取集合遍历
                    $(msg).each(function (index,item) {
                        companys.push(item.companyName);
                        salesAcount.push(item.salesAmount);
                    })
                    var option = {
                        title: {
                            text: '2018同行营销额比较',
                            left:'center',
                            textStyle:{
                                fontStyle:'oblique',
                                fontSize:'30',
                                color:'#FF1493'
                            }
                        },
                        tooltip: {},
                        legend: {
                            data:['销售额']
                        },
                        xAxis: {
                            data:companys
                        },
                        yAxis: {
                            axisLabel:{
                                // 使用字符串模板，模板变量为刻度默认标签 {value}
                             // formatter: '{value} 亿'
                                formatter:function (value,index) {
                                    return value+"(亿)";
                                }
                            }
                        },
                        series: [{
                            name: '营业额比较',
                            type: 'bar',
                            data:salesAcount
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    chart.setOption(option);
                }
            })
        })
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
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width:800px;height:600px;"></div>


</body>
</html>