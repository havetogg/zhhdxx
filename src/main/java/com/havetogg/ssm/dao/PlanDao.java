package com.havetogg.ssm.dao;

import com.havetogg.ssm.model.Plan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 13:53 2017/5/22
 * @Modified By:
 */
@Repository
public interface PlanDao {

    List<Plan> listPlan(Map<String,Object> param);

}
