package xyz.qinghuan.service;

import xyz.qinghuan.dto.UserRegister;

import java.util.List;
import java.util.Map;

public interface UserService {
    int checkPhoneNumber(String phoneNumber);

    int userRegister(UserRegister userRegister);

    String userSignIn(String signInPhoneNumber, String signInPassword);

    String queryUserJson(String stat);

    List<Map> checkDriverSchoolInfo(String stat);
}
