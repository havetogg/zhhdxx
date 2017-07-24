package com.havetogg.ssm.service.impl;

import com.havetogg.ssm.dao.RecommendDao;
import com.havetogg.ssm.model.Recommend;
import com.havetogg.ssm.model.User;
import com.havetogg.ssm.result.PageResult;
import com.havetogg.ssm.service.RecommendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 9:37 2017/5/23
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecommendServiceImpl implements RecommendService{

    @Resource
    private RecommendDao recommendDao;

    @Override
    public List<Recommend> listRecommend(Map<String,Object> param) {
        List<Recommend> recommendList = recommendDao.listRecommend(param);
        return recommendList;
    }


    @Override
    public PageResult<Recommend> getPageRecommend(int pageNum, int pageSize, Map<String, Object> param) {
        if(pageSize>1000){
            return null;
        }
        PageResult<Recommend> pageResult = new PageResult<>();
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        List<Recommend> recommendList = recommendDao.getPageRecommend(param);
        pageResult.setDatas(recommendList);
        //?count问题
        /*int count = recommendList.size();
        pageResult.setTotalCount(count);*/
        return pageResult;
    }


}
