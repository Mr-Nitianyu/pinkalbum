package xyz.qinghuan.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xyz.qinghuan.dto.OwnOrderTime;
import xyz.qinghuan.dto.UserInfo;
import xyz.qinghuan.mapper.OrderTicketMapper;
import xyz.qinghuan.service.RobService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class OrderTicketJob implements Job {
    @Autowired
    private RobService robService;
    @Autowired
    private OrderTicketMapper orderTicketMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        //JobDataMap data = jobExecutionContext.getTrigger().getJobDataMap();
        //String invokeParam =(String) data.get("invokeParam");
        //在这里实现业务逻辑
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(System.currentTimeMillis()+172800000);
        List<Map<String,String>> orderUser = orderTicketMapper.queryOrderUser(date);
        if(orderUser.size()>0){
            for(Map<String,String> map:orderUser){
                List<Map<String,String>> orderTime = orderTicketMapper.queryOwnOrderTime(map.get("phone_num"),date);
                UserInfo userInfo = new UserInfo();
                userInfo.setLogin_name(map.get("login_name"));
                userInfo.setPASSWORD(map.get("login_password"));
                userInfo.setTYPE(map.get("login_type"));
                userInfo.setDEPT_ID(map.get("dept_id"));
                userInfo.setSTUDENT_ID(map.get("student_id"));
                userInfo.setTEACHER_ID(map.get("teacher_id"));
                userInfo.setKEMUNO(map.get("kemuno"));
                userInfo.setPHONENUMBER(map.get("phone_num"));
                userInfo.setOwnOrderTime(new OwnOrderTime(orderTime));
                try {
                   robService.robTicket(userInfo);
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        }
    }
}
