package com.meng.crm.workbench.service;

import com.meng.crm.vo.PaginationVO;
import com.meng.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
