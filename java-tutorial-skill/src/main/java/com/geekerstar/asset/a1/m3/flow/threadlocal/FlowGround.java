package com.geekerstar.asset.a1.m3.flow.threadlocal;

import java.lang.annotation.*;

/**
* FlowGround
*author  wenhe
*date 2019/10/16
*/
@Target(ElementType.METHOD)// 表示该注解应该打在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FlowGround {


  String requestKey();

  String responseKey();

}
