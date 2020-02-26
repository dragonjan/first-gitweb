package cn.zj.shiro;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {
	
	/*
	 * 
	 * 入门案例。完成Shiro的认证过程
	 * 
	 */
	
	@Test
	public void testName() {
		
		//1.读取shiro.ini 配置文件，创建 工厂对象IniSecurityManagerFactory
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		//2.创建安全管理器SecurityManager
		SecurityManager securityManager = factory.getInstance();
		System.out.println(securityManager);
		
		//3.将安全管理器添加当前线程:和当前线程绑定
		SecurityUtils.setSecurityManager(securityManager);
		
		//4.从当前线程获取Subject 主体
		Subject subject = SecurityUtils.getSubject();
		
		
		//5.创建Toke（令牌），封装账号密码(模拟登陆输入的身份凭证/账号密码)
		AuthenticationToken token = new UsernamePasswordToken("zhangsan", "abc12312312312");
		
		
		//判断是否认证，只有没有情况下认证才去认证
		System.out.println("isAuthenticated :"+subject.isAuthenticated());
		
		if(!subject.isAuthenticated()) {
			
			try {
				//6.认证（登陆）
				subject.login(token);
			} catch (UnknownAccountException e) {
				System.out.println("亲，找不到账号（账号错误）");
			}catch(IncorrectCredentialsException e) {
				System.out.println("亲，无效的凭证（密码错误）");
			}
			
		}
		//判断是否认证
		System.out.println("isAuthenticated :"+subject.isAuthenticated());
		
		//7.推出认证
		subject.logout();
		//判断是否认证
		System.out.println("isAuthenticated :"+subject.isAuthenticated());
		
		
		
	}
}
