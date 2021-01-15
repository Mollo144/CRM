package com.meng.crm.workbench.service.impl;

import com.meng.crm.utils.SqlSessionUtil;
import com.meng.crm.vo.PaginationVO;
import com.meng.crm.workbench.dao.ActivityDao;
import com.meng.crm.workbench.domain.Activity;
import com.meng.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao=SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity a) {
        boolean flag=true;
        int count=activityDao.save(a);
        if(count!=1){
            flag=false;
        }
        return flag;
    }

    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        //取得total
        int total=activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList=activityDao.getActivityListByCondition(map);
        //返回vo
        PaginationVO<Activity> vo=new PaginationVO<>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }
}
