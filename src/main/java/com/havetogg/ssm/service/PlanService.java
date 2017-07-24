package com.havetogg.ssm.service;

import com.havetogg.ssm.model.Plan;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:55 2017/5/23
 * @Modified By:
 */
public interface PlanService {
    List<Plan> listPlan(Map<String,Object> param);
}
