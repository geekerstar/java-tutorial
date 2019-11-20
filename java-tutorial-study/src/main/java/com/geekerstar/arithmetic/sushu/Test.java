package com.geekerstar.arithmetic.sushu;

/**
 * @author geekerstar
 * date: 2019/3/13 09:19
 * description:
 *
 * 101-200之间有多少个素数
 */
public class Test {
    public static void main(String[] args) {
        int count = 0;
        for(int i =101; i < 200; i++){
            boolean b = false;
            for (int j =2; j <= Math.sqrt(i);j++){
                if (i % j == 0){
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b){
                count++;
                System.out.println(count);

            }
        }
        System.out.println("一共有"+ count + "个素数");

    }
}
