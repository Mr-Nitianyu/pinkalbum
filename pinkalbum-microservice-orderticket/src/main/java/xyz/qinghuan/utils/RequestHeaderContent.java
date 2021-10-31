package xyz.qinghuan.utils;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaderContent {
    String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9";
    String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";
    String ACCEPT_ENCODING = "gzip, deflate, br";
    String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9";
    String HOST = "es.kucarlife.com:9090";

    public Map getHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ACCEPT", ACCEPT);
        map.put("USER_AGENT", USER_AGENT);
        map.put("ACCEPT_ENCODING", ACCEPT_ENCODING);
        map.put("ACCEPT_LANGUAGE", ACCEPT_LANGUAGE);
        map.put("HOST", HOST);
        return map;
    }
}
