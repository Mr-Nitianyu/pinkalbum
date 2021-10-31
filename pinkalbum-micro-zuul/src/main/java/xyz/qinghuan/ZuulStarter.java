package xyz.qinghuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulStarter {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStarter.class,args);
    }
}
