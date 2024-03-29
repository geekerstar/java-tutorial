package com.geekerstar.asset.a1.m3.flow.threadlocal;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * AsyncRunnable
 *
 * @author likongpeng
 * @date 2017/9/3
 */
@Slf4j
@Getter
public class AsyncRunnable implements Runnable {

  private Runnable runnable;
  private HashMap hashMap = new HashMap();

  public AsyncRunnable(Runnable runnable) {
    this.runnable = runnable;
    copy(runnable);
  }

  @Override
  public void run() {
    try {
      ContextCache.putAllAttribute(hashMap);
      if (null != runnable) {
        runnable.run();
      }
    } catch (Exception e) {
      log.error("[AsyncRunnable-run] has error", e);
      throw new RuntimeException(e);
    } finally {
      hashMap = new HashMap();
      ContextCache.clean();
    }
  }

  private void copy(Runnable runnable) {
    try {
      hashMap.putAll(ContextCache.getMap());
    } catch (Exception e) {
      log.error("[AsyncRunnable-copy] has error,runnable is {}",
                runnable.toString(), e);
      throw new RuntimeException(e);
    }
  }

}
