package xyz.qinghuan.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix="spring.redis.shardedpool")
public class ShardeJedisPoolConfig {
	//configurationProperties注解,可以根据值
	//利用", ; "等隔开,分别add到同名的属性中
	private List<String> nodes;//["10.9.100.26:6379","",""]
	private Integer maxTotal;
	private Integer maxIdle;
	private Integer minIdle;
	private String password;
	@Bean
	public ShardedJedisPool sjPoolInit(){
		//利用属性完成连接池的初始化任务
		//第一步 收集节点信息
		List<JedisShardInfo> infoList=new ArrayList<>();
		for (String node : nodes) {
			String ip=node.split(":")[0];
			int port=Integer.parseInt(node.split(":")[1]);
			JedisShardInfo jedisShardInfo = new JedisShardInfo(ip,port);
			jedisShardInfo.setPassword(password);
			infoList.add(jedisShardInfo);
		}
		//封装一个config,设置最大连接数最大空闲
		GenericObjectPoolConfig config=
				new GenericObjectPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		return new ShardedJedisPool(config,infoList);
	}
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
