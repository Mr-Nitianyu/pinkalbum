package xyz.qinghuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("xyz.qinghuan.mapper")
@EnableEurekaClient
public class OrderticketStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderticketStarter.class,args);

    }
    @Bean
    @LoadBalanced
    public RestTemplate initRestTemplete(){
        return new RestTemplate();
    }
}
