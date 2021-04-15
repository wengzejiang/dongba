package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequestLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.utils.ShiroUtils;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.common.vo.SysUserMenuVo;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Package: com.cy.pj.sys.service.impl
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 15:36
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
@Slf4j
@Transactional(timeout = 30,	//超时时间30就回滚
        readOnly = false,//悲观锁,true一般只用于查询
        isolation = Isolation.READ_COMMITTED,//避免脏读
        rollbackFor = Throwable.class, //出现异常就事务
        propagation = Propagation.REQUIRED)//事务传播特性
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Override
    @RequestLog(value = "用户查询")
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
            //1.对参数进行校验
            if(pageCurrent==null||pageCurrent<1)
                throw new IllegalArgumentException("当前页码值无效");
            //2.查询总记录数并进行校验
            int rowCount=sysUserDao.getRowCount(username);
            if(rowCount==0)
                throw new ServiceException("没有找到对应记录");
            //3.查询当前页记录
            int pageSize=10;
            int startIndex=(pageCurrent-1)*pageSize;
            List<SysUserDeptVo> records=
                    sysUserDao.findPageObjects(username,
                            startIndex, pageSize);
            //4.对查询结果进行封装并返回
        return new PageObject<>(rowCount, records, pageCurrent, pageSize);
        }


    @Override
    //1.由已认证的用户进行授权
    //2.认证用户具备访问这个资源的权限
    //3.1用户可以访问哪些菜单-菜单中有一个权限标识字段
    //3.2检查用户拥有的权限标识中是否包含@RequiresPermissions注解中定义的字符串，假如有则认为有权限
    //由谁授权？SecurityManager(这个对象本身就是一个授权管理器)
    //表示此方法必须授权才能访问
    @RequiresPermissions("sys:user:update")
    @RequestLog(value = "禁用启动")//注解中的内容表示操作
    public int validById(Integer id, Integer valid, String modifiedUser) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("参数不合法,id="+id);
        if(valid!=1&&valid!=0)
            throw new ServiceException("参数不合法,valie="+valid);
        if(StringUtils.isEmpty(modifiedUser))
            throw new ServiceException("修改用户不能为空");
        //2.执行禁用或启用操作
        int rows=sysUserDao.validById(id, valid, modifiedUser);
        //3.判定结果,并返回
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        return rows;
    }

    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        log.info("method start:"+System.currentTimeMillis());
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new ServiceException("用户名不能为空");
        if(StringUtils.isEmpty(entity.getPassword()))
            throw new ServiceException("密码不能为空");
        if(roleIds==null || roleIds.length==0)
            throw new ServiceException("至少要为用户分配角色");
        String source=entity.getPassword();
        String salt= UUID.randomUUID().toString();
        SimpleHash simpleHash=new SimpleHash("MD5", source, salt,1);
        entity.setSalt(salt);
        entity.setPassword(simpleHash.toHex());
        int rows = sysUserDao.insertObject(entity);
        sysUserRoleDao.insertObjects(entity.getId(),roleIds);
        log.info("method end:"+System.currentTimeMillis());
        return rows;
    }

    @Override
    //方法上的@Transactional注解用于定义事务特性,readOnly = true一般用于描述查询方法，表示这是一个读事务，允许并发读
    @Transactional(readOnly = true)
    public Map<String, Object> findObjectById(Integer userId) {
        log.info("method start {}", System.currentTimeMillis());
        //1.合法性验证
        if(userId==null||userId<=0){
            throw new ServiceException("参数数据不合法,userId="+userId);
        }
        SysUserDeptVo user = sysUserDao.findObjectById(userId);
        log.info("================"+user);
        if(user==null){
            throw new ServiceException("用户可能不存在");
        }
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
        log.info("================"+roleIds);
        Map<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("roleIds",roleIds);
        log.info("method end {}", System.currentTimeMillis());
        return map;
    }

    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        //1.参数有效性验证
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if(roleIds==null||roleIds.length==0)
            throw new IllegalArgumentException("必须为其指定角色");
        //其它验证自己实现，例如用户名已经存在，密码长度，...
        //2.更新用户自身信息
        int rows=sysUserDao.updateObject(entity);
        //3.保存用户与角色关系数据
        sysUserRoleDao.deleteObjectsByUserId(entity.getId());
        sysUserRoleDao.insertObjects(entity.getId(),
                roleIds);
        //4.返回结果
        return rows;
    }

    @Override
    public int updatePassword(String sourcePassword, String newPassword, String cfgPassword) {
        //1.参数校验
        //1)非空校验
        if(StringUtils.isEmpty(sourcePassword)){
            throw new IllegalArgumentException("原密码不能为空");
        }
        if(StringUtils.isEmpty(newPassword)){
            throw new IllegalArgumentException("新密码不能为空");
        }
        //2)新密码和确认密码是否相同
        if(!newPassword.equals(cfgPassword)){
            throw new IllegalArgumentException("密码和确认起码不一致");
        }
        //3)校验原密码是否正确(将sourcePass加密以后与数据库中的密码进行比对)
        SysUser user=ShiroUtils.getUser();
        SimpleHash sHash=new SimpleHash("MD5", sourcePassword, user.getSalt(),1 );
        String hashedpassword = user.getPassword();
        if(!hashedpassword.equals(sHash.toHex())){//sHash.toHex()密码加密后转换为16进制
            throw new ServiceException("原密码输入不正确");
        }
        //2.修改密码
        //1)获取新的盐值
        String newSalt = UUID.randomUUID().toString();
        //2)对用户输入的新的密码进行加密
        sHash=new SimpleHash("MD5", newPassword,newSalt,1 );
        //3)更新密码
        int rows = sysUserDao.updatePassword(sHash.toHex(), newSalt, user.getId());
        //3.返回结果
        return rows;
    }

    @Override
    public List<SysUserMenuVo> findUserMenusByUserId(Integer userId) {
        //1.对用户id进行判断
        //2.基于用户id查找用户对应的角色id
        List<Integer> roleIds= sysUserRoleDao.findRoleIdsByUserId(userId);
        //3.基于角色id获取角色对应的菜单信息,并进行封装.
        List<Integer> menuIds=sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(new Integer[] {}));
        //4.基于菜单id获取用户对应的菜单信息并返回
        return sysMenuDao.findMenusByIds(menuIds);
    }
}
