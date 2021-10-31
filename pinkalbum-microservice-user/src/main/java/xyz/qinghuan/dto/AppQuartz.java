package xyz.qinghuan.dto;

public class AppQuartz {
    private Integer quartzId;  //id  主键
    private String jobName;  //任务名称
    private String jobGroup;  //任务分组
    private String startTime;  //任务开始时间
    private String cronExpression;  //corn表达式
    private String invokeParam;//需要传递的参数

    public AppQuartz() {
    }

    public AppQuartz(Integer quartzId, String jobName, String jobGroup, String startTime, String cronExpression, String invokeParam) {
        this.quartzId = quartzId;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.startTime = startTime;
        this.cronExpression = cronExpression;
        this.invokeParam = invokeParam;
    }

    public Integer getQuartzId() {
        return quartzId;
    }

    public void setQuartzId(Integer quartzId) {
        this.quartzId = quartzId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getInvokeParam() {
        return invokeParam;
    }

    public void setInvokeParam(String invokeParam) {
        this.invokeParam = invokeParam;
    }

    @Override
    public String toString() {
        return "AppQuartz{" +
                "quartzId=" + quartzId +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", startTime='" + startTime + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", invokeParam='" + invokeParam + '\'' +
                '}';
    }
}
