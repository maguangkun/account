package com.cun.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cun.dao.PermissionDao;
import com.cun.dao.RoleDao;
import com.cun.dao.UserDao;
import com.cun.entity.Permission;
import com.cun.entity.Role;
import com.cun.entity.User;

public class MyRealm extends AuthorizingRealm {
	//用户查询
	@Autowired
	private UserDao userDao;
	//角色查询
	@Autowired
	private RoleDao roleDao;
	//是否有权限允许
	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * 角色权限   授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//@RequiresPermissions
		String userName=(String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Set<String> roles=new HashSet<String>();
		List<Role> rolesByUserName = roleDao.getRolesByUserName(userName);
		for(Role role:rolesByUserName) {
			roles.add(role.getRoleName());
		}
		List<Permission> permissionsByUserName = permissionDao.getPermissionsByUserName(userName);
		for(Permission permission:permissionsByUserName) {
			info.addStringPermission(permission.getPermissionName());
		}
		info.setRoles(roles);
		return info;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("token.getPrincipal:" + token.getPrincipal());
		System.out.println("token.getCredentials:" + token.getCredentials());
		String userName = token.getPrincipal().toString();
		User user = userDao.getUserByUserName(userName);
		if (user != null) {
			// Object principal, Object credentials, String realmName
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
			return authcInfo;
		} else {
			return null;
		}
	}

}
