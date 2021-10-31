package xyz.qinghuan.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qinghuan.dto.UserRegister;
import xyz.qinghuan.service.UserService;
import xyz.qinghuan.utils.CookieUtils;
import xyz.qinghuan.vo.SysResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/manage")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 检查注册账号是否已使用
     * @param phoneNumber
     * @return
     */
    @RequestMapping("/checkPhoneNumber")
    public SysResult checkPhoneNumber(String phoneNumber){
        int stat = userService.checkPhoneNumber(phoneNumber);
        SysResult result = new SysResult();
        if (stat == 0){
          result = SysResult.ok();
        }else {
            result.setStatus(300);
            result.setMsg("该手机号已注册，请登录");
        }
        return result;
    }
    @RequestMapping("/userRegister")
    public SysResult userRegister(UserRegister userRegister){
        int stat = userService.userRegister(userRegister);
        SysResult result = new SysResult();
        if (stat == 1){
          result = SysResult.ok();
        }else {
            result.setStatus(300);
            result.setMsg("注册失败");
        }
        return result;
    }
    @RequestMapping("/userSignIn")
    public SysResult userSignIn(String signInPhoneNumber,String signInPassword, HttpServletRequest request, HttpServletResponse response) {
        String stat = userService.userSignIn(signInPhoneNumber, signInPassword);
        if (stat == "201") {
            return SysResult.build(201, "账号或密码不正确", null);
        } else if(StringUtils.isNotEmpty(stat)) {//
            //stat,redis存好登录的查询结果
            //将stat作为cookie的值返回 cookie
            //的名称,调用Cookie工具类	//添加到cookie返回给前端
            CookieUtils.setCookie(request, response, "QH_TICKET", stat,60*60*24);
            return SysResult.ok();
        }else{
            //什么都不做直接返回失败
            return SysResult.build(201, "失败", null);
        }
    }
    /**
     *  通过cookie携带的ticket值查询redisuser数据
     */
    @RequestMapping("/checkLoginUser")
    public SysResult checkLoginUser(String stat){
        String userJson=userService.queryUserJson(stat);
        //判断非空
        if(StringUtils.isNotEmpty(userJson)){
            //确实曾经登录过.也正在登录使用状态中
            return SysResult.build(200, "ok", userJson);
        }else{
            return SysResult.build(201, "", null);
        }
    }
    /**
     *  通过手机号判断是否获取过驾校信息
     */
    @RequestMapping("/checkDriverSchoolInfo")
    public SysResult checkDriverSchoolInfo(String signInPhoneNumber,String signInPassword){
        List<Map> userJson=userService.checkDriverSchoolInfo(signInPhoneNumber);
        //判断非空
        if(userJson.size()>0){
            return SysResult.build(200, "ok", userJson);
        }else{
            return SysResult.build(201, "", null);
        }
    }

}
