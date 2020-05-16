package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author ZhouJie
 * @Description
 * @date 2020/02/15
 */
public class IniRealmTest {

    @Test
    public void testAuthentication(){

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhoujie","123456");
        Subject subject =SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:update");

    }
}
