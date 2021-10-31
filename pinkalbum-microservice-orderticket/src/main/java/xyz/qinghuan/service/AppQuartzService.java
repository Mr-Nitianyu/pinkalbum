package xyz.qinghuan.service;

import xyz.qinghuan.dto.AppQuartz;

public interface AppQuartzService {
    void insertAppQuartzSer(AppQuartz appQuartz);

    int deleteJob(AppQuartz appQuartz);
}
