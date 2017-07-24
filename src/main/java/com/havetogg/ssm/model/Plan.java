package com.havetogg.ssm.model;

import com.havetogg.ssm.model.base.BaseEntity;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:47 2017/5/22
 * @Modified By:
 */
public class Plan extends BaseEntity{

    //用户名
    private String userName;
    //计划时间
    private String planTime;
    //企业名称
    private String enterpriseName;
    //企业规模
    private String enterpriseScale;
    //客户数量
    private String customerNum;

    private String isVisited;

    private String needNum;

    private String goal;

    private String other;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(String isVisited) {
        this.isVisited = isVisited;
    }

    public String getNeedNum() {
        return needNum;
    }

    public void setNeedNum(String needNum) {
        this.needNum = needNum;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
