package com.geekerstar.design.pattern.structural.bridge;



public abstract class Bank {
    protected Account account;
    public Bank(Account account){
        this.account = account;
    }
    abstract Account openAccount();

}
