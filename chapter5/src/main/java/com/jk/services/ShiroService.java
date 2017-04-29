package com.jk.services;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {

	//只需满足其中一种角色就好
	@RequiresRoles({"role1","admin"})
	public void testRoleMethod(){
		System.out.println("testMethod, time: " + new Date());
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		System.out.println("Service SessionVal: " + val);
	}

	//只需满足其中一种权限就好
	@RequiresPermissions({"user1:*","user4:*"})
	public void testPermissionMethod(){
		System.out.println("testMethod, time: " + new Date());
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		System.out.println("Service SessionVal: " + val);
	}

}
 