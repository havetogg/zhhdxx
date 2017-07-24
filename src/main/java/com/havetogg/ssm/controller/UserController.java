package com.havetogg.ssm.controller;

import com.havetogg.ssm.model.User;
import com.havetogg.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = {"/user",""})
@SessionAttributes({"user","errorTimes"})
public class UserController {
    @ModelAttribute("errorTimes")
    public int getError(){
        return 0;
    }

    private Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //进入登陆页面
    @RequestMapping("")
    public String Default(Model model){
        if(model.containsAttribute("user")){
            return "forward:/recommend/recommend";
        }
        return "login";
    }

    //进入登陆页面
    @RequestMapping("/index")
    public String Index(Model model){
        if(model.containsAttribute("user")){
            return "forward:/recommend/recommend";
        }
        return "login";
    }

    //登陆
    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String name,@RequestParam(required = false) String pwd,@ModelAttribute("errorTimes") int errorTimes , Model model)throws ServletException, IOException{
        if(model.containsAttribute("user")){
            return "forward:/recommend/recommend";
        }
        log.info("用户登录");
        if("".equals(name)||name==null||"".equals(pwd)||pwd==null){
            model.addAttribute("errorTimes", errorTimes+1);
            model.addAttribute("errorMessage", "用户名或密码错误");
            log.info("失败次数"+(errorTimes+1));
            return "login";
        }
        List<User> userList = userService.getAllUser();
        for(User user:userList){
            if(name.equals(user.getUserName())&&pwd.equals(user.getUserPwd())){
                model.addAttribute("user",user);
                return "forward:/recommend/recommend";
            }else{
                model.addAttribute("errorTimes", errorTimes+1);
                model.addAttribute("errorMessage", "用户名或密码错误");
                log.info("失败次数"+(errorTimes+1));
                return "login";
            }
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(@ModelAttribute("user") User user,@ModelAttribute("errorTimes") int errorTimes, SessionStatus sessionStatus){
        //@ModelAttribute("User")相当于将session中名为"User"的对象注入user对象中
        //sessionStatus中的setComplete方法可以将session中的内容全部清空
        sessionStatus.setComplete();
        return "redirect:/login";
    }


}
