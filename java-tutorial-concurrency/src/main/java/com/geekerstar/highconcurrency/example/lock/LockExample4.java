package com.geekerstar.highconcurrency.example.lock;

import java.util.concurrent.locks.StampedLock;


/**
 * @author geekerstar
 * date: 2019/1/22 12:14
 * description:
 */
public class LockExample4 {

    class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        /**
         * 下面看看乐观读锁案例
         * @return
         */
        double distanceFromOrigin() { //A read-only method
            //获得一个乐观读锁
            long stamp = sl.tryOptimisticRead();
            //将两个字段读入本地局部变量
            double currentX = x, currentY = y;
            //检查发出乐观读锁后同时是否有其他写锁发生？
            if (!sl.validate(stamp)) {
                //如果没有，我们再次获得一个读悲观锁
                stamp = sl.readLock();
                try {
                    // 将两个字段读入本地局部变量
                    currentX = x;
                    // 将两个字段读入本地局部变量
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        /**
         * 下面是悲观读锁案例
         * @param newX
         * @param newY
         */
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                //循环，检查当前状态是否符合
                while (x == 0.0 && y == 0.0) {
                    //将读锁转为写锁
                    long ws = sl.tryConvertToWriteLock(stamp);
                    //这是确认转为写锁是否成功
                    if (ws != 0L) {
                        //如果成功 替换票据
                        stamp = ws;
                        //进行状态改变
                        x = newX;
                        //进行状态改变
                        y = newY;
                        break;
                    } else { //如果不能成功转换为写锁
                        //我们显式释放读锁
                        sl.unlockRead(stamp);
                        //显式直接进行写锁 然后再通过循环再试
                        stamp = sl.writeLock();
                    }
                }
            } finally {
                //释放读锁或写锁
                sl.unlock(stamp);
            }
        }
    }
}

