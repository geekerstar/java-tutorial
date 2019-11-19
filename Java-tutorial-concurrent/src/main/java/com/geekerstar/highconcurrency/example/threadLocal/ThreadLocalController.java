package com.geekerstar.highconcurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author geekerstar
 * date: 2019/1/22 10:07
 * description:
 *
 * 线程封闭
 * Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
 * 堆栈封闭：局部变量，无并发问题
 * ThreadLocal线程封闭：特别好的封闭方法
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }
}
