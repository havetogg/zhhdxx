package com.havetogg.ssm.dao;

import com.havetogg.ssm.model.Recommend;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:52 2017/5/22
 * @Modified By:
 */
@Repository
public interface RecommendDao {
    List<Recommend> listRecommend(Map<String,Object> param);

    List<Recommend> getPageRecommend(Map<String,Object> param);
}
