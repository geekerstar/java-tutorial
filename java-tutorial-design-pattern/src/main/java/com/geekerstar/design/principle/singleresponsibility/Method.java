package com.geekerstar.design.principle.singleresponsibility;

/**
 * @author geekerstar
 * date: 2019/1/6 15:23
 * description:
 */
public class Method {
    private void updateUserInfo(String userName,String address){
        userName = "Geekerstar";
        address = "chengdu";
    }

    private void updateUserInfo(String userName,String... properties){
        userName = "Geekerstar";
//        address = "chengdu";
    }

    private void updateUsername(String userName){
        userName = "Geekerstar";
    }

    private void updateUserAddress(String address){
        address = "chengdu";
    }

    private void updateUserInfo(String userName,String address,boolean bool){
        if(bool){
            //todo something1
        } else {
            //todo something2
        }
        userName = "Geekerstar";
        address = "chengdu";
    }
}
