package com.imooc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ZhouJie
 * @Description
 * @date 2020/02/16
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<String, String>(16);
    Set<String> roleSet = new HashSet<String>(16);
    Set<String> permissionSet = new HashSet<String>(16);

    {
        userMap.put("zhoujie", "8bb6736cf6254d335d27892f9281fca8");
        roleSet.add("admin");
        roleSet.add("user");
        permissionSet.add("user:update");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionByRole(roles);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    private Set<String> getRolesByUsername(String username) {
        return roleSet;
    }

    private Set<String> getPermissionByRole(Set<String> roles) {

        return permissionSet;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = getPwdByUsername(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, this.getClass().getSimpleName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("zhoujie"));
        return authenticationInfo;
    }

    private String getPwdByUsername(String username) {
        return userMap.get(username);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","zhoujie");
        System.out.println(md5Hash.toString());
    }
}
