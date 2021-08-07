package com.geekerstar.asset.a1.m3.flow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanOne implements DomainAbilityBean {

  @Override
  public FlowContent invoke(FlowContent content) {
    // 方法的入参从 FlowContent 里面拿
    log.info("BeanOne is run,thread name is {}",Thread.currentThread().getName());
    return null;
  }
}
