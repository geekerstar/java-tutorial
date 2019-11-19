package com.geekerstar.design.pattern.creational.factorymethod;

/**
 * @author geekerstar
 * date: 2019/1/7 10:08
 * description:
 */
public abstract class VideoFactory {
    public abstract Video getVideo();
//    public Video getVideo(String type){
//        if ("java".equalsIgnoreCase(type)){
//            return new JavaVideo();
//        } else if("python".equalsIgnoreCase(type)){
//            return new PythonVideo();
//        }
//        return null;
//    }

//    public Video getVideo(Class c){
//        Video video = null;
//        try {
//            video = (Video) Class.forName(c.getName()).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return video;
//    }
}
