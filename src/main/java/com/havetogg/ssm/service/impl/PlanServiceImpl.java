package com.havetogg.ssm.service.impl;

import com.havetogg.ssm.dao.PlanDao;
import com.havetogg.ssm.model.Plan;
import com.havetogg.ssm.service.PlanService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:56 2017/5/23
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanServiceImpl implements PlanService{

    @Resource
    private PlanDao planDao;

    @Override
    public List<Plan> listPlan(Map<String, Object> param) {
        return planDao.listPlan(param);
    }
}
