package com.havetogg.ssm.controller;

import com.havetogg.ssm.model.Plan;
import com.havetogg.ssm.model.Recommend;
import com.havetogg.ssm.service.PlanService;
import com.havetogg.ssm.utils.ExcelUtil;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:52 2017/5/23
 * @Modified By:
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

    private Logger log = Logger.getLogger(PlanController.class);

    //private ThreadLocal<List<Plan>> planContentTL = new ThreadLocal<List<Plan>>();

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/plan")
    public String plan(){
        log.info("进入plan");
        return "plan";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Plan> listPlan(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, @RequestParam(required = false) String year, HttpSession session){
        if(startDate==null&&endDate==null){
            Date date = new Date();//获取当前时间
            DateFormat format = new SimpleDateFormat("MM/dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -7);//当前时间减去7天
            calendar.getTime();//获取7天前的时间
            startDate = format.format(calendar.getTime());
            Date currentTime = new Date();
            endDate = format.format(currentTime);
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }
        Map<String,Object> param = new HashMap<>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        param.put("year",year);
        List<Plan> planList = planService.listPlan(param);
        //this.planContentTL.remove();
        log.info("设置表格大小为"+planList.size());
        session.setAttribute("planList",planList);
        //this.planContentTL.set(planList);
        return planList;
    }

    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response,HttpSession session) throws Exception{
        String filename = "周计划.xls";//导出excel名
        OutputStream os = response.getOutputStream();
        Object planListObj = session.getAttribute("planList");
        if(planListObj == null){
            throw new Exception("无法获取列表信息");
        }
        log.info("获取表格大小为"+((List<Plan>)session.getAttribute("planList")).size());
        WritableWorkbook wwb = ExcelUtil.exportExcel(os,(List<Plan>)session.getAttribute("planList"));
        filename= URLEncoder.encode(filename,"UTF-8");
        response.setContentType("application/msexcel");
        response.setHeader( "Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO8859-1"));
        session.removeAttribute("planList");
        wwb.write();//写入excel对象
        wwb.close();//关闭可写入的Excel对象
        os.close();
    }
}
