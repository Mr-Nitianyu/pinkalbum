package xyz.qinghuan.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.qinghuan.dto.FetchInfo;
import xyz.qinghuan.dto.OrderInfo;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderTicketMapper {
    int insertFetchInfo(FetchInfo fetchInfo);

    int checkPhoneNum(String phoneNum);

    int checkAccount(String account);

    int updateFetchInfo(FetchInfo fetchInfo);

    int addOrderTime(OrderInfo orderInfo);

    List queryOrderTime(OrderInfo orderInfo);

    List<Map<String, String>> queryOrderUser(String date);

    List<Map<String, String>> queryOwnOrderTime(@Param("phoneNum")String phoneNum, @Param("date")String date);

    int deleteOrderTime(OrderInfo orderInfo);

    List queryfetchinfo(String userPhone);
}
