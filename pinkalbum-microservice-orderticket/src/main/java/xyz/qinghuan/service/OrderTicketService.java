package xyz.qinghuan.service;

import java.io.IOException;
import java.util.List;

public interface OrderTicketService {

    int fetchinfo(String account, String password ,String teacherName ,String phoneNum) throws IOException;

    int addOrderTime(String orderTimes,String cookieStat,String orderDate);

    List queryOrderTime(String cookieStat, String orderDate);

    int deleteOrderTime(String orderTimes, String cookieStat, String orderDate);

    List queryfetchinfo(String cookieStat);
}
