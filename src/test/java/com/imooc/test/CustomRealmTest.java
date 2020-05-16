package com.imooc.test;

import com.imooc.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author ZhouJie
 * @Description
 * @date 2020/02/16
 */
public class CustomRealmTest {


    @Test
    public void testAuthentication() {
        CustomRealm customRealm = new CustomRealm();
        //customRealm.setPermissionsLookupEnabled(true);
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhoujie", "123456");
        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
//        subject.checkRoles("admin", "user");
//        subject.checkPermission("user:update");
    }
}
