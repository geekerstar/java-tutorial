package com.geekerstar.design.pattern.behavioral.chainofresponsibility;


public class Test {
    public static void main(String[] args) {
        Approver articleApprover = new ArticleApprover();
        Approver videoApprover = new VideoApprover();

        Course course = new Course();
        course.setName("Java设计模式 -- By Geekerstar");
        course.setArticle("Java设计模式手记");
        course.setVideo("Java设计模式的视频");

        articleApprover.setNextApprover(videoApprover);

        articleApprover.deploy(course);
    }
}
