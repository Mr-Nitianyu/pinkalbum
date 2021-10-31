package xyz.qinghuan.service.impl;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qinghuan.dto.AppQuartz;
import xyz.qinghuan.jobmanage.JobManage;
import xyz.qinghuan.service.AppQuartzService;
@Service
public class AppQuartzServiceImpl implements AppQuartzService {
    @Autowired
    private JobManage jobManage;
    @Override
    public void insertAppQuartzSer(AppQuartz appQuartz) {
        try {
            jobManage.addJob(appQuartz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int deleteJob(AppQuartz appQuartz) {
        String result = null;
        try {
            result = jobManage.deleteJob(appQuartz);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if("success".equals(result)){
            return 000;
        }else {
            return 101;
        }
    }
}
