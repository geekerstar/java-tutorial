package com.geekerstar.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author geekerstar
 * date: 2019/2/1 10:32
 * description:
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    static {
//        INSTANCE = new Singleton3("www.jikewenku.com");
        Properties pro = new Properties();
        try {
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new Singleton3(pro.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Singleton3(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
