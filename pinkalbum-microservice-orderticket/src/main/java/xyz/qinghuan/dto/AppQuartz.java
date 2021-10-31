package xyz.qinghuan.dto;

public class AppQuartz {
    private String startTime;
    private String cronExpression;
    private String jobGroup;
    private String jobName;
    private String invokeParam;
    private String jobClassName;

    public AppQuartz() {
    }

    public AppQuartz(String startTime, String cronExpression, String jobGroup, String jobName, String invokeParam, String jobClassName) {
        this.startTime = startTime;
        this.cronExpression = cronExpression;
        this.jobGroup = jobGroup;
        this.jobName = jobName;
        this.invokeParam = invokeParam;
        this.jobClassName = jobClassName;
    }

    public AppQuartz(String startTime, String cronExpression, String jobGroup, String jobName, String invokeParam) {
        this.startTime = startTime;
        this.cronExpression = cronExpression;
        this.jobGroup = jobGroup;
        this.jobName = jobName;
        this.invokeParam = invokeParam;
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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getInvokeParam() {
        return invokeParam;
    }

    public void setInvokeParam(String invokeParam) {
        this.invokeParam = invokeParam;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    @Override
    public String toString() {
        return "AppQuartz{" +
                "startTime='" + startTime + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobName='" + jobName + '\'' +
                ", invokeParam='" + invokeParam + '\'' +
                ", jobClassName='" + jobClassName + '\'' +
                '}';
    }
}
