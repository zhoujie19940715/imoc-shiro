package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ZhouJie
 * @Description
 * @date 2020/02/15
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccount = new SimpleAccountRealm();

    @Before
    public  void addUser(){
        simpleAccount.addAccount("zhoujie","123456","admin");
    }

    @Test
    public void testAuthentication(){

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccount);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhoujie","123456");
        Subject subject =SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
        subject.checkRole("admin");

    }
}
