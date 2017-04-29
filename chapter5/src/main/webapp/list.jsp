<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>List Page</h4>
	<shiro:guest>
		欢迎游客访问
	</shiro:guest>

	<shiro:user>
		已登录
	</shiro:user>

	<shiro:authenticated>
		已通过认证
	</shiro:authenticated>

	<shiro:notAuthenticated>
		未通过身份认证（包括记住我）
	</shiro:notAuthenticated>

	<shiro:hasRole name="admin">
		拥有角色admin
	</shiro:hasRole>

	<shiro:hasAnyRoles name="admin,role1">
		拥有角色admin或role1
	</shiro:hasAnyRoles>

	<shiro:lacksRole name="admin">
		不拥有角色admin
	</shiro:lacksRole>

	Welcome: <shiro:principal></shiro:principal>
	
	<shiro:hasRole name="admin">
	<br><br>
	<a href="admin.jsp">Admin Page</a>
	</shiro:hasRole>

	<shiro:hasRole name="role1">
	<br><br>
	<a href="role1.jsp">Role1 Page</a>
	</shiro:hasRole>
	
	<br><br>
	<a href="shiro/testShiroAnnotation">Test ShiroAnnotation</a>
	
	<br><br>
	<a href="shiro/logout">Logout</a>
	
</body>
</html>