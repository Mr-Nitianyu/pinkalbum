package xyz.qinghuan.mobilephone;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.qinghuan.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;


public class SendSms {
	private final String host;
	private final String path;
	private final String post;
	private final String appcode;
	private final String formwork;
	Logger logger = LoggerFactory.getLogger(SendSms.class);

	public SendSms(String host, String path, String post, String appcode, String formwork) {
		this.host = host;
		this.path = path;
		this.post = post;
		this.appcode = appcode;
		this.formwork = formwork;
	}

	public String sendMessage(String phoneNumble, String time,String name) {
/*		String host = this.host;
		String path = this.path;
		String method = this.post;
		String appcode = this.appcode;
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phoneNumble);
		querys.put("param", "name:"+name+",time:"+time);
		querys.put("tpl_id", this.formwork);
		Map<String, String> bodys = new HashMap<String, String>();

		try {
			*//**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 *//*
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			// 获取response的body
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	    String host = this.host;
		String path = this.path;
		String method = this.post;
		String appcode = this.appcode;
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phoneNumble);
		querys.put("content", "【秋名山老司机】"+name+"选手您好，已成功帮您预约到"+time+"的挑战赛，祝您车技更上一层楼！加油！嘀嘀嘀！");
		//querys.put("tpl_id", this.formwork);
		Map<String, String> bodys = new HashMap<String, String>();

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			// 获取response的body
			logger.info(EntityUtils.toString(response.getEntity()));
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

/*	public static void main(String[] args) {
		String host = "https://zwp.market.alicloudapi.com";
		String path = "/sms/sendv2";
		String method = "GET";
		String appcode = "ec929179690b454cbb2a71154e6f81f4";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", "17637689837");
		querys.put("content", "【秋名山老司机】"+"LIUJINJUAN"+"选手您好，已成功帮您预约到"+"ZAOSHANG"+"的挑战赛，祝您车技更上一层楼！加油！嘀嘀嘀！");
		//querys.put("tpl_id", this.formwork);
		Map<String, String> bodys = new HashMap<String, String>();

		try {

			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			// 获取response的body
			System.out.println( EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
