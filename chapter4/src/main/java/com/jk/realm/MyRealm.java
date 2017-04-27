package com.jk.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

    //授权,调用checkRole/checkPermission/hasRole/isPermitted都会执行该方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //可通过不同principal赋予不同权限
        if (principals.getPrimaryPrincipal().equals("jack")){
            //授予角色role1
            authorizationInfo.addRole("role1");
            authorizationInfo.addRole("role2");
            //授予对user任何行为任何实例的权限
            authorizationInfo.addObjectPermission(new WildcardPermission("user:*"));
            //等同于
            //authorizationInfo.addStringPermission("user:*");
        }
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        //加密算法
        String hashAlgorithName="MD5";
        //加密明文
        String credentials="123456";
        //加密盐值
        ByteSource salt = null;
        //加密盐值
        //盐值通常取唯一的，我们这用用户名作为盐值
        //ByteSource salt= ByteSource.Util.bytes(username);
        //加密次数
        int hashIterations = 1024;
        Object password = new SimpleHash(hashAlgorithName,credentials,salt,hashIterations);
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
