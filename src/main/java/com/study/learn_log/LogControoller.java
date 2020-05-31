package com.study.learn_log;


import com.study.learn_log.annotation.Log;
import com.study.learn_log.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
@RequestMapping("/log")
public class LogControoller {


    @Log(Constants.LogType.TEST)
    @GetMapping("/test")
    public String testLog(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) {


        return name;
    }
}
