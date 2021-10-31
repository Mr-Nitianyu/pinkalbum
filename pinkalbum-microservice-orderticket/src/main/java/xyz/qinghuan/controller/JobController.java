package xyz.qinghuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.qinghuan.dto.AppQuartz;
import xyz.qinghuan.jobmanage.JobManage;
import xyz.qinghuan.service.AppQuartzService;
import xyz.qinghuan.vo.SysResult;

@RestController
@RequestMapping("/job/manage")
public class JobController {

    @Autowired
    private AppQuartzService appQuartzService;

    //添加一个job
    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public SysResult addjob(@RequestBody AppQuartz appQuartz) throws Exception {
        SysResult result = new SysResult();
        appQuartzService.insertAppQuartzSer(appQuartz);
        result.setStatus(200);
        return result;
    }
    //删除job
    @RequestMapping(value = "/deletJob", method = RequestMethod.POST)
    public SysResult deletjob(@RequestBody AppQuartz appQuartz) throws Exception {
             int stat = appQuartzService.deleteJob(appQuartz);
             return new SysResult(200, "success deleteJob","");
    }

   /* //暂停job
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    public ReturnMsg pausejob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if (quartzIds.length > 0) {
            for (Integer quartzId : quartzIds) {
                appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobManage.pauseJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return new ReturnMsg("200", "success pauseJob");
        } else {
            return new ReturnMsg("404", "fail pauseJob");
        }
    }

    //恢复job
    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
    public ReturnMsg resumejob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if (quartzIds.length > 0) {
            for (Integer quartzId : quartzIds) {
                appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobManage.resumeJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return new ReturnMsg("200", "success resumeJob");
        } else {
            return new ReturnMsg("404", "fail resumeJob");
        }
    }


    //删除job
    @RequestMapping(value = "/deletJob", method = RequestMethod.POST)
    public ReturnMsg deletjob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        for (Integer quartzId : quartzIds) {
            appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
            String ret = jobManage.deleteJob(appQuartz);
            if ("success".equals(ret)) {
                appQuartzService.deleteAppQuartzByIdSer(quartzId);
            }
        }
        return new ReturnMsg("200", "success deleteJob");
    }

    //修改
    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public ReturnMsg modifyJob(@RequestBody AppQuartz appQuartz) throws Exception {
        String ret = jobManage.modifyJob(appQuartz);
        if ("success".equals(ret)) {
            appQuartzService.updateAppQuartzSer(appQuartz);
            return new ReturnMsg("200", "success updateJob", ret);
        } else {
            return new ReturnMsg("404", "fail updateJob", ret);
        }
    }

    //暂停所有
    @RequestMapping(value = "/pauseAll", method = RequestMethod.GET)
    public ReturnMsg pauseAllJob() throws Exception {
        jobManage.pauseAllJob();
        return new ReturnMsg("200", "success pauseAll");
    }

    //恢复所有
    @RequestMapping(value = "/repauseAll", method = RequestMethod.GET)
    public ReturnMsg repauseAllJob() throws Exception {
        jobManage.resumeAllJob();
        return new ReturnMsg("200", "success repauseAll");
    }*/
}
