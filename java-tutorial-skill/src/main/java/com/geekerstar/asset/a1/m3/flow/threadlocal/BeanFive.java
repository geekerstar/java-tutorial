package com.geekerstar.asset.a1.m3.flow.threadlocal;

import com.geekerstar.asset.a1.m3.flow.AsyncComponent;
import com.geekerstar.asset.a1.m3.flow.DomainAbilityBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AsyncComponent
@Component
public class BeanFive implements DomainAbilityBean {

  @Override
  public void invoke() {
    String value = ContextCache.getAttribute("key1");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("get 线程名称为{}, value is {}", Thread.currentThread().getName(),value);
    log.info("------------------");
  }
}
