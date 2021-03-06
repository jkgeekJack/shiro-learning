import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
public class RealmTest {
    @Test
    public void main(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");

        try {
            //4、登录，即身份验证
            subject.login(token);
            //判断是否有角色,没有则会抛出异常
            subject.checkRole("role1");
            //判断是否有权限,没有则会抛出异常
            subject.checkPermission("user:update:1");
            //判断是否有角色
            System.out.println(subject.hasRole("role1"));
            System.out.println(subject.hasRole("role2"));
            System.out.println(subject.hasRole("role3"));
            //判断有权限
            System.out.println(subject.isPermitted("user:update:1"));
            System.out.println(subject.isPermitted("user:delete:2"));
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace(); 
        }
        System.out.println(subject.isAuthenticated());
        //6、退出
        subject.logout();
    }
}
