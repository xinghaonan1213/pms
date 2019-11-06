
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试图例</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        //类似于jquery的就绪函数$(function(){})
        window.onload=function () {
            var keys=[];
            var values=[];
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/echart/getEcharts",
                success:function (msg) {
                    for ( key in msg) {
                       keys.push(key);
                       values.push(msg[key]);
                    }
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: 'ECharts 入门示例',
                            subtext:'这是副标题',
                            textStyle:{
                                fontStyle:'oblique',
                                fontSize:'30',
                                color:'#FF1493'
                            }
                        },
                        tooltip: {},
                        legend: {
                            data:['销量']
                        },
                        xAxis: {
                            data: keys
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'pie',
                            data: values
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });
        };
    </script>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>
