package com.geekerstar.waiting.oom;

/**
 * @author geekerstar
 * date: 2019-07-24 09:59
 * description:
 *
 * 栈溢出，栈空间不足——StackOverflowError实例
 */
public class SOFTest {
    int depth = 0;
    public void sofMethod(){
        depth ++ ;
        sofMethod();
    }
    public static void main(String[] args) {
        SOFTest test = null;
        try {
            test = new SOFTest();
            test.sofMethod();
        } finally {
            System.out.println("递归次数："+test.depth);
        }
    }

}
