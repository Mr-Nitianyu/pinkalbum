package xyz.qinghuan.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import xyz.qinghuan.dto.UserRegister;
import xyz.qinghuan.mapper.UserMapper;
import xyz.qinghuan.service.UserService;
import xyz.qinghuan.utils.MD5Util;
import xyz.qinghuan.utils.MapperUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShardedJedisPool pool;
    private final ObjectMapper mapper = MapperUtil.MP;

    @Override
    public int checkPhoneNumber(String phoneNumber) {
        return userMapper.checkPhoneNumber(phoneNumber);
    }

    @Override
    public int userRegister(UserRegister userRegister) {
        return userMapper.userRegister(userRegister);
    }

    @Override
    public String userSignIn(String signInPhoneNumber, String signInPassword) {
        //利用user数据查询数据库,判断登录是否合法
        //对password做加密操作o
        ShardedJedis jedis = pool.getResource();
        try {
            UserRegister userRegister = userMapper.userSignIn(signInPhoneNumber, signInPassword);
            if (userRegister == null) {//登录失败,没有数据可以存储在redis
                return "201";
            } else {
                //为了后续访问能够获取到user对象的数据,需要创建一个key值,将userJson作为value
                //存储在redis中,key值返回给前端,页面下次访问
                //就可以携带,生成一个cookie将要携带回去的stat
                String stat = UUID.randomUUID().toString();
                String userJson = mapper.writeValueAsString(userRegister);//jackson的代码.将已存在的exist用户对象转化成json
                jedis.setex(stat, 60 * 30, userJson);//将userJson存储在redis中
                return stat;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            //pool.returnResource(jedis);
            jedis.close();
        }
    }

    @Override
    public String queryUserJson(String stat) {
        ShardedJedis jedis = pool.getResource();
        //首先判断超时剩余时间
        Long leftTime = jedis.pttl(stat);
        //少于10分钟,延长5分钟
        if (leftTime < 1000 * 60 * 10L) {
            jedis.pexpire(stat, leftTime + 1000 * 60 * 5);
        }
        String userJson = "";
        try {
            userJson = jedis.get(stat);
            return userJson;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            //pool.returnResource(jedis);
            jedis.close();
        }
    }

    @Override
    public List<Map> checkDriverSchoolInfo(String signInPhoneNumber) {
        return userMapper.checkDriverSchoolInfo(signInPhoneNumber);
    }
}
