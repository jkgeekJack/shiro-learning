import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by xjk on 17-4-26.
 */
public class UseMD5 {
    public static void main(String[]args){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        //登录信息传密码明文
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");

        try {
            //4、登录，即身份验证
            subject.login(token);
            //验证是否有user1的create权限
            System.out.println(subject.isPermitted("user1:create:*"));
            //验证是否有role1角色
            System.out.println(subject.hasRole("role1"));
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        //6、退出
        subject.logout();
    }
}
