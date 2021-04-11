package com.cy.pj.sys.service.realm;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.cy.pj.sys.service.remal
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/11 0011 16:28
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
//Shiro框架中Realm对象，通过此对象完成认证和授权业务数据的获取和封装
@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    //负责授权信息的获取和封装
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    //此方法负责认证信息的获取和封装
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户登录时输入的用户名
        UsernamePasswordToken uToken=(UsernamePasswordToken)token;
        String username = uToken.getUsername();
        //2.基于用户名查询数据库中对应的用户信息并校验
        SysUser user = sysUserDao.findUserByUserName(username);
        //3.校验用户是否存在
        if(user==null){
            throw new UnknownAccountException();
        }
        //4.验证用户是否被禁用
        if(user.getValid()==0){
            throw new LockedAccountException();
        }
        //5.对查询到的用户信息进行封装并返回
        ByteSource.Util Util;
        ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());;
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
                user,//用户身份
                user.getPassword(),//已加密的密码
                credentialsSalt,//盐
                getName()
        );
        return info;//info对象会返回给securityManager对象，由此对象调用认证方法对用户信息进行认证
    }

    //设置凭证匹配器，通过此对象对登陆时输入的密码进行加密(set/get选一个就行)
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        //1.构建凭证匹配其对象
        HashedCredentialsMatcher cMather = new HashedCredentialsMatcher();
        //2.设置加密算法
        cMather.setHashAlgorithmName("MD5");
        //3.设置加密次数
        cMather.setHashIterations(1);
        return cMather;
    }

    //    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        //1.构建凭证匹配其对象
//        HashedCredentialsMatcher cMather = new HashedCredentialsMatcher();
//        //2.设置加密算法
//        cMather.setHashAlgorithmName("MD5");
//        //3.设置加密次数
//        cMather.setHashIterations(1);
//        super.setCredentialsMatcher(cMather);//一定要将创建的对象传入父类方法
//    }
}
