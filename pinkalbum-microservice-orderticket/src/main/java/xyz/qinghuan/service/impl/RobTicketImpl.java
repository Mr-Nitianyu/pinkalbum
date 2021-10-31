package xyz.qinghuan.service.impl;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.qinghuan.service.RobTicket;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service("robTicket")
public class RobTicketImpl implements RobTicket {
    private final Logger logger = LoggerFactory.getLogger(xyz.qinghuan.service.impl.RobTicketImpl.class);
    /**
     * 递归进入页面
     * @param start 开始时间
     * @param end 结束时间
     * @param cookie cookie
     * @param urls 请求连接
     * @param httpClient 请求客户端
     * @param requestHeaderCont 请求头内容
     * @return int
     * @throws Exception 请求异常
     */
    @Override
    public int jumpPages(int start, int end, String cookie, String[] urls, HttpClient httpClient, Map<String,String> requestHeaderCont) throws Exception {
        Map<String,String> jumpMap = new HashMap<>();
        jumpMap.put("url", urls[start]);
        jumpMap.put("referer", urls[start - 1]);
        jumpMap.put("cookie", cookie);
        jumpMap.putAll(requestHeaderCont);
        int status = executeGet(jumpMap);
        if (status == 200 && start <= end) {
            jumpPages(++start, end, cookie, urls, httpClient, requestHeaderCont);
        }
        return status;
    }

    /**
     * get请求预约课程
     * @param map 请求参数
     * @return 请求结果状态码
     * @throws IOException 异常
     */
    @Override
    public int executeGet(Map<String, String> map) throws IOException {
        String url = map.get("url");
        String cookie = map.get("cookie");
        String referer = map.get("referer");
        String user_Agent = map.get("USER_AGENT");
        String host = map.get("HOST");

        // 进行登陆后的操作
        GetMethod getMethod = new GetMethod(url);
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
        getMethod.setRequestHeader("cookie", cookie);
        // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
        // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
        getMethod.setRequestHeader("Referer", referer);
        getMethod.setRequestHeader("User-Agent", user_Agent);
        getMethod.setRequestHeader("Host", host);
        HttpClient client = new HttpClient();
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        return client.executeMethod(getMethod);
    }

    /**
     * post请求预约课程
     * @param map 请求参数
     * @param times 次数
     * @return 模拟预约课程
     */
    @Override
    public Map<String,Object> executePost(Map<String, String> map, int times)  {
        String url = map.get("url");
        String cookie = map.get("cookie");
        String referer = map.get("referer");
        String user_Agent = map.get("USER_AGENT");
        String host = map.get("HOST");
        // 进行登陆后的操作
        PostMethod postMethod = new PostMethod(url);
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
        postMethod.setRequestHeader("cookie", cookie);
        // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
        // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
        postMethod.setRequestHeader("Referer", referer);
        postMethod.setRequestHeader("User-Agent", user_Agent);
        postMethod.setRequestHeader("Host", host);
        HttpClient client = new HttpClient();
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        int statusCode = 0;
        try {
            statusCode = client.executeMethod(postMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = null;
        try {
            text = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> returnData = new HashMap<>();
        returnData.put("status", statusCode);
        returnData.put("msg", text);
        return returnData;
    }

    /**
     * 获取cookie
     * @param url 请求链接
     * @return map
     */
    @Override
    public Map<String,Object> acceptCookie(String url) {
        HttpClient httpClient = new HttpClient();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(url);
        StringBuilder tmpcookies = new StringBuilder();
        int statusCode = 0;
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            statusCode = httpClient.executeMethod(postMethod);
            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString()).append(";");
                System.out.println("cookie = " + c.toString());
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
        Map<String,Object> returnData = new HashMap<>();
        returnData.put("statusCode", statusCode);
        returnData.put("cookie", tmpcookies.toString());
        returnData.put("httpClient", httpClient);
        return returnData;
    }


}