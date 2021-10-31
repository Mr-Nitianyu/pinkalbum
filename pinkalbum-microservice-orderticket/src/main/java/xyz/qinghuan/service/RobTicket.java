package xyz.qinghuan.service;

import org.apache.commons.httpclient.HttpClient;

import java.io.IOException;
import java.util.Map;

public interface RobTicket {
     int jumpPages(int start, int end, String cookie, String[] urls, HttpClient httpClient, Map<String,String> requestHeaderCont) throws Exception;
     int executeGet(Map<String, String> map) throws IOException;
     Map<String,Object> executePost(Map<String, String> map, int times) throws IOException;
     Map<String,Object> acceptCookie(String url);
}
