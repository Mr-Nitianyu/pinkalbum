package xyz.qinghuan.service.impl;

import org.apache.commons.httpclient.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.qinghuan.dto.UserInfo;

import xyz.qinghuan.mobilephone.ChangeTemplet;
import xyz.qinghuan.service.RobService;
import xyz.qinghuan.service.RobTicket;
import xyz.qinghuan.thread.ExecuteRob;
import xyz.qinghuan.utils.ReqUrl;
import xyz.qinghuan.utils.RequestHeaderContent;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("robService")
public class RobServiceImpl implements RobService {
    @Autowired
    @Qualifier("robTicket")
    private RobTicket robTicket;
    @Autowired
    private ChangeTemplet changeTemplet;

    private final Logger logger = LoggerFactory.getLogger(RobServiceImpl.class);

    /**
     * 直接预约课程
     *
     * @throws Exception
     */
    @Override
    public void robTicket(UserInfo userInfo) throws Exception {
        String[] urls = new ReqUrl(userInfo).getUrls();
        int tempCode = 100;
        Map cookieMap = null;
        while(tempCode == 100){
            cookieMap = robTicket.acceptCookie(new ReqUrl(userInfo).getREDIRECT_URL());
            tempCode = (int) cookieMap.get("statusCode");
        }
        int statusCode = (int) cookieMap.get("statusCode");
        Map headersMap = new RequestHeaderContent().getHeaders();
        String cookie = (String) cookieMap.get("cookie");
        if (statusCode == 200) {//重定向到新的URL
            if (statusCode == 200) {
                List<Map<String, String>> list = userInfo.getOwnOrderTime().getOrderTime();
                String temple = changeTemplet.getTemplet();
                for (Map<String, String> map : list) {
                    UserInfo userInfo1 = new UserInfo();
                    userInfo1.setLogin_name(userInfo.getLogin_name());
                    userInfo1.setPASSWORD(userInfo.getPASSWORD());
                    userInfo1.setDEPT_ID(userInfo.getDEPT_ID());
                    userInfo1.setSTUDENT_ID(userInfo.getSTUDENT_ID());
                    userInfo1.setTEACHER_ID(userInfo.getTEACHER_ID());
                    userInfo1.setKEMUNO(userInfo.getKEMUNO());
                    userInfo1.setPHONENUMBER(userInfo.getPHONENUMBER());
                    userInfo1.setTYPE(userInfo.getTYPE());
                    userInfo1.setSTART_TIME(map.get("startTime"));
                    userInfo1.setEND_TIME(map.get("endTime"));
                    for (int i = 0; i < 2; i++) {
                        new Thread(new ExecuteRob(true, 40, headersMap, userInfo1, cookie, (10 + i), robTicket,temple)).start();
                    }

                }
            }
        } else {
            System.out.println("登录失败");
        }
    }
}
