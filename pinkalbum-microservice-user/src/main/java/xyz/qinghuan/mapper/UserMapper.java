package xyz.qinghuan.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.qinghuan.dto.UserRegister;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int checkPhoneNumber(String phoneNumber);

    int userRegister(UserRegister userRegister);

    UserRegister userSignIn(@Param("signInPhoneNumber") String signInPhoneNumber, @Param("signInPassword") String signInPassword);

    List<Map> checkDriverSchoolInfo(String signInPhoneNumber);
}
