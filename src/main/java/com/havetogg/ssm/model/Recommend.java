package com.havetogg.ssm.model;

import com.havetogg.ssm.model.base.BaseEntity;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:31 2017/5/22
 * @Modified By:
 */
public class Recommend extends BaseEntity{
    private String recomCode;

    private String count;

    private String createTime;

    public String getRecomCode() {
        return recomCode;
    }

    public void setRecomCode(String recomCode) {
        this.recomCode = recomCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
