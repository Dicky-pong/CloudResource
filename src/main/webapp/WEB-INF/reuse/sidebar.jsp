<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--sidebar start-->

<aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">

            <p class="centered"><a href="javascript:openEditUserInfo()">

            <c:if test="${sessionScope.currentUser.imgUrl == ''}">
       			<img src="assets/img/ui-sam.jpg" class="img-circle" width="60" ></a></p>
            </c:if>
            <c:if test="${sessionScope.currentUser.imgUrl != ''}">
                <img src="${sessionScope.currentUser.imgUrl}" class="img-circle" width="60"></a></p>
            </c:if>
            <h5 class="centered">${sessionScope.currentUser.userName}</h5>
            <shiro:hasPermission name='/user/list.htm'> 
                    <li class="mt">
                        <a id="userMainId" href="user/list.htm">
                            <i class="fa fa-user"></i>
                            <span>用户管理</span>
                        </a>
                    </li>
			</shiro:hasPermission>
			<shiro:hasPermission name='/role/list.htm'>
				<li class="sub">
                        <a id="roleMainId" href="role/list.htm">
                            <i class="fa fa-dashboard"></i>
                            <span>角色管理</span>
                        </a>
                    </li>
             </shiro:hasPermission>
             <shiro:hasPermission name='/menu/list.htm'>
				<li class="sub">
                        <a id="menuMainId" href="menu/list.htm">
                            <i class="fa fa-navicon"></i>
                            <span>菜单管理</span>
                        </a>
                    </li>
             </shiro:hasPermission>
             <shiro:hasPermission name='/filec/listFile.htm'> 
                    <li class="sub">
                        <a id="fileMainId" href="filec/listFile.htm">
                            <i class="fa fa-desktop"></i>
                            <span>资源管理</span>
                        </a>
                    </li>
              </shiro:hasPermission>
              <shiro:hasPermission name='/filec/gotoFileLib.htm'> 
                <li class="sub">
                    <a id="fileLibId" href="filec/gotoFileLib.htm">
                        <i class="fa fa-cloud"></i>
                        <span>资源共享库</span>
                    </a>
                </li>
              </shiro:hasPermission>
              <shiro:hasPermission name='/task/listMyFile.htm'> 
                <li class="sub">
                    <a id="myUpLoadId" href="task/listMyFile.htm">
                        <i class="fa fa-tasks"></i>
                        <span>我的资源</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/task/taskList.htm?taskState=0">
                        <li class="sub-menu">
	                        <a id="workMainId" href="javascript:;">
	                            <i class="fa fa-book"></i>
	                            <span>任务管理</span>
	                        </a>
	                        <ul class="sub">
	                        <li><a href="task/taskList.htm?taskState=0">待领任务</a></li>
                        <li><a href="task/taskList.htm?taskState=1">待办任务</a></li>
                        <li><a href="task/showHistroicTask.htm">历史任务</a></li>
                            <li><a href="task/showAllRunningTasks.htm">所有正在运行的任务</a></li>
                    </ul>
                    </li>
                </shiro:hasPermission>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<script src="assets/js/jquery.js"></script>
<script>
    $(function(){
        checkUserInfo();
    })
    //编辑用户基本信息
    function openEditUserInfo(){
        var userUrl = "user/preUserInfo.htm?userId="+${sessionScope.currentUser.userId};
        layer.open({
            type: 2,
            title: ['编辑用户信息','font-family: Helvetica, arial, sans-serif;font-size: 14px;font-weight: bold;'],
            shade: 0.5,
            area: ['600px', '355px'],
            content: [userUrl,'no'],
            move:false
        });
    }

    function checkUserInfo(){
        if('${sessionScope.currentUser.isComplete}'== '0'){
            openEditUserInfo();
            layer.msg("小提示：请完善您的基本信息！",{icon:5,offset:100,shift:6});
        }
      /*   else if("${sessionScope.currentUser.isComplete eq '1'}"){
            openEditUserInfo();
            layer.msg("小提示：绑定邮箱能绑住您找回密码哦！",{icon:5,offset:100,shift:6});
        } */
    }
</script>
