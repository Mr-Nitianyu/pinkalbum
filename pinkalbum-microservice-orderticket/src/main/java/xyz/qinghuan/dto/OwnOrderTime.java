package xyz.qinghuan.dto;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OwnOrderTime {
    public List<Map<String,String>> orderTime;

    public OwnOrderTime() {
    }

    public List<Map<String, String>> getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(List<Map<String, String>> orderTime) {
        this.orderTime = orderTime;
    }

    public OwnOrderTime(List<Map<String, String>> orderTime) {

        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "OwnOrderTime{" +
                "orderTime=" + orderTime +
                '}';
    }
}
