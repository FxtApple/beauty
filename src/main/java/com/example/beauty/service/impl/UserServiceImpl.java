package com.example.beauty.service.impl;

import com.example.beauty.dao.UserDao;
import com.example.beauty.pojo.User;
import com.example.beauty.request.*;
import com.example.beauty.response.*;
import com.example.beauty.service.UserService;
import com.example.beauty.utils.CodeCache;
import com.example.beauty.utils.CommonUtil;
import com.example.beauty.utils.JsonUtils;
import com.example.beauty.utils.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by NiuLilu on 2020-04-13 16:40.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String saveCode(String phoneNumber) {
        String code=String.valueOf(CommonUtil.random(6));
        CodeCache codeCache = CodeCache.getInstance();
        codeCache.save(phoneNumber, MD5Util.getMD5(code));
        return code;
    }

    @Override
    public String getCode(String phoneNumber) {
        CodeCache instance = CodeCache.getInstance();
        String code = instance.getCode(phoneNumber);
        return code;
    }

    //首页账号密码登陆
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        List<User> list = userDao.login(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        if (!CollectionUtils.isEmpty(list)){
            User user = list.get(0);
            loginResponse.setUser(user);
            String uuid= UUID.randomUUID().toString();
            String token=uuid+","+user.getId()+","+user.getNickName()+","+user.getImgUrl();
            log.info("该用户-{},token-{}",user.toString(),token);
            //将token留在redis中
            stringRedisTemplate.opsForValue().set(token, JsonUtils.objectToJson(user));
            loginResponse.setToken(token);
        }
        return loginResponse;
    }

    //手机验证码登陆
    @Override
    public LoginResponse loginBySms(LoginUserBySmsRequest request) {
        LoginResponse response=new LoginResponse();
        String code = request.getCode();
        String phoneNumber = request.getPhoneNumber();
        //1.调用短信接口  传入手机号和短息验证码
        if (!checkPhoneAndCode(code, phoneNumber)){
            response.setMsg("短信验证失败");
            return response;
        }
        //2.验证通过
        //3.查看当前数据库是否有这个手机的记录
        List<User> userByPhone = userDao.findUserByPhone(phoneNumber);
        //4.无记录，生成新的用户，返还
        if (CollectionUtils.isEmpty(userByPhone)){
            User user=new User();
            user.setPhoneNumber(phoneNumber);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            //构造user的信息
            userDao.addUser(user);
            response.setUser(user);
            String uuid= UUID.randomUUID().toString();
            String token=uuid+","+user.getId()+","+user.getNickName()+","+user.getImgUrl();
            log.info("该用户-{},token-{}",user.toString(),token);
            //将token留在redis中
            stringRedisTemplate.opsForValue().set(token, JsonUtils.objectToJson(user));
            response.setToken(token);
            //生成token，放入redis，返还前端
            return response;
        }
        //5。有记录拿到该记录，返还
        User user = userByPhone.get(0);
        response.setUser(user);
        String uuid= UUID.randomUUID().toString();
        String token=uuid+","+user.getId()+","+user.getNickName()+","+user.getImgUrl();
        log.info("该用户-{},token-{}",user.toString(),token);
        //将token留在redis中
        stringRedisTemplate.opsForValue().set(token, JsonUtils.objectToJson(user));
        response.setMsg(token);
        //生成token，放入redis，返还前端
        return response;
    }

    @Override
    public Integer deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    //注册账号
    @Override
    public Integer addUser(AddUserRequest request) {
        List<User> userByPhone = userDao.findUserByPhone(request.getPhoneNumber());
        if (CollectionUtils.isEmpty(userByPhone)) {
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            return userDao.addUser(user);
        }else
            return 0;
    }


    @Override
    public SelectUserResponse findUserById(Long id) {
        SelectUserResponse selectUserResponse = new SelectUserResponse();
        User user = userDao.findUserById(id);
        selectUserResponse.setUser(user);
        return selectUserResponse;
    }

    @Override
    public SelectUserResopnseList selectAll(SelectRequest request) {
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        List<User> userList = userDao.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        List<User> list = userPageInfo.getList();
        SelectUserResopnseList selectUserResopnseList = new SelectUserResopnseList();
        selectUserResopnseList.setList(userList);
        selectUserResopnseList.setTotal(userPageInfo.getTotal());
        return selectUserResopnseList;
    }

    @Override
    public Integer updateUser(UpdateUserRequest updateUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(updateUserRequest,user);
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer exits(String phoneNumber) {
        return userDao.findUserByPhone(phoneNumber).size();
    }

    private Boolean checkPhoneAndCode(String code,String phoneNumber){
        return true;
    }
}
