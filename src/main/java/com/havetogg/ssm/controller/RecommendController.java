package com.havetogg.ssm.controller;

import com.havetogg.ssm.model.Plan;
import com.havetogg.ssm.model.Recommend;
import com.havetogg.ssm.result.PageResult;
import com.havetogg.ssm.service.RecommendService;
import com.havetogg.ssm.utils.ExcelUtil;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 9:32 2017/5/23
 * @Modified By:
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

    private Logger log = Logger.getLogger(RecommendController.class);

    //private ThreadLocal<List<Recommend>> recommendContentTL = new ThreadLocal<List<Recommend>>();

    @Autowired
    private RecommendService recommendService;


    @RequestMapping(value = "/recommend")
    public String recommend(){
        log.info("进入recommend");
        return "recommend";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Recommend> listRecommend(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,HttpSession session){
        if(startDate==null&&endDate==null){
            Date date = new Date();//获取当前时间
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -7);//当前时间减去7天
            calendar.getTime();//获取7天前的时间
            startDate = format.format(calendar.getTime());
            Date currentTime = new Date();
            endDate = format.format(currentTime);
        }
        Map<String,Object> param = new HashMap<>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        List<Recommend> recommendList = recommendService.listRecommend(param);
        /*this.recommendContentTL.remove();
        this.recommendContentTL.set(recommendList);*/
        session.setAttribute("recommendList",recommendList);
        return recommendList;
    }

    @RequestMapping(value = "/showPage")
    @ResponseBody
    public PageResult<Recommend> PageRecommend(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){
        log.info("查询分页信息");
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        if(startDate==null&&endDate==null){
            Date date = new Date();//获取当前时间
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -7);//当前时间减去7天
            calendar.getTime();//获取7天前的时间
            startDate = format.format(calendar.getTime());
            Date currentTime = new Date();
            endDate = format.format(currentTime);
        }
        Map<String,Object> param = new HashMap<>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        PageResult<Recommend> pageResult = recommendService.getPageRecommend(pageNum,pageSize,param);
        return pageResult;
    }

    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response,HttpSession session) throws Exception{
        String filename = "微信达人推荐.xls";//导出excel名
        OutputStream os = response.getOutputStream();
        Object planListObj = session.getAttribute("recommendList");
        if(planListObj == null){
            throw new Exception("无法获取列表信息");
        }
        log.info("获取表格大小为"+((List<Plan>)session.getAttribute("recommendList")).size());
        WritableWorkbook wwb = ExcelUtil.exportExcel(os,(List<Plan>)session.getAttribute("recommendList"));
        filename= URLEncoder.encode(filename,"UTF-8");
        response.setContentType("application/msexcel");
        response.setHeader( "Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO8859-1"));
        session.removeAttribute("recommendList");
        wwb.write();//写入excel对象
        wwb.close();//关闭可写入的Excel对象
        os.close();
    }

}
