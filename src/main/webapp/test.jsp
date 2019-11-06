<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/30
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>zTree测试jsp</title>
    <link rel="stylesheet" type="text/css" href="static/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript">
        var setting = {
            async:{
                enable: true,
                url:"${pageContext.request.contextPath}/",
                type:"get",
                //携带的参数
                autoParam: ["id"]
            }
        };

        var zNodes =[
            { name:"父节点1 - 展开",
                children: [
                    { name:"父节点11 - 折叠",
                        children: [
                            { name:"叶子节点111"},
                            { name:"叶子节点112"},
                            { name:"叶子节点113"},
                            { name:"叶子节点114"}
                        ]},
                    { name:"父节点12 - 折叠",
                        children: [
                            { name:"叶子节点121"},
                            { name:"叶子节点122"},
                            { name:"叶子节点123"},
                            { name:"叶子节点124"}
                        ]},
                    { name:"父节点13 - 没有子节点", isParent:true}
                ]},
            { name:"父节点2 - 折叠",
                children: [
                    { name:"父节点21 - 展开", open:true,
                        children: [
                            { name:"叶子节点211"},
                            { name:"叶子节点212"},
                            { name:"叶子节点213"},
                            { name:"叶子节点214"}
                        ]},
                    { name:"父节点22 - 折叠",
                        children: [
                            { name:"叶子节点221"},
                            { name:"叶子节点222"},
                            { name:"叶子节点223"},
                            { name:"叶子节点224"}
                        ]},
                    { name:"父节点23 - 折叠",
                        children: [
                            { name:"叶子节点231"},
                            { name:"叶子节点232"},
                            { name:"叶子节点233"},
                            { name:"叶子节点234"}
                        ]}
                ]},
            { name:"父节点3 - 没有子节点", isParent:true}

        ];
        //ztree的初始化代码
        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </script>
</head>
<body>
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</body>
</html>
