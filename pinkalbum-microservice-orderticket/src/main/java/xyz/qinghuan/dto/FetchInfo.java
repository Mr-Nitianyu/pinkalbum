package xyz.qinghuan.dto;

public class FetchInfo {
    String LOGIN_NAME;
    String LOGIN_PASSWORD;
    String LOGIN_TPYE;
    int KENUM;
    String DEPT_ID;
    String STUDENT_ID;
    String TEACHER_ID;
    String VM_NUM;
    String PHONE_NUM;
    String TEACHER_NAME;

    public FetchInfo() {
    }

    public FetchInfo(String LOGIN_NAME, String LOGIN_PASSWORD, String LOGIN_TPYE, int KENUM, String DEPT_ID, String STUDENT_ID, String TEACHER_ID, String VM_NUM, String PHONE_NUM, String TEACHER_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
        this.LOGIN_PASSWORD = LOGIN_PASSWORD;
        this.LOGIN_TPYE = LOGIN_TPYE;
        this.KENUM = KENUM;
        this.DEPT_ID = DEPT_ID;
        this.STUDENT_ID = STUDENT_ID;
        this.TEACHER_ID = TEACHER_ID;
        this.VM_NUM = VM_NUM;
        this.PHONE_NUM = PHONE_NUM;
        this.TEACHER_NAME = TEACHER_NAME;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }

    public String getLOGIN_PASSWORD() {
        return LOGIN_PASSWORD;
    }

    public void setLOGIN_PASSWORD(String LOGIN_PASSWORD) {
        this.LOGIN_PASSWORD = LOGIN_PASSWORD;
    }

    public String getLOGIN_TPYE() {
        return LOGIN_TPYE;
    }

    public void setLOGIN_TPYE(String LOGIN_TPYE) {
        this.LOGIN_TPYE = LOGIN_TPYE;
    }

    public int getKENUM() {
        return KENUM;
    }

    public void setKENUM(int KENUM) {
        this.KENUM = KENUM;
    }

    public String getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(String DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public String getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(String STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public String getVM_NUM() {
        return VM_NUM;
    }

    public void setVM_NUM(String VM_NUM) {
        this.VM_NUM = VM_NUM;
    }

    public String getPHONE_NUM() {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM) {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getTEACHER_NAME() {
        return TEACHER_NAME;
    }

    public void setTEACHER_NAME(String TEACHER_NAME) {
        this.TEACHER_NAME = TEACHER_NAME;
    }

    @Override
    public String toString() {
        return "FetchInfo{" +
                "LOGIN_NAME='" + LOGIN_NAME + '\'' +
                ", LOGIN_PASSWORD='" + LOGIN_PASSWORD + '\'' +
                ", LOGIN_TPYE='" + LOGIN_TPYE + '\'' +
                ", KENUM=" + KENUM +
                ", DEPT_ID='" + DEPT_ID + '\'' +
                ", STUDENT_ID='" + STUDENT_ID + '\'' +
                ", TEACHER_ID='" + TEACHER_ID + '\'' +
                ", VM_NUM='" + VM_NUM + '\'' +
                ", PHONE_NUM='" + PHONE_NUM + '\'' +
                ", TEACHER_NAME='" + TEACHER_NAME + '\'' +
                '}';
    }
}
