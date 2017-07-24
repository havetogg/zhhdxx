package com.havetogg.ssm.service;

import com.havetogg.ssm.model.Recommend;
import com.havetogg.ssm.result.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 9:34 2017/5/23
 * @Modified By:
 */
public interface RecommendService {

    List<Recommend> listRecommend(Map<String,Object> param);


    PageResult<Recommend> getPageRecommend(int pageNum, int pageSize, Map<String,Object> param);
}
