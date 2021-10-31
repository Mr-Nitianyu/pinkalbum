package xyz.qinghuan.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import xyz.qinghuan.dto.FetchInfo;
import xyz.qinghuan.dto.OrderInfo;
import xyz.qinghuan.mapper.OrderTicketMapper;
import xyz.qinghuan.service.OrderTicketService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderTicketService")
public class OrderTicketServiceImpl implements OrderTicketService {
    private final Logger logger = LoggerFactory.getLogger(OrderTicketServiceImpl.class);
    @Autowired
    private OrderTicketMapper orderTicketMapper;
    @Autowired
    private ShardedJedisPool pool;

    @Override
    public int fetchinfo(String account, String password ,String teacherName ,String phoneNum) throws IOException {
        int stat = checkPhoneNum(phoneNum);//校验手机号是否为注册手机号，需要注册信息和学员驾校信息通过手机号进行关联
        if(stat == 0){
            return 0;
        }
        Map cookies = getCookies(account, password);
        Document documentFirst = Jsoup.connect("http://es.kucarlife.com:9090/servplat/webAppPage/studydate/Smart_DateStudy.php?YEAR=2020&MONTH=07&DAY=27").cookies(cookies).get();
        int courseNum = getKEMUNO(documentFirst);
        Document document = Jsoup.connect("http://es.kucarlife.com:9090/servplat/webAppPage/studydate/Smart_DateStudy.php?YEAR=2020&MONTH=07&DAY=27&searchtype="+courseNum).cookies(cookies).get();
        Elements  urlInfos = document.select(".smalltable");
        FetchInfo fetchInfo = getUrlInfo(urlInfos,teacherName,courseNum);//获取学员的驾校信息
        if(fetchInfo.getKENUM() == 404){
            return 3;
        }
        if("".equals(fetchInfo.getTEACHER_ID()) || null == fetchInfo.getTEACHER_ID()){
            return 2;
        }
        fetchInfo.setLOGIN_NAME(URLEncoder.encode(account,"GBK"));
        fetchInfo.setLOGIN_PASSWORD(password);
        fetchInfo.setLOGIN_TPYE("STUDENT");
        fetchInfo.setKENUM(courseNum);
        fetchInfo.setPHONE_NUM(phoneNum);
        logger.info("学员补录信息为："+fetchInfo.toString());
        int statName = orderTicketMapper.checkAccount(URLEncoder.encode(account,"GBK"));
        if(statName == 1){
            return orderTicketMapper.updateFetchInfo(fetchInfo);
        }
        return orderTicketMapper.insertFetchInfo(fetchInfo);
    }

    private int checkPhoneNum(String phoneNum) {
        return orderTicketMapper.checkPhoneNum(phoneNum);
    }

    public Map<String,String> getCookies(String account, String password) throws UnsupportedEncodingException {
        String redirectUrl = "http://es.kucarlife.com:9090/servplat_HH/WebAPPPage/sign.php?login_name="+URLEncoder.encode(account,"GBK")+"&PASSWORD="+password+"&TYPE=STUDENT&y_url=";
        // 访问平台信息
        String User_Agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";
        HttpClient httpClient = new HttpClient();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(redirectUrl);
        postMethod.setRequestHeader("Referer", "http://www.kucarlife.com/WebAPP/login_zjjs.php");
        postMethod.setRequestHeader("User-Agent", User_Agent);
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获得登陆后的 Cookie
        Cookie[] cookies = httpClient.getState().getCookies();
        StringBuffer tmpcookies = new StringBuffer();
        HashMap<String, String> mapCookie = new HashMap<>();
        for (Cookie c : cookies) {
            tmpcookies.append(c.toString()).append(";");
            mapCookie.put(c.getName(),c.getValue());
            System.out.println("cookies = "+c.toString());
        }
        return mapCookie;
    }
    public int getKEMUNO(Document document){
        Elements links = document.select("a[href]");
        Elements  courseinfos = document.select("#search-option");

        final Integer[] KEMUNO = {0};
        courseinfos.forEach(sel ->{
            Elements optins = sel.getElementsByTag("option");
            optins.forEach(opt ->{
                if(Integer.parseInt(opt.attr("value")) > KEMUNO[0]){
                    KEMUNO[0] = Integer.parseInt(opt.attr("value"));
                }
            });
        });
        links.forEach(x-> System.out.println(x.toString()));
        courseinfos.forEach(x-> System.out.println(x.toString()));
        return KEMUNO[0];
    }
    public int getKEMUNO(Elements courseinfos){
        final Integer[] KEMUNO = {0};
        courseinfos.forEach(sel ->{
            Elements optins = sel.getElementsByTag("option");
            optins.forEach(opt ->{
                if(Integer.parseInt(opt.attr("value")) > KEMUNO[0]){
                    KEMUNO[0] = Integer.parseInt(opt.attr("value"));
                }
            });
        });
        return KEMUNO[0];
    }
    public FetchInfo getUrlInfo(Elements urlInfos,String teacherNamet,int courseNum){
        FetchInfo fetchInfo = new FetchInfo();
        urlInfos.forEach(sel ->{
            Element td = sel.select("[align=left]").first();
            String url = td.attr("onclick");
            if(!url.contains("location")){
                fetchInfo.setKENUM(404);
                return;
            }
            String teacherName = td.text().split(" ")[0];
            logger.info(url);
            logger.info(teacherName);
            if(teacherNamet.equals(teacherName)){
                fetchInfo.setTEACHER_NAME(teacherName);
                fetchInfo.setDEPT_ID("3");
                fetchInfo.setKENUM(courseNum);
                fetchInfo.setSTUDENT_ID(url.split("=")[3].substring(0,6));
                fetchInfo.setTEACHER_ID(url.split("=")[4].substring(0,6));
                try {
                    fetchInfo.setVM_NUM(URLEncoder.encode(url.split("=")[7].substring(0,7),"GBK"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });
        return fetchInfo;
    }


    @Override
    public int addOrderTime(String orderTimes,String cookieStat,String orderDate) {
        ShardedJedis jedis = pool.getResource();
        //首先判断超时剩余时间
        Long leftTime = jedis.pttl(cookieStat);
        //少于10分钟,延长5分钟
        if(leftTime<1000*60*10L){
            jedis.pexpire(cookieStat,leftTime+1000*60*5);
        }
        OrderInfo orderInfo =  JSONObject.parseObject(jedis.get(cookieStat),OrderInfo.class);
        String userPhone = orderInfo.getRegistPhoneNumber();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        if(!"".equals(userPhone) && null != userPhone){
            final List<String> orderTime = JSONObject.parseArray(orderTimes,String.class);
            orderTime.forEach(x -> {
                try {
                    String endTime = simpleDateFormat.format(new Date(simpleDateFormat.parse(x).getTime()+2700000));
                    orderInfo.setStartTime(x);
                    orderInfo.setEndTime(endTime);
                    orderInfo.setOrderDate(orderDate);
                    orderTicketMapper.addOrderTime(orderInfo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            return 000;
        }
        return 111;
    }

    @Override
    public List queryOrderTime(String cookieStat, String orderDate) {
        ShardedJedis jedis = pool.getResource();
        OrderInfo orderInfo =  JSONObject.parseObject(jedis.get(cookieStat),OrderInfo.class);
        orderInfo.setOrderDate(orderDate);
        return orderTicketMapper.queryOrderTime(orderInfo);
    }

    @Override
    public int deleteOrderTime(String orderTimes, String cookieStat, String orderDate) {
        ShardedJedis jedis = pool.getResource();
        //首先判断超时剩余时间
        Long leftTime = jedis.pttl(cookieStat);
        //少于10分钟,延长5分钟
        if(leftTime<1000*60*10L){
            jedis.pexpire(cookieStat,leftTime+1000*60*5);
        }
        OrderInfo orderInfo =  JSONObject.parseObject(jedis.get(cookieStat),OrderInfo.class);
        String userPhone = orderInfo.getRegistPhoneNumber();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        if(!"".equals(userPhone) && null != userPhone){
            final List<String> orderTime = JSONObject.parseArray(orderTimes,String.class);
            orderTime.forEach(x -> {
                orderInfo.setStartTime(x);
                orderInfo.setOrderDate(orderDate);
                orderTicketMapper.deleteOrderTime(orderInfo);
            });
            return 000;
        }
        return 111;
    }

    @Override
    public List queryfetchinfo(String cookieStat) {
        ShardedJedis jedis = pool.getResource();
        //首先判断超时剩余时间
        Long leftTime = jedis.pttl(cookieStat);
        //少于10分钟,延长5分钟
        if(leftTime<1000*60*10L){
            jedis.pexpire(cookieStat,leftTime+1000*60*5);
        }
        OrderInfo orderInfo =  JSONObject.parseObject(jedis.get(cookieStat),OrderInfo.class);
        String userPhone = orderInfo.getRegistPhoneNumber();
        List<Map<String,String>> list = orderTicketMapper.queryfetchinfo(userPhone);
        if(list.size()>0){
            list.stream().forEach(x -> {
                try {
                    x.put("login_name", URLDecoder.decode(x.get("login_name"),"GBK"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }
}
