package com.geekerstar.asset.a1.m3.flow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AsyncComponent
@Component
@Slf4j
public class BeanTwo implements DomainAbilityBean {

  @Override
  public FlowContent invoke(FlowContent content) {
    log.info("BeanTwo is run,thread name is {}",Thread.currentThread().getName());
    return null;
  }
}
