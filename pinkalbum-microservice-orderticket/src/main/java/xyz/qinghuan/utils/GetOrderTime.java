package xyz.qinghuan.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class GetOrderTime {

    public String getTime(){
        Format format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date(new Date().getTime()+24*3600*1000*2);
        return format.format(date);
    }
    public String getYear(){
        Format format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date date = new Date(new Date().getTime());
        return format.format(date);
    }
    public String getMonth(){
        Format format = new SimpleDateFormat("MM", Locale.ENGLISH);
        Date date = new Date(new Date().getTime());
        return format.format(date);
    }
    public String getDay(){
        Format format = new SimpleDateFormat("dd", Locale.ENGLISH);
        Date date = new Date(new Date().getTime()+24*3600*1000*2);
        return format.format(date);
    }
    public String getRunTime(){
        Format format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date date = new Date(new Date().getTime());
        return format.format(date);
    }
}
