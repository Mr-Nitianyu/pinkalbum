package xyz.qinghuan.dto;

public class UserInfo {
    private String login_name;
    private String PASSWORD;
    private String TYPE;
    private String DEPT_ID;
    private String STUDENT_ID;
    private String TEACHER_ID;
    private String START_TIME;
    private String END_TIME;
    private String KEMUNO;
    private String PHONENUMBER;
    private OwnOrderTime ownOrderTime;

    public OwnOrderTime getOwnOrderTime() {
        return ownOrderTime;
    }

    public void setOwnOrderTime(OwnOrderTime ownOrderTime) {
        this.ownOrderTime = ownOrderTime;
    }

    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
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

    public String getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getKEMUNO() {
        return KEMUNO;
    }

    public void setKEMUNO(String KEMUNO) {
        this.KEMUNO = KEMUNO;
    }
}
