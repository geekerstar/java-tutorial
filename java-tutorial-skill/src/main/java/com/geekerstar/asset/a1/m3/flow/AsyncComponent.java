package com.geekerstar.asset.a1.m3.flow;

import java.lang.annotation.*;

/**
* 异步 SpringBean 执行注解
 * SpringBean 需要异步执行的话，就打上该注解
*author  wenhe
*date 2019/10/7
*/
@Target(ElementType.TYPE)// 表示该注解应该打在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AsyncComponent {

}
