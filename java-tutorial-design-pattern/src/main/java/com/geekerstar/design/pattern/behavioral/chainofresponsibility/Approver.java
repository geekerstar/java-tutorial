package com.geekerstar.design.pattern.behavioral.chainofresponsibility;


public abstract class Approver {
    protected Approver approver;

    public void setNextApprover(Approver approver){
        this.approver = approver;
    }
    public abstract void deploy(Course course);
}
