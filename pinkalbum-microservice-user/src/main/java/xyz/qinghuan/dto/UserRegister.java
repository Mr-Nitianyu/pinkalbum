package xyz.qinghuan.dto;

public class UserRegister {
    private String id;
    private String registName;
    private String registPhoneNumber;
    private String registPassword;

    public UserRegister() {
    }

    public UserRegister(String id, String registName, String registPhoneNumber, String registPassword) {
        this.id = id;
        this.registName = registName;
        this.registPhoneNumber = registPhoneNumber;
        this.registPassword = registPassword;
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

    @Override
    public String toString() {
        return "UserRegister{" +
                "id='" + id + '\'' +
                ", registName='" + registName + '\'' +
                ", registPhoneNumber='" + registPhoneNumber + '\'' +
                ", registPassword='" + registPassword + '\'' +
                '}';
    }
}
