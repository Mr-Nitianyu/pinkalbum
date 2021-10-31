package xyz.qinghuan.thread;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.qinghuan.dto.UserInfo;
import xyz.qinghuan.mobilephone.SendSms;
import xyz.qinghuan.service.RobTicket;
import xyz.qinghuan.utils.GetOrderTime;
import xyz.qinghuan.utils.ReqUrl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExecuteRob extends Thread {
    private final Logger logger = LoggerFactory.getLogger(ExecuteRob.class);
    private boolean flag;
    private int times;
    private final Map<String,String> headersMap;
    private final UserInfo userInfo;
    private final String cookie;
    private final int ThreadName;
    private final RobTicket robTicket;
    private final GetOrderTime getOrderTime = new GetOrderTime();
    private final String temple;


    public ExecuteRob(boolean flag, int times, Map headersMap, UserInfo userInfo, String cookie, int ThreadName, RobTicket robTicket, String temple) {
        this.flag = flag;
        this.times = times;
        this.headersMap = headersMap;
        this.userInfo = userInfo;
        this.cookie = cookie;
        this.ThreadName = ThreadName;
        this.robTicket = robTicket;
        this.temple = temple;
    }


    @Override
    @SneakyThrows
    public void run() {
        if (ThreadName == 10) {
            try {
                evaluateTeacher();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("threadtask" + ThreadName + ":started");
        while (flag) {
            Map<String,String> map = new HashMap<String, String>();
            String url = new ReqUrl(userInfo).getSUBMITDATA_URL();
            map.put("url", url);
            map.put("referer", new ReqUrl(userInfo).getSELECTTIME_URL());
            map.putAll(headersMap);
            map.put("cookie", cookie);
            Map<String, Object> returnData = null;
            try {
                returnData = robTicket.executePost(map, times);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info(url);
            if (((String) returnData.get("msg")).contains("1") && ((String) returnData.get("msg")).contains("***科目三***")) {
                flag = false;
                try {
                    logger.info("threadtask" + ThreadName + " :" + URLDecoder.decode(new ReqUrl(userInfo).getLogin_name(), "GBK") + "预约成功，退出！--------" + new ReqUrl(userInfo).getPASSWORD() + " OrderSuccessfully,exit");
                    SendSms sms = new SendSms("https://zwp.market.alicloudapi.com", "/sms/sendv2", "GET", "ec929179690b454cbb2a71154e6f81f4", temple);
                    sms.sendMessage(userInfo.getPHONENUMBER(), (userInfo.getSTART_TIME() + "-" + userInfo.getEND_TIME()), URLDecoder.decode(new ReqUrl(userInfo).getLogin_name().substring(0, 18), "GBK"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                logger.info("*************************************************************************************");
                logger.info("*************************************************************************************");
            } else if (((String) returnData.get("msg")).contains("当天最多预约1课时")) {
                flag = false;
                try {
                    logger.info("threadtask" + ThreadName + " :" + URLDecoder.decode(new ReqUrl(userInfo).getLogin_name(), "GBK") + "已有线程抢到，退出！--------" + new ReqUrl(userInfo).getPASSWORD() + " HaveOthersThreadRobed,exit");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                logger.info("*************************************************************************************");
                logger.info("*************************************************************************************");
            } else {
                DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                try {
                    if (format.parse(getOrderTime.getRunTime()).before(format.parse("12:29:58"))) {
                        Thread.sleep(2000);
                    } else {
                        Thread.sleep(1000);
                    }
                } catch (ParseException | InterruptedException e) {
                    e.printStackTrace();
                }
                flag = ((--times) != 0) && flag;
                logger.info("threadtask" + ThreadName + " : 下次开冲888");
                logger.info("*************************************************************************************");
                logger.info("*************************************************************************************");
            }

        }
    }

    public void evaluateTeacher() throws IOException {
        Calendar cal = Calendar.getInstance();
        int lastMonth = cal.get(Calendar.MONTH);
        if (lastMonth == 0) {
            lastMonth = 12;
        }
        for (int i = 0; i < 2; i++) {
            lastMonth += i;
            List commentDate = getNeedCommentDate(userInfo, lastMonth + "");
            commentTeacher(commentDate);
        }
    }

    public void commentTeacher(List commentDate) {
        if (commentDate.size() > 0) {
            commentDate.stream().forEach(date -> {
                Map<String, String> map = new HashMap<String, String>();
                StringBuilder url = new StringBuilder().append("http://es.kucarlife.com:9090/servplat/WebAPPPage/studydate/updateteachercomm.php?");
                url.append("DEPT_ID=").append(userInfo.getDEPT_ID());
                url.append("&TEACHER_ID=").append(userInfo.getTEACHER_ID());
                url.append("&STUDENT_ID=").append(userInfo.getSTUDENT_ID());
                url.append("&searchtype=2");
                url.append("&TEACHER_PINJIA=90");
                url.append("&PINJIA_INFO=Good");
                url.append("&CDATE=").append(date);
                map.put("url", url.toString());
                map.put("referer", "http://es.kucarlife.com:9090/servplat/webAppPage/studydate/adddate.php");
                map.putAll(headersMap);
                map.put("cookie", cookie);
                try {
                    Map<String, Object> returnData = robTicket.executePost(map, times);
                    if (returnData.get("msg").toString().contains("评价成功")) {
                        logger.info(date + ": 评价成功!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.toString());
                }
                logger.info(url.toString());
            });
        }
    }

    public List getNeedCommentDate(UserInfo userInfo, String checkMonth) throws IOException {
        //Map cookies = new OrderTicketServiceImpl().getCookies(userInfo.getLogin_name(), userInfo.getPASSWORD());
        Document document = Jsoup.connect("http://es.kucarlife.com:9090/servplat/webAppPage/studydate/Smart_DateStudySelect.php?YEAR=2020&MONTH=" + checkMonth + "&EXAM_TYPE=&PARENT_ID=").cookie("PHPSESSID", cookie.split("=")[1]).get();
        Elements urlInfos = document.select("a");
        List<String> list = new ArrayList<String>();
        urlInfos.stream().forEach(x -> {
            if (x.toString().contains("去评价教练")) {
                String[] date = x.attr("href").split("\\?")[1].split("&");
                final String[] year = new String[1];
                final String[] month = new String[1];
                final String[] day = new String[1];
                Arrays.stream(date).forEach(y -> {
                    String[] detail = y.split("=");
                    switch (detail[0]) {
                        case "YEAR":
                            year[0] = detail[1];
                        case "MONTH":
                            month[0] = detail[1];
                            if (Integer.parseInt(month[0]) < 10) {
                                month[0] = "0" + month[0];
                            }
                        case "DAY":
                            day[0] = detail[1];
                            if (Integer.parseInt(day[0]) < 10) {
                                day[0] = "0" + day[0];
                            }
                    }

                });
                list.add(year[0] + "-" + month[0] + "-" + day[0]);
            }
        });
        return list;
    }
}
