package xyz.qinghuan.utils;

import xyz.qinghuan.dto.UserInfo;

import java.util.Arrays;

public class ReqUrl {
    private final String login_name;
    private final String PASSWORD;
    private final String TYPE;
    private final String DEPT_ID;
    private final String STUDENT_ID;
    private final String TEACHER_ID;
    private final String START_TIME;
    private final String END_TIME;
    private final String KEMUNO;

    String orderTime = new GetOrderTime().getTime();

    private String REDIRECT_URL;
    private String LOAD_URL;
    private String SELECDATET_URL;
    private String SELECTEACHER_URL;
    private String SELECTTIME_URL;
    private String SUBMITDATA_URL;
    private String[] urls = new String[7];

    public ReqUrl(UserInfo userInfo) {
        this.login_name = userInfo.getLogin_name();
        this.PASSWORD = userInfo.getPASSWORD();
        this.TYPE = userInfo.getTYPE();
        this.DEPT_ID = userInfo.getDEPT_ID();
        this.STUDENT_ID = userInfo.getSTUDENT_ID();
        this.TEACHER_ID = userInfo.getTEACHER_ID();
        this.START_TIME = userInfo.getSTART_TIME();
        this.END_TIME = userInfo.getEND_TIME();
        this.KEMUNO = userInfo.getKEMUNO();
        REDIRECT_URL = "http://es.kucarlife.com:9090/servplat_HH/WebAPPPage/sign.php?login_name=" + login_name + "&PASSWORD=" + PASSWORD + "&TYPE=" + TYPE + "&y_url=";
        LOAD_URL = "http://es.kucarlife.com:9090/servplat/WebAppPage/mycenter.php";
        SELECDATET_URL = "http://es.kucarlife.com:9090/servplat/webAppPage/studydate/Smart_DateStudySelect.php";
        SELECTEACHER_URL = "http://es.kucarlife.com:9090/servplat/webAppPage/studydate/Smart_DateStudy.php?YEAR=" + new GetOrderTime().getYear() + "&MONTH=" + new GetOrderTime().getMonth() + "&DAY=" + new GetOrderTime().getDay();
        SELECTTIME_URL = "http://es.kucarlife.com:9090/servplat/webAppPage/studydate/smartdodate.php?DEPT_ID=" + DEPT_ID + "&STUDENT_ID=" + STUDENT_ID + "&TEACHER_ID=" + TEACHER_ID + "&CDATE=" + orderTime + "&KEMUNO=" + KEMUNO + "&VM_NUM=%CF%E6GJ638%D1%A7";
        SUBMITDATA_URL = "http://es.kucarlife.com:9090/servplat/webAppPage/studydate/adddate.php?DEPT_ID=" + DEPT_ID + "&STUDENT_ID=" + STUDENT_ID + "&TEACHER_ID=" + TEACHER_ID + "&CDATE=" + orderTime + "&START_TIME=" + START_TIME + "&END_TIME=" + END_TIME + "&KEMUNO=" + KEMUNO;
        urls = new String[]{REDIRECT_URL, LOAD_URL, SELECDATET_URL, SELECTEACHER_URL, SELECTTIME_URL, SUBMITDATA_URL};
    }


    public void setREDIRECT_URL(String REDIRECT_URL) {
        this.REDIRECT_URL = REDIRECT_URL;
    }

    public void setLOAD_URL(String LOAD_URL) {
        this.LOAD_URL = LOAD_URL;
    }

    public void setSELECDATET_URL(String SELECDATET_URL) {
        this.SELECDATET_URL = SELECDATET_URL;
    }

    public void setSELECTEACHER_URL(String SELECTEACHER_URL) {
        this.SELECTEACHER_URL = SELECTEACHER_URL;
    }

    public void setSELECTTIME_URL(String SELECTTIME_URL) {
        this.SELECTTIME_URL = SELECTTIME_URL;
    }

    public void setSUBMITDATA_URL(String SUBMITDATA_URL) {
        this.SUBMITDATA_URL = SUBMITDATA_URL;
    }


    public String getREDIRECT_URL() {
        return REDIRECT_URL;
    }

    public String getLOAD_URL() {
        return LOAD_URL;
    }

    public String getSELECDATET_URL() {
        return SELECDATET_URL;
    }

    public String getSELECTEACHER_URL() {
        return SELECTEACHER_URL;
    }

    public String getSELECTTIME_URL() {
        return SELECTTIME_URL;
    }

    public String getSUBMITDATA_URL() {
        return SUBMITDATA_URL;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String getLogin_name() {
        return login_name;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    @Override
    public String toString() {
        return "ReqUrl{" +
                "login_name='" + login_name + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", DEPT_ID='" + DEPT_ID + '\'' +
                ", STUDENT_ID='" + STUDENT_ID + '\'' +
                ", TEACHER_ID='" + TEACHER_ID + '\'' +
                ", START_TIME='" + START_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", KEMUNO='" + KEMUNO + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", REDIRECT_URL='" + REDIRECT_URL + '\'' +
                ", LOAD_URL='" + LOAD_URL + '\'' +
                ", SELECDATET_URL='" + SELECDATET_URL + '\'' +
                ", SELECTEACHER_URL='" + SELECTEACHER_URL + '\'' +
                ", SELECTTIME_URL='" + SELECTTIME_URL + '\'' +
                ", SUBMITDATA_URL='" + SUBMITDATA_URL + '\'' +
                ", urls=" + Arrays.toString(urls) +
                '}';
    }
}
