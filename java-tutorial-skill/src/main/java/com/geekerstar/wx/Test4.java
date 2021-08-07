package com.geekerstar.wx;

/**
 * @author geekerstar
 * @date 2021/7/25 16:26
 * @description  try catch相关问题
 *
 * https://mp.weixin.qq.com/s?__biz=MzI5NTYwNDQxNA==&mid=2247484297&idx=1&sn=c5e581565ca598d589f0d732ca9a869c&chksm=ec505a58db27d34e0f4f266579f80b2bf784a94a9c4b7718da79177085ec16478e995a2809e8&mpshare=1&scene=1&srcid=1029rfK3asA4QNS716h1bt5R#rd
 *
 *
 * http://mp.weixin.qq.com/s?__biz=MzA3ODQ0Mzg2OA==&mid=2649047296&idx=1&sn=25e36b71bcb0325ff7e005dd03d87dca&chksm=87534533b024cc25e4849ee2ac24ea5817b5c1e4182101e7c7bc258601c8759eb70ed057418a&mpshare=1&scene=1&srcid=1025o7p6ZhKjl07pGJtuPZA6#rd
 *
 * https://mp.weixin.qq.com/s/8tJ8g1JKW_z6ZugQI4gb8Q
 *
 * https://mp.weixin.qq.com/s?__biz=MzI4Njc5NjM1NQ==&mid=2247489901&idx=1&sn=25910872f9c7e884f1c2e7f63fe3885c&chksm=ebd62641dca1af57bed194b032122910ba4ace519299897516e00dad0339e973af17ccf9f16e&mpshare=1&scene=1&srcid=&sharer_sharetime=1570177280470&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class Test4 {

    //对以上所有的例子进行总结
    //1 try、catch、finally语句中，在如果try语句有return语句，则返回的之后当前try中变量此时对应的值，此后对变量做任何的修改，都不影响try中return的返回值
    //2 如果finally块中有return 语句，则返回try或catch中的返回语句忽略。
    //3 如果finally块中抛出异常，则整个try、catch、finally块中抛出异常
    //所以使用try、catch、finally语句块中需要注意的是
    //1 尽量在try或者catch中使用return语句。通过finally块中达到对try或者catch返回值修改是不可行的。
    //2 finally块中避免使用return语句，因为finally块中如果使用return语句，会显示的消化掉try、catch块中的异常信息，屏蔽了错误的发生
    //3 finally块中避免再次抛出异常，否则整个包含try语句块的方法回抛出异常，并且会消化掉try、catch块中的异常

    public int aaa() {
        int x = 1;

        try {
            return ++x;
        } catch (Exception e) {

        } finally {
            System.out.println("finally");

            ++x;
        }
        return x;
    }

    public static void main(String[] args) {
        Test4 t = new Test4();
        int y = t.aaa();
        System.out.println(y);
        System.out.println(test());
    }


    private static int test() {
        int num = 0;
        try {
            // num=1,此处不返回
            num++;
            return num;
        } catch (Exception e) {
            // do something
        } finally {
            // num=2,返回此值
            num++;
            return num;
        }
    }
}
