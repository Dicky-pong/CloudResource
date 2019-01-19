<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>CRMS主页</title>

    <!-- 引入全局css样式 -->
    <jsp:include page="/WEB-INF/reuse/css.jsp"/>


    <script type="text/javascript">
        function del(roleId,url) {
           layer.confirm('删除后无法恢复,确定要删除吗？', {icon: 0, title:'警告',offset:30}, function(index){
//                if($(".cb:checked").length<=0){
//                    layer.alert("请至少选择一项!",{icon:3,title:'提醒',offset:30,shift:6});
//                    return;
//                }
                $.get(
                        "${pageContext.request.contextPath}"+url+"",
                        {
                            roleId : roleId
                        },
                        function(data) {

                            var data = eval("(" + data + ")"); //将data 转换成JS对象，这样才可以使用data.msg这种形式

                            if ("success" == data.msg) {
                                layer.msg(data.msg+"删除成功！", {icon: 1,time:1000,offset:100},function(){
                                    window.location.href = "role/list.htm";
                                });
                            } else if ("error" == data.msg){
                                layer.msg("角色已分配，不能删除！",{icon:5,offset:100,shift:6});
                            }
                        });
            });
        }

    </script>
</head>

<body>

<section id="container">
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header-->
    <jsp:include page="/WEB-INF/reuse/header.jsp"/>

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar-->
    <jsp:include page="/WEB-INF/reuse/sidebar.jsp"/>

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->

    <section id="main-content">

        <section class="wrapper site-min-height">
            <h3><i class="fa fa-angle-right"></i>角色管理</h3>
            <div class="row mt">
                <div class="col-lg-12">
		                    <div class="col-lg-4">
		                        <button type="button" class="btn btn-theme02" onclick="openAddRole('role/preinsert.htm')"><i class="glyphicon glyphicon-plus"></i>新增</button>
		                    </div>
<%--                     <c:if test="${oper.funName=='角色查询'}">
 --%>                    <div class="col-lg-8">
                        <div class="pull-right">
                            <form class="form-inline" role="form" action="role/list.htm" method="post">

                                <div class="form-group">
                                    <label class="control-label" for="roleNameId">角色名：</label>
                                    <input type="text" name="roleName" class="form-control" id="roleNameId" placeholder="">
                                </div>
                                <button type="submit" class="btn btn-theme">搜索<i class="glyphicon glyphicon-search"></i></button>
                            </form>
                        </div>
                    </div><!-- /form-panel -->
                   <%--  </c:if> --%>
                </div>
            </div>

            <div class="row mt">
                <div class="col-lg-12">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th><center>序号 </center></th>
                            <th><center>角色名 </center></th>
                            <th><center>说明 </center></th>
                            <th><center>权限 </center></th>
                            <th><center>操作</center></th>

                        </tr>
                        <c:forEach var="role" items="${roleList}" varStatus="status">
                            <tr>
                                <td width="50px"><center>${status.count } </center></td>
                                <td><center>${role.role_name } </center></td>
                                <td><center>${role.role_descript} </center></td>
                                <td style="WORD-WRAP: break-word"  width="500px">
                                    ${role.permissionNames}
                                    </td>

                                <td width="180px">
                                    <center>
                                        <div style="text-align:center">
                                            <table width="100%">
                                                <tr>
                                                      <td> <a  class="btn btn-default btn-xs" href="javascript:openEditRole('${role.role_id.toString()}','role/preUpdate.htm')" role="button" title="菜单"> <i class="fa fa-gear"></i></a></td>
                                                      <td> <a class="btn btn-default btn-xs" href="javascript:openEditOper('${role.role_id.toString()}','role/preUpdateOper.htm')" role="button" title="功能"> <i class="fa fa-gears"></i></a></td>
                                                      <td> <a class="btn btn-default btn-xs" href="javascript:del('${role.role_id.toString()}','/role/delete.htm')" role="button" title="删除"><i class="glyphicon glyphicon-trash"></i></a></td>
                                                </tr>
                                            </table>

                                        </div>
                                    </center>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="row centered">
                        <nav>
                            <ul class="pagination">${pageCode }
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <!-- wrapper -->
    </section>

    <!-- /MAIN CONTENT -->

    <!--main content end-->

    <!--footer start-->
    <jsp:include page="/WEB-INF/reuse/footer.jsp"/>
    <!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<jsp:include page="/WEB-INF/reuse/js.jsp"/>
<!-- 让侧边栏菜单高亮 -->
<script>$("#roleMainId").attr({"class" : "active"});</script>
<!--引入此页面的js-->
<script type="text/javascript" src="res/js/role/roleMain.js"></script>
</body>
</html>
