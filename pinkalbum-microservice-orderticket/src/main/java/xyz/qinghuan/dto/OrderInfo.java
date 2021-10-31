package xyz.qinghuan.dto;

public class OrderInfo {
    private String id;
    private String registName;
    private String registPhoneNumber;
    private String registPassword;
    private String startTime;
    private String endTime;
    private String orderDate;

    public OrderInfo() {
    }

    public OrderInfo(String id, String registName, String registPhoneNumber, String registPassword, String startTime, String endTime, String orderDate) {
        this.id = id;
        this.registName = registName;
        this.registPhoneNumber = registPhoneNumber;
        this.registPassword = registPassword;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRegistName() {
        return registName;
    }

    public void setRegistName(String registName) {
        this.registName = registName;
    }

    public String getRegistPhoneNumber() {
        return registPhoneNumber;
    }

    public void setRegistPhoneNumber(String registPhoneNumber) {
        this.registPhoneNumber = registPhoneNumber;
    }

    public String getRegistPassword() {
        return registPassword;
    }

    public void setRegistPassword(String registPassword) {
        this.registPassword = registPassword;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id='" + id + '\'' +
                ", registName='" + registName + '\'' +
                ", registPhoneNumber='" + registPhoneNumber + '\'' +
                ", registPassword='" + registPassword + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
