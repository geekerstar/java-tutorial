package com.geekerstar.wx.t2;

/**
 * @author geekerstar
 * @date 2021/7/23 13:34
 * @description https://mp.weixin.qq.com/s/cedDS2QO09sTIvB3Y68sww
 */
public class TryWithResourceTest {

//    public static void main(String[] args) {
//        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
//             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
//            int b;
//            while ((b = bin.read()) != -1) {
//                bout.write(b);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    // 为了能够配合try-with-resource，资源必须实现AutoClosable接口。该接口的实现类需要重写close方法
    public static void main(String[] args) {
        try (Connection conn = new Connection()) {
            conn.sendData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
